package com.enciyo.satellitesapp.ui.satellites

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.enciyo.domain.model.Satellite
import com.enciyo.satellitesapp.R
import com.enciyo.satellitesapp.databinding.ItemSatelliteBinding
import com.enciyo.satellitesapp.ui.ext.take


class SatellitesAdapter(
    private val onClick: (Satellite) -> Unit,
) : ListAdapter<Satellite, SatellitesAdapter.SatellitesViewHolder>(DIFF),
    Filterable {

    private var originalList = listOf<Satellite>()

    override fun submitList(list: List<Satellite>?) {
        if (this.originalList.isEmpty()) {
            this.originalList = list.orEmpty()
        }
        super.submitList(list)
    }

    override fun submitList(list: List<Satellite>?, commitCallback: Runnable?) {
        if (this.originalList.isEmpty()) {
            this.originalList = list.orEmpty()
        }
        super.submitList(list, commitCallback)
    }


    inner class SatellitesViewHolder(
        private val binding: ItemSatelliteBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        private val ctx
            get() = binding.root.context

        fun bind(data: Satellite) {
            with(binding) {
                name.isEnabled = data.active
                status.isEnabled = data.active
                statusBadge.isEnabled = data.active
                name.text = data.name
                status.text = data.active.take(
                    takeTrue = ctx.getString(R.string.active),
                    takeFalse = ctx.getString(R.string.passive)
                )
                root.setOnClickListener {
                    onClick.invoke(data)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatellitesViewHolder =
        SatellitesViewHolder(
            ItemSatelliteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: SatellitesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DIFF : DiffUtil.ItemCallback<Satellite>() {
        override fun areItemsTheSame(oldItem: Satellite, newItem: Satellite): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Satellite, newItem: Satellite): Boolean =
            oldItem.active == newItem.active
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            results.values = if (constraint.isNullOrEmpty()) {
                originalList
            } else {
                originalList.filter {
                    it.name.lowercase().contains(constraint.toString().lowercase())
                }
            }
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            submitList(list = filterResults?.values as? List<Satellite> ?: return)
        }
    }

}