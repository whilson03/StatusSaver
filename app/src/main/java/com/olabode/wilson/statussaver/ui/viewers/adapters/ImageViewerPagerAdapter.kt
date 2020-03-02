package com.olabode.wilson.statussaver.ui.viewers.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.olabode.wilson.statussaver.model.Status
import com.olabode.wilson.statussaver.ui.viewers.imagesviewer.ImagesViewerFragment

/**
 *   Created by OLABODE WILSON on 2020-01-17.
 */


@Suppress("DEPRECATION")
class ImageViewerPagerAdapter internal constructor(
    fm: FragmentManager,
    private val statusList: List<Status>,
    private val statusType: String
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null

        while (position < statusList.size) {
            return ImagesViewerFragment.newInstance(statusList[position], statusType)
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return statusList.size
    }
}
