package com.olabode.wilson.statussaver.ui.bizwhatsapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.olabode.wilson.statussaver.ui.bizwhatsapp.images.BizImagesFragment
import com.olabode.wilson.statussaver.ui.bizwhatsapp.saved.BizSavedFragment
import com.olabode.wilson.statussaver.ui.bizwhatsapp.videos.BizVideosFragment

/**
 *   Created by OLABODE WILSON on 2020-01-13.
 */

@Suppress("DEPRECATION")
class BizPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val titles = listOf("Images", "Videos", "Saved")

    private val COUNT = 3


    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = BizImagesFragment()
            1 -> fragment = BizVideosFragment()
            2 -> fragment = BizSavedFragment()

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
