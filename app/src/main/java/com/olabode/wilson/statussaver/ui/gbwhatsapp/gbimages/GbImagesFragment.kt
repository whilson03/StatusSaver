package com.olabode.wilson.statussaver.ui.gbwhatsapp.gbimages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.olabode.wilson.statussaver.databinding.GbWhatsappImagesBinding
import com.olabode.wilson.statussaver.ui.adapters.ImagesRecyclerAdapter
import com.olabode.wilson.statussaver.ui.adapters.listeners.StatusClickListener
import com.olabode.wilson.statussaver.ui.gbwhatsapp.GbWhatsAppFragmentDirections
import com.olabode.wilson.statussaver.utils.StatusType

class GbImagesFragment : Fragment() {

    companion object {
        fun newInstance() =
            GbImagesFragment()
    }

    private lateinit var viewModel: GbImagesViewModel
    private lateinit var binding: GbWhatsappImagesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GbWhatsappImagesBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GbImagesViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.imagesRecycler.adapter =
            ImagesRecyclerAdapter(
                StatusClickListener { pos ->
                    this.findNavController().navigate(
                        GbWhatsAppFragmentDirections
                            .actionGbWhatsAppFragmentToViewFragment(
                                viewModel.listImages.value!!.toTypedArray(),
                                pos,
                                StatusType.GB_WHATSAPP
                            )
                    )
                })

    }

}
