package com.olabode.wilson.statussaver.ui.whatsapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.olabode.wilson.statussaver.ui.whatsapp.images.ImagesFragment
import com.olabode.wilson.statussaver.ui.whatsapp.saved.SavedFragment
import com.olabode.wilson.statussaver.ui.whatsapp.videos.VideosFragment

/**
 *   Created by OLABODE WILSON on 2018-01-02.
 */


@Suppress("DEPRECATION")
class WhatsAppPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val titles = listOf("Images", "Videos", "Saved")

    private val COUNT = 3

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ImagesFragment()
            1 -> fragment = VideosFragment()
            2 -> fragment = SavedFragment()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}
