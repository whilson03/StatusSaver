package com.olabode.wilson.statussaver.ui.viewers.imagesviewer

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.olabode.wilson.statussaver.R
import com.olabode.wilson.statussaver.databinding.ImagesViewerFragmentBinding
import com.olabode.wilson.statussaver.ui.model.Status
import java.io.File

class ImagesViewerFragment : Fragment() {

    companion object {
        private const val ARG_STATUS = "status"
        fun newInstance(status: Status): ImagesViewerFragment {
            val args: Bundle = Bundle()
            args.putParcelable(ARG_STATUS, status)
            val fragment = ImagesViewerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getParcelable<Status>(ARG_STATUS)?.let {
            status = it
        }
    }

    private lateinit var viewModel: ImagesViewerViewModel
    private lateinit var binding: ImagesViewerFragmentBinding
    private lateinit var status: Status

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ImagesViewerFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory: ImageViewerFactory = ImageViewerFactory(status)
        viewModel = ViewModelProviders.of(this, factory).get(ImagesViewerViewModel::class.java)

        viewModel.currentImage.observe(viewLifecycleOwner, Observer {
            it?.let {
                Glide.with(requireContext())
                    .load(Uri.fromFile(File(it.path)))
                    .apply(
                        RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(R.drawable.ic_menu_camera)
                    )
                    .into(binding.imageView)
            }
        })


    }

}
