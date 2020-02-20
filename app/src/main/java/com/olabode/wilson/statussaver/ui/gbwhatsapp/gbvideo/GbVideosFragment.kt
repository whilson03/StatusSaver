package com.olabode.wilson.statussaver.ui.gbwhatsapp.gbvideo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.olabode.wilson.statussaver.StatusClickListener
import com.olabode.wilson.statussaver.databinding.GbVideosFragmentBinding
import com.olabode.wilson.statussaver.ui.StatusType
import com.olabode.wilson.statussaver.ui.adapters.VideosRecyclerAdapter
import com.olabode.wilson.statussaver.ui.gbwhatsapp.GbWhatsAppFragmentDirections

class GbVideosFragment : Fragment() {

    companion object {
        fun newInstance() =
            GbVideosFragment()
    }

    private lateinit var viewModel: GbVideosViewModel
    private lateinit var binding: GbVideosFragmentBinding
    private lateinit var adapter: VideosRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GbVideosFragmentBinding.inflate(inflater)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GbVideosViewModel::class.java)
        binding.lifecycleOwner = this
        viewModel.listVideo.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter =
                    VideosRecyclerAdapter(
                        StatusClickListener { pos ->
                            findNavController().navigate(
                                GbWhatsAppFragmentDirections
                                    .actionGbWhatsAppFragmentToVideosViewerFragment(
                                        it[pos]
                                        , StatusType.GB_WHATSAPP
                                    )
                            )
                        })
                binding.videoRecycler.adapter = adapter
                adapter.submitList(it)

            }
        })

    }

}
