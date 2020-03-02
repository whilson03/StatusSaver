package com.olabode.wilson.statussaver.ui.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.olabode.wilson.statussaver.model.Status

class UriDiffCallBack : DiffUtil.ItemCallback<Status>() {
    override fun areItemsTheSame(oldItem: Status, newItem: Status): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Status, newItem: Status): Boolean {
        return oldItem == newItem
    }
}

