package com.olabode.wilson.statussaver.ui.whatsapp.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.olabode.wilson.statussaver.databinding.VideosFragmentBinding
import com.olabode.wilson.statussaver.ui.adapters.VideosRecyclerAdapter
import com.olabode.wilson.statussaver.ui.adapters.listeners.StatusClickListener
import com.olabode.wilson.statussaver.ui.whatsapp.WhatsAppFragmentDirections
import com.olabode.wilson.statussaver.utils.StatusType


class VideosFragment : Fragment() {

    companion object {
        fun newInstance() =
            VideosFragment()
    }

    private lateinit var viewModel: VideosViewModel
    private lateinit var binding: VideosFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VideosFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(VideosViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.videosRecycler.adapter =
            VideosRecyclerAdapter(
                StatusClickListener { pos ->
                    findNavController().navigate(
                        WhatsAppFragmentDirections
                            .actionWhatsAppFragmentToVideosViewerFragment(
                                viewModel.listVideo.value!![pos]
                                , StatusType.WHATSAPP
                            )
                    )
                })
    }

}
