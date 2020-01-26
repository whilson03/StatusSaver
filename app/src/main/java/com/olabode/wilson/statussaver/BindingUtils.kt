package com.olabode.wilson.statussaver

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.olabode.wilson.statussaver.ui.adapters.ImagesRecyclerAdapter
import com.olabode.wilson.statussaver.ui.model.Status
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
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_menu_camera)
            )
            .into(imgView)
    }
}


@BindingAdapter("listImages")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Status>?) {
    val adapter = recyclerView.adapter as ImagesRecyclerAdapter
    adapter.submitList(data) {
        recyclerView.smoothScrollToPosition(0)
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