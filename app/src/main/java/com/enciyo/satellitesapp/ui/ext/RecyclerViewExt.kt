package com.enciyo.satellitesapp.ui.ext

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.InsetDrawable
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt


fun Fragment. dividerDecoration(
    orientation: Int = RecyclerView.VERTICAL,
    @DrawableRes dividerId: Int = -1,
    @DimenRes margin: Int? = null,
    drawLast: Boolean = true,
    ignoredItemTypes: Array<Int> = emptyArray(),
): Lazy<DividerItemDecoration> = lazy {
    object : DividerItemDecoration(requireContext(), orientation) {
        private val mBounds = Rect()

        init {
            if (dividerId > 0) {
                setDrawable(AppCompatResources.getDrawable(requireContext(), dividerId)!!)
            }
            val isHorizontal = orientation == RecyclerView.HORIZONTAL
            margin?.let {
                val inset = resources.getDimensionPixelSize(it)
                val insetDivider = InsetDrawable(
                    drawable,
                    if (isHorizontal) 0 else inset,
                    if (isHorizontal) inset else 0,
                    if (isHorizontal) 0 else inset,
                    if (isHorizontal) inset else 0
                )
                setDrawable(insetDivider)
            }
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
        ) {
            val position = parent.getChildAdapterPosition(view)
            val viewType = if (position > -1) {
                parent.adapter?.getItemViewType(position) ?: -1
            } else {
                -1
            }
            val count = parent.adapter?.itemCount ?: -1
            val isDraw =
                ignoredItemTypes.contains(viewType).not() && drawLast.take(true,
                    position != count - 1)
            draw(outRect, isDraw)
        }

        private fun draw(outRect: Rect, isDraw: Boolean) {
            if (drawable == null || isDraw.not()) {
                outRect.setEmpty()
                return
            }
            if (orientation == VERTICAL) {
                outRect.set(0, 0, 0, drawable!!.intrinsicHeight)
            } else {
                outRect.set(0, 0, drawable!!.intrinsicWidth, 0)
            }
        }

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            if (parent.layoutManager == null || drawable == null) {
                return
            }
            if (orientation == VERTICAL) {
                drawVertical(c, parent)
            } else {
                drawHorizontal(c, parent)
            }
        }

        private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
            val divider = drawable ?: return
            canvas.save()
            val left: Int
            val right: Int
            if (parent.clipToPadding) {
                left = parent.paddingStart
                right = parent.width - parent.paddingEnd
                canvas.clipRect(left,
                    parent.paddingTop,
                    right,
                    parent.height - parent.paddingBottom)
            } else {
                left = 0
                right = parent.width
            }
            val childCount = parent.childCount
            for (i in 0 until (childCount - 1)) {
                val child = parent.getChildAt(i)
                if (canDraw(parent, child)) {
                    parent.getDecoratedBoundsWithMargins(child, mBounds)
                    val bottom = mBounds.bottom + child.translationY.roundToInt()
                    val top = bottom - divider.intrinsicHeight
                    divider.setBounds(left, top, right, bottom)
                    divider.draw(canvas)
                }
            }
            canvas.restore()
        }

        private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
            val divider = drawable ?: return
            canvas.save()
            val top: Int
            val bottom: Int
            if (parent.clipToPadding) {
                top = parent.paddingTop
                bottom = parent.height - parent.paddingBottom
                canvas.clipRect(parent.paddingStart, top, parent.width - parent.paddingEnd, bottom)
            } else {
                top = 0
                bottom = parent.height
            }
            val childCount = parent.childCount
            for (i in 0 until childCount) {
                val layoutManager = parent.layoutManager!!
                val child = parent.getChildAt(i)
                if (canDraw(parent, child)) {
                    layoutManager.getDecoratedBoundsWithMargins(child, mBounds)
                    val right = mBounds.right + child.translationX.roundToInt()
                    val left = right - divider.intrinsicWidth
                    divider.setBounds(left, top, right, bottom)
                    divider.draw(canvas)
                }
            }
            canvas.restore()
        }

        private fun canDraw(parent: RecyclerView, view: View): Boolean {
            val position = parent.getChildAdapterPosition(view)
            val viewType = if (position > -1) {
                parent.adapter?.getItemViewType(position) ?: -1
            } else {
                -1
            }
            val count = parent.adapter?.itemCount ?: -1
            return ignoredItemTypes.contains(viewType).not() && drawLast.take(
                true,
                position != count - 1
            )
        }
    }
}

fun <T : RecyclerView.ViewHolder> RecyclerView.attach(
    adapter: RecyclerView.Adapter<T>,
    layoutManager: RecyclerView.LayoutManager,
    decorator: RecyclerView.ItemDecoration? = null,
) {
    this.adapter = adapter
    this.layoutManager = layoutManager
    decorator?.let(::addItemDecoration)
}

fun RecyclerView.detach() {
    layoutManager = null
    adapter = null
    clearOnScrollListeners()
    val decoratorCount = itemDecorationCount
    if (decoratorCount <= 0) return
    for (i in 0 until decoratorCount) {
        removeItemDecoration(getItemDecorationAt(0))
    }
}


fun Fragment.linearLayoutManager(isHorizontal: Boolean = false): Lazy<LinearLayoutManager> = lazy {
    LinearLayoutManager(
        requireContext(),
        if (isHorizontal) LinearLayoutManager.HORIZONTAL else LinearLayoutManager.VERTICAL,
        false
    )
}

fun <T : Any> Boolean.take(takeTrue: T, takeFalse: T): T {
    return if (this) takeTrue else takeFalse
}
