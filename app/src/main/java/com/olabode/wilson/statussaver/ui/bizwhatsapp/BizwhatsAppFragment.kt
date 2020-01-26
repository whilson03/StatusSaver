package com.olabode.wilson.statussaver.ui.bizwhatsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.olabode.wilson.statussaver.databinding.FragmentBizwhatsAppBinding

class BizwhatsAppFragment : Fragment() {

    companion object {
        fun newInstance() =
            BizwhatsAppFragment()
    }

    private lateinit var binding: FragmentBizwhatsAppBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBizwhatsAppBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewPager = binding.viewPager
        val adapter = BizPagerAdapter(childFragmentManager)
        viewPager.adapter = adapter

        val tab = binding.tablayout
        tab.setupWithViewPager(binding.viewPager)
    }

}
