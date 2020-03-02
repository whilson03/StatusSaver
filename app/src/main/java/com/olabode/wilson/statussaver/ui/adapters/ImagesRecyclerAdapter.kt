package com.olabode.wilson.statussaver.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.olabode.wilson.statussaver.model.Status
import com.olabode.wilson.statussaver.ui.adapters.callbacks.UriDiffCallBack
import com.olabode.wilson.statussaver.ui.adapters.listeners.StatusClickListener
import com.olabode.wilson.statussaver.ui.adapters.viewholders.ImageViewHolder

/**
 *   Created by OLABODE WILSON on 2020-01-10.
 */
class ImagesRecyclerAdapter(val clickListener: StatusClickListener) :
    RecyclerView.Adapter<ImageViewHolder>() {

    init {
        setHasStableIds(true)
    }

    private val DIFF_CALLBACK = UriDiffCallBack()

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item, clickListener, position)
    }

    fun submitList(list: List<Status>?) {
        differ.submitList(list)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}