package com.olabode.wilson.statussaver.ui.gbwhatsapp.gbimages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.olabode.wilson.statussaver.StatusClickListener
import com.olabode.wilson.statussaver.databinding.GbImagesFragmentBinding
import com.olabode.wilson.statussaver.ui.StatusType
import com.olabode.wilson.statussaver.ui.adapters.ImagesRecyclerAdapter
import com.olabode.wilson.statussaver.ui.gbwhatsapp.GbWhatsAppFragmentDirections

class GbImagesFragment : Fragment() {

    companion object {
        fun newInstance() =
            GbImagesFragment()
    }

    private lateinit var viewModel: GbImagesViewModel
    private lateinit var binding: GbImagesFragmentBinding
    private lateinit var adapter: ImagesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GbImagesFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GbImagesViewModel::class.java)
        binding.lifecycleOwner = this
        viewModel.listImages.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter =
                    ImagesRecyclerAdapter(
                        StatusClickListener { pos ->
                            this.findNavController().navigate(
                                GbWhatsAppFragmentDirections
                                    .actionGbWhatsAppFragmentToViewFragment(
                                        it.toTypedArray(),
                                        pos,
                                        StatusType.GB_WHATSAPP
                                    )
                            )
                        })
                binding.imagesRecycler.adapter = adapter
                adapter.submitList(it)

            }
        })
    }

}
