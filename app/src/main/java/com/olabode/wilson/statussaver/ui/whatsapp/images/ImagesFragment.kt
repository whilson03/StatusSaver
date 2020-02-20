package com.olabode.wilson.statussaver.ui.whatsapp.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.olabode.wilson.statussaver.StatusClickListener
import com.olabode.wilson.statussaver.databinding.ImagesFragmentBinding
import com.olabode.wilson.statussaver.ui.StatusType
import com.olabode.wilson.statussaver.ui.adapters.ImagesRecyclerAdapter
import com.olabode.wilson.statussaver.ui.whatsapp.WhatsAppFragmentDirections

class ImagesFragment : Fragment() {

    companion object {
        fun newInstance() = ImagesFragment()
    }

    private lateinit var viewModel: ImagesViewModel
    private lateinit var binding: ImagesFragmentBinding
    private lateinit var adapter: ImagesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ImagesFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ImagesViewModel::class.java)
        binding.lifecycleOwner = this
        viewModel.listImages.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter =
                    ImagesRecyclerAdapter(
                        StatusClickListener { pos ->
                            this.findNavController().navigate(
                                WhatsAppFragmentDirections
                                    .actionWhatsAppFragmentToViewFragment(
                                        it.toTypedArray(),
                                        pos,
                                        StatusType.WHATSAPP
                                    )
                            )
                        })
                binding.imagesRecycler.adapter = adapter
                adapter.submitList(it)

            }
        })
    }

}
