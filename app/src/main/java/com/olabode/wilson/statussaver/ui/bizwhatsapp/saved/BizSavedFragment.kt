package com.olabode.wilson.statussaver.ui.bizwhatsapp.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.olabode.wilson.statussaver.R

class BizSavedFragment : Fragment() {

    companion object {
        fun newInstance() = BizSavedFragment()
    }

    private lateinit var viewModel: BizSavedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.saved_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BizSavedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
