package com.olabode.wilson.statussaver.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olabode.wilson.statussaver.databinding.ItemImagesBinding
import com.olabode.wilson.statussaver.model.Status
import com.olabode.wilson.statussaver.ui.adapters.listeners.StatusClickListener

/**
 *   Created by OLABODE WILSON on 2020-03-01.
 */

class ImageViewHolder private constructor(val binding: ItemImagesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Status, clickListener: StatusClickListener, position: Int) {
        binding.status = item
        binding.pos = position
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemImagesBinding.inflate(
                layoutInflater
                , parent, false
            )
            return ImageViewHolder(
                binding
            )
        }
    }

}