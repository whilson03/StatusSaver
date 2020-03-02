package com.olabode.wilson.statussaver.utils

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.olabode.wilson.statussaver.R
import com.olabode.wilson.statussaver.model.Status
import com.olabode.wilson.statussaver.ui.adapters.ImagesRecyclerAdapter
import com.olabode.wilson.statussaver.ui.adapters.SavedRecyclerAdapter
import com.olabode.wilson.statussaver.ui.adapters.VideosRecyclerAdapter
import java.io.File

/**
 *   Created by OLABODE WILSON on 2019-09-02.
 */

@BindingAdapter("image")
fun bindImage(imgView: ImageView, path: String) {
    path.let {
        val file = File(it)
        Glide.with(imgView.context)

            .load(Uri.fromFile(file))
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.ic_menu_camera)
            )
            .into(imgView)
    }
}

@BindingAdapter("video")
fun bindVideo(imgView: ImageView, path: String) {
    path.let {
        val file = File(it)
        Glide.with(imgView.context)

            .load(Uri.fromFile(file))
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_menu_slideshow)
            )
            .into(imgView)
    }
}


/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}


@BindingAdapter("listImages")
fun bindImagesRecyclerView(recyclerView: RecyclerView, data: List<Status>?) {
    val adapter = recyclerView.adapter as ImagesRecyclerAdapter
    adapter.submitList(data)
    recyclerView.smoothScrollToPosition(0)
}


@BindingAdapter("listVideos")
fun bindVideosRecyclerView(recyclerView: RecyclerView, data: List<Status>?) {
    val adapter = recyclerView.adapter as VideosRecyclerAdapter
    adapter.submitList(data)
    recyclerView.smoothScrollToPosition(0)
}


@BindingAdapter("listSaved")
fun bindSavedRecyclerView(recyclerView: RecyclerView, data: List<Status>?) {
    val adapter = recyclerView.adapter as SavedRecyclerAdapter
    adapter.submitList(data)
    recyclerView.smoothScrollToPosition(0)
}