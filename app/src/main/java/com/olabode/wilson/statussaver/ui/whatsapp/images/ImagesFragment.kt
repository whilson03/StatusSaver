package com.olabode.wilson.statussaver.ui.whatsapp.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.olabode.wilson.statussaver.databinding.ImagesFragmentBinding
import com.olabode.wilson.statussaver.ui.adapters.ImagesRecyclerAdapter
import com.olabode.wilson.statussaver.ui.adapters.listeners.StatusClickListener
import com.olabode.wilson.statussaver.ui.whatsapp.WhatsAppFragmentDirections
import com.olabode.wilson.statussaver.utils.StatusType

class ImagesFragment : Fragment() {

    companion object {
        fun newInstance() = ImagesFragment()
    }


    private lateinit var viewModel: ImagesViewModel
    private lateinit var binding: ImagesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ImagesFragmentBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this).get(ImagesViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.imagesRecycler.adapter = ImagesRecyclerAdapter(
            StatusClickListener { pos ->
                this.findNavController().navigate(
                    WhatsAppFragmentDirections
                        .actionWhatsAppFragmentToViewFragment(
                            viewModel.listImages.value!!.toTypedArray(),
                            pos,
                            StatusType.WHATSAPP
                        )
                )
            })

    }
}
