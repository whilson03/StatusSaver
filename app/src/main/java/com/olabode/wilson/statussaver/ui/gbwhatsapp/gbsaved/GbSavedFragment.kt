package com.olabode.wilson.statussaver.ui.gbwhatsapp.gbsaved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.olabode.wilson.statussaver.R

class GbSavedFragment : Fragment() {

    companion object {
        fun newInstance() = GbSavedFragment()
    }

    private lateinit var viewModel: GbSavedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.saved_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GbSavedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
