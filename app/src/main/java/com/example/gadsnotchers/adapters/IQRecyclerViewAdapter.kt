package com.example.gadsnotchers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gadsnotchers.databinding.IqListItemBinding
import com.example.gadsnotchers.domain.IQDataClass


class IQRecyclerViewAdapter() :
    ListAdapter<IQDataClass, IQRecyclerViewAdapter.HoursViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        return HoursViewHolder.createView(
            parent
        )
    }

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        val iqDataClass = getItem(position)
        holder.bind(iqDataClass)
    }

    class HoursViewHolder(private var binding: IqListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(iqDataClass: IQDataClass) {
            binding.iqDataClass = iqDataClass
            binding.executePendingBindings()
        }

        companion object {
            fun createView(parent: ViewGroup): HoursViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IqListItemBinding.inflate(layoutInflater, parent, false)
                return HoursViewHolder(binding)
            }
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<IQDataClass>() {
        override fun areItemsTheSame(oldItem: IQDataClass, newItem: IQDataClass): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: IQDataClass, newItem: IQDataClass): Boolean {
            return oldItem == newItem
        }

    }
}
