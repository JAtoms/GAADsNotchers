package com.example.gadsnotchers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gadsnotchers.databinding.FragmentLearningLeadersBinding
import com.example.gadsnotchers.databinding.HourListItemBinding
import com.example.gadsnotchers.domain.HoursDataClass


class HoursRecyclerViewAdapter() :
    ListAdapter<HoursDataClass, HoursRecyclerViewAdapter.HoursViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        return HoursViewHolder.createView(
            parent
        )
    }

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        val hoursDataclass = getItem(position)
        holder.bind(hoursDataclass)
    }

    class HoursViewHolder(private var binding: HourListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hoursdataclass: HoursDataClass) {
            binding.hoursDataClass = hoursdataclass
            binding.executePendingBindings()
        }

        companion object {
            fun createView(parent: ViewGroup): HoursViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HourListItemBinding.inflate(layoutInflater, parent, false)
                return HoursViewHolder(binding)
            }
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<HoursDataClass>() {
        override fun areItemsTheSame(oldItem: HoursDataClass, newItem: HoursDataClass): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HoursDataClass, newItem: HoursDataClass): Boolean {
            return oldItem == newItem
        }

    }
}
