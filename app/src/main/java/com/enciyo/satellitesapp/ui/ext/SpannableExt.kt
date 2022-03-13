package com.enciyo.satellitesapp.ui.ext

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan


fun String.toBold(boldText: String): Spannable {
    return SpannableStringBuilder(this)
        .addSpans(boldText)
}

fun Spannable.addSpans(
    markedTextBold: String? = null,
): Spannable = apply {
    addSpan(markedTextBold.toString()) { StyleSpan(Typeface.BOLD) }
}

fun Spannable.addSpan(markedText: String, spanBlock: () -> Any) = apply {
    val startIndex = this.toString().indexOf(markedText)
    val endIndex = startIndex + markedText.length
    if (startIndex != -1 && endIndex <= this.toString().length) {
        this.setSpan(spanBlock(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}