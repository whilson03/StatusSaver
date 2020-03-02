package com.olabode.wilson.statussaver.ui.gbwhatsapp.gbvideo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.olabode.wilson.statussaver.databinding.GbWhatsappVideosBinding
import com.olabode.wilson.statussaver.ui.adapters.VideosRecyclerAdapter
import com.olabode.wilson.statussaver.ui.adapters.listeners.StatusClickListener
import com.olabode.wilson.statussaver.ui.gbwhatsapp.GbWhatsAppFragmentDirections
import com.olabode.wilson.statussaver.utils.StatusType

class GbVideosFragment : Fragment() {

    companion object {
        fun newInstance() =
            GbVideosFragment()
    }

    private lateinit var viewModel: GbVideosViewModel
    private lateinit var binding: GbWhatsappVideosBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GbWhatsappVideosBinding.inflate(inflater)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GbVideosViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.videosRecycler.adapter =
            VideosRecyclerAdapter(
                StatusClickListener { pos ->
                    findNavController().navigate(
                        GbWhatsAppFragmentDirections
                            .actionGbWhatsAppFragmentToVideosViewerFragment(
                                viewModel.listVideo.value!![pos]
                                , StatusType.GB_WHATSAPP
                            )
                    )
                })


    }

}
