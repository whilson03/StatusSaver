package com.olabode.wilson.statussaver.ui.gbwhatsapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.olabode.wilson.statussaver.databinding.FragmentGbWhatsAppBinding

/**
 * A simple [Fragment] subclass.
 */
class GbWhatsAppFragment : Fragment() {

    private lateinit var binding: FragmentGbWhatsAppBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGbWhatsAppBinding.inflate(inflater)
        val viewPager = binding.viewPager
        val adapter = GbPagerAdapter(childFragmentManager)
        viewPager.adapter = adapter

        val tab = binding.tablayout
        tab.setupWithViewPager(binding.viewPager)

        return binding.root
    }


}
