package com.olabode.wilson.statussaver.ui.bizwhatsapp.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.olabode.wilson.statussaver.databinding.BusinessWhatsappVideosBinding
import com.olabode.wilson.statussaver.ui.adapters.VideosRecyclerAdapter
import com.olabode.wilson.statussaver.ui.adapters.listeners.StatusClickListener
import com.olabode.wilson.statussaver.ui.bizwhatsapp.BizwhatsAppFragmentDirections
import com.olabode.wilson.statussaver.utils.StatusType

class BizVideosFragment : Fragment() {

    companion object {
        fun newInstance() = BizVideosFragment()
    }

    private lateinit var viewModel: BizVideosViewModel
    private lateinit var binding: BusinessWhatsappVideosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BusinessWhatsappVideosBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BizVideosViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.videosRecycler.adapter =
            VideosRecyclerAdapter(
                StatusClickListener { pos ->
                    findNavController().navigate(
                        BizwhatsAppFragmentDirections
                            .actionBizwhatsAppFragmentToVideosViewerFragment(
                                viewModel.listVideo.value!![pos]
                                , StatusType.BIZ_WHATSAPP
                            )
                    )
                })
    }

}
