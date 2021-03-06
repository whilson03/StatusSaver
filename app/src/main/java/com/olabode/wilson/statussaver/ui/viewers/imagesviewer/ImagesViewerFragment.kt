package com.olabode.wilson.statussaver.ui.viewers.imagesviewer

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.olabode.wilson.statussaver.R
import com.olabode.wilson.statussaver.databinding.ImagesViewerFragmentBinding
import com.olabode.wilson.statussaver.model.Status
import com.olabode.wilson.statussaver.utils.StatusType
import com.olabode.wilson.statussaver.utils.Utils
import java.io.File

class ImagesViewerFragment : Fragment() {

    companion object {
        private const val ARG_STATUS = "status"
        private const val ARG_STATUS_TYPE = "status_type"
        fun newInstance(
            status: Status,
            statusType: String
        ): ImagesViewerFragment {
            val args = Bundle()
            args.putParcelable(ARG_STATUS, status)
            args.putString(ARG_STATUS_TYPE, statusType)
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

        arguments?.getString(ARG_STATUS_TYPE)?.let {
            statusType = StatusType.valueOf(it)
        }

    }

    private lateinit var viewModel: ImagesViewerViewModel
    private lateinit var binding: ImagesViewerFragmentBinding
    private lateinit var status: Status
    private lateinit var statusType: StatusType

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ImagesViewerFragmentBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory: ImageViewerFactory = ImageViewerFactory(status, statusType)
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


        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                viewModel.resetErrorMessage()
            }

        })

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_functions, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.download -> {
                Toast.makeText(context, "Saving...", Toast.LENGTH_SHORT).show()
                viewModel.save()
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            }
            R.id.repost -> {
                val intent = Utils.openWhatsApp(context!!, status.path)
                if (Utils.isIntentAvailable(context!!, intent)) {
                    startActivity(intent)
                } else {
                    Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.share -> {
                Utils.shareToOtherApp(context!!, status.path)
            }

        }
        return super.onOptionsItemSelected(item)
    }

}
