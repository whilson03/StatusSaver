package com.olabode.wilson.statussaver.ui.whatsapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.olabode.wilson.statussaver.databinding.FragmentWhatsAppBinding

/**
 * A simple [Fragment] subclass.
 */
class WhatsAppFragment : Fragment() {


    private lateinit var binding: FragmentWhatsAppBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhatsAppBinding.inflate(inflater)

        val viewPager = binding.viewPager
        val adapter = WhatsAppPagerAdapter(childFragmentManager)
        viewPager.adapter = adapter

        val tab = binding.tablayout
        tab.setupWithViewPager(binding.viewPager)

        return binding.root


    }


}
