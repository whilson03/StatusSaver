package com.olabode.wilson.statussaver.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.olabode.wilson.statussaver.StatusClickListener
import com.olabode.wilson.statussaver.databinding.ItemVideosBinding
import com.olabode.wilson.statussaver.ui.model.Status

/**
 *   Created by OLABODE WILSON on 2020-01-10.
 */
class VideosRecyclerAdapter(val clickListener: StatusClickListener) :
    ListAdapter<Status, VideosRecyclerAdapter.viewHolder>(
        UriDiffCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder.from(
            parent
        )
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!, clickListener, position)
    }

    // binding.root is the root view of the layout, in this case the constraint layout
    class viewHolder private constructor(val binding: ItemVideosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Status, clickListener: StatusClickListener, position: Int) {
            binding.uriHolder = item
            binding.pos = position
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): viewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemVideosBinding.inflate(
                    layoutInflater
                    , parent, false
                )
                return viewHolder(
                    binding
                )
            }
        }

    }


    class UriDiffCallBack : DiffUtil.ItemCallback<Status>() {
        override fun areItemsTheSame(oldItem: Status, newItem: Status): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Status, newItem: Status): Boolean {
            return oldItem == newItem
        }
    }


}