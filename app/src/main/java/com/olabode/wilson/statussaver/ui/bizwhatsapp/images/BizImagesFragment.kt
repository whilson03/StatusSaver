package com.olabode.wilson.statussaver.ui.bizwhatsapp.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.olabode.wilson.statussaver.databinding.BusinessWhatsappImagesBinding
import com.olabode.wilson.statussaver.ui.adapters.ImagesRecyclerAdapter
import com.olabode.wilson.statussaver.ui.adapters.listeners.StatusClickListener
import com.olabode.wilson.statussaver.ui.bizwhatsapp.BizwhatsAppFragmentDirections
import com.olabode.wilson.statussaver.utils.StatusType

class BizImagesFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = BizImagesFragment()
    }

    private lateinit var viewModel: BizImagesViewModel
    private lateinit var binding: BusinessWhatsappImagesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BusinessWhatsappImagesBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BizImagesViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.imagesRecycler.adapter =
            ImagesRecyclerAdapter(
                StatusClickListener { pos ->
                    findNavController().navigate(
                        BizwhatsAppFragmentDirections
                            .actionBizwhatsAppFragmentToViewFragment(
                                viewModel.listImages.value!!.toTypedArray(),
                                pos,
                                StatusType.BIZ_WHATSAPP
                            )
                    )
                })

    }

}
