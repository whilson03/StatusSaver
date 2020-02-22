package com.olabode.wilson.statussaver

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
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