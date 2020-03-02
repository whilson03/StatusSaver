package com.olabode.wilson.statussaver.ui.whatsapp.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.olabode.wilson.statussaver.databinding.SavedFragmentBinding
import com.olabode.wilson.statussaver.ui.adapters.SavedRecyclerAdapter
import com.olabode.wilson.statussaver.ui.adapters.listeners.StatusClickListener
import com.olabode.wilson.statussaver.ui.whatsapp.WhatsAppFragmentDirections
import com.olabode.wilson.statussaver.utils.StatusType

class SavedFragment : Fragment() {

    companion object {
        fun newInstance() = SavedFragment()
    }

    private lateinit var viewModel: SavedViewModel
    private lateinit var binding: SavedFragmentBinding
    private lateinit var adapter: SavedRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SavedFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SavedViewModel::class.java)
        binding.lifecycleOwner = this
        viewModel.listImages.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter =
                    SavedRecyclerAdapter(
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
                binding.savedRecycler.adapter = adapter
                adapter.submitList(it)
            }
        })


    }

}


