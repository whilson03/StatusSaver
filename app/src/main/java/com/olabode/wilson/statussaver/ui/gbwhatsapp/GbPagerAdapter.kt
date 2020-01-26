package com.olabode.wilson.statussaver.ui.gbwhatsapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.olabode.wilson.statussaver.ui.gbwhatsapp.gbimages.GbImagesFragment
import com.olabode.wilson.statussaver.ui.gbwhatsapp.gbsaved.GbSavedFragment
import com.olabode.wilson.statussaver.ui.gbwhatsapp.gbvideo.GbVideosFragment

/**
 *   Created by OLABODE WILSON on 2020-01-17.
 */


@Suppress("DEPRECATION")
class GbPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val titles = listOf("Images", "Videos", "Saved")

    private val COUNT = 3

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = GbImagesFragment()
            1 -> fragment = GbVideosFragment()
            2 -> fragment = GbSavedFragment()

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
