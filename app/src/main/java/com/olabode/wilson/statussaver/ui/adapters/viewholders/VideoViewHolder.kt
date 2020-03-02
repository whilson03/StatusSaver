package com.olabode.wilson.statussaver.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olabode.wilson.statussaver.databinding.ItemVideosBinding
import com.olabode.wilson.statussaver.model.Status
import com.olabode.wilson.statussaver.ui.adapters.listeners.StatusClickListener

// binding.root is the root view of the layout, in this case the constraint layout
class VideoViewHolder private constructor(val binding: ItemVideosBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Status, clickListener: StatusClickListener, position: Int) {
        binding.status = item
        binding.pos = position
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): VideoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemVideosBinding.inflate(
                layoutInflater
                , parent, false
            )
            return VideoViewHolder(
                binding
            )
        }
    }

}
