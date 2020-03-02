package com.olabode.wilson.statussaver.ui.adapters

/**
 *   Created by OLABODE WILSON on 2020-02-20.
 */

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.olabode.wilson.statussaver.model.Status
import com.olabode.wilson.statussaver.ui.adapters.callbacks.UriDiffCallBack
import com.olabode.wilson.statussaver.ui.adapters.listeners.StatusClickListener
import com.olabode.wilson.statussaver.ui.adapters.viewholders.ImageViewHolder
import com.olabode.wilson.statussaver.ui.adapters.viewholders.VideoViewHolder

/**
 *   Created by OLABODE WILSON on 2020-01-10.
 */
class SavedRecyclerAdapter(val clickListener: StatusClickListener) :
    ListAdapter<Status, RecyclerView.ViewHolder>(
        UriDiffCallBack()
    ) {

    private val ITEM_TYPE_IMAGE = 0
    private val ITEM_TYPE_VIDEO = 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (item.path.endsWith(".jpg")) {
            true -> {
                (holder as ImageViewHolder).bind(
                    item!!,
                    clickListener,
                    position
                )
            }
            else -> {
                (holder as VideoViewHolder).bind(
                    item!!,
                    clickListener,
                    position
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_IMAGE -> {
                ImageViewHolder.from(parent)
            }
            else -> {
                VideoViewHolder.from(parent)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).path.endsWith(".jpg")) {
            true -> ITEM_TYPE_IMAGE
            else -> ITEM_TYPE_VIDEO

        }
    }


}