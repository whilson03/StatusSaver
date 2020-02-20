package com.olabode.wilson.statussaver.ui.bizwhatsapp.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.olabode.wilson.statussaver.StatusClickListener

import com.olabode.wilson.statussaver.databinding.VideosFragmentBinding
import com.olabode.wilson.statussaver.ui.StatusType
import com.olabode.wilson.statussaver.ui.adapters.VideosRecyclerAdapter
import com.olabode.wilson.statussaver.ui.bizwhatsapp.BizwhatsAppFragmentDirections

class BizVideosFragment : Fragment() {

    companion object {
        fun newInstance() = BizVideosFragment()
    }

    private lateinit var viewModel: BizVideosViewModel
    private lateinit var binding: VideosFragmentBinding
    private lateinit var adapter: VideosRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VideosFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BizVideosViewModel::class.java)
        binding.lifecycleOwner = this
        viewModel.listVideo.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter =
                    VideosRecyclerAdapter(
                        StatusClickListener { pos ->
                            findNavController().navigate(
                                BizwhatsAppFragmentDirections
                                    .actionBizwhatsAppFragmentToVideosViewerFragment(
                                        it[pos]
                                        , StatusType.BIZ_WHATSAPP
                                    )
                            )
                        })
                binding.videoRecycler.adapter = adapter
                adapter.submitList(it)

            }
        })
    }

}
