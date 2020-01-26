package com.olabode.wilson.statussaver.ui.viewers


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.olabode.wilson.statussaver.databinding.FragmentViewerBinding
import com.olabode.wilson.statussaver.ui.model.Status
import com.olabode.wilson.statussaver.ui.viewers.adapters.ImageViewerPagerAdapter

/**f
 * A simple [Fragment] subclass.
 */
class ViewFragment : Fragment() {

    private lateinit var binding: FragmentViewerBinding
    private var mCurrentItem = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val statusList: Array<Status> = ViewFragmentArgs.fromBundle(arguments!!).statusList
        val positionSelected = ViewFragmentArgs.fromBundle(arguments!!).position

        binding = FragmentViewerBinding.inflate(inflater)

        val viewPager = binding.viewPager
        val adapter = ImageViewerPagerAdapter(childFragmentManager, statusList.asList())
        mCurrentItem = positionSelected
        viewPager.adapter = adapter
        viewPager.currentItem = positionSelected
        return binding.root
    }


}
