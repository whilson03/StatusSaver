package com.olabode.wilson.statussaver.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.olabode.wilson.statussaver.StatusClickListener
import com.olabode.wilson.statussaver.databinding.ItemImagesBinding
import com.olabode.wilson.statussaver.ui.model.Status

/**
 *   Created by OLABODE WILSON on 2020-01-10.
 */
class ImagesRecyclerAdapter(val clickListener: StatusClickListener) :
    RecyclerView.Adapter<ImagesRecyclerAdapter.ViewHolder>() {


    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Status>() {

        override fun areItemsTheSame(oldItem: Status, newItem: Status): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Status, newItem: Status): Boolean {
            return oldItem == newItem
        }

    }


    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item, clickListener, position)
    }

    // binding.root is the root view of the layout, in this case the constraint layout
    class ViewHolder private constructor(val binding: ItemImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Status, clickListener: StatusClickListener, position: Int) {
            binding.uriHolder = item
            binding.pos = position
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemImagesBinding.inflate(
                    layoutInflater
                    , parent, false
                )
                return ViewHolder(
                    binding
                )
            }
        }

    }

    fun submitList(list: List<Status>?) {
        differ.submitList(list)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}