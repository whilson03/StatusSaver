package com.olabode.wilson.statussaver.ui


import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.olabode.wilson.statussaver.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {
    private val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val version = Build.VERSION.SDK_INT
        if (version > Build.VERSION_CODES.LOLLIPOP_MR1) {


            Handler().postDelayed({ navigate() }, 2000)
        }

    }

    private fun navigate() {
        context?.let {
            findNavController().navigate(R.id.action_splashFragment_to_whatsAppFragment)
        }
    }


}
