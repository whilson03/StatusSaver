package com.olabode.wilson.statussaver.ui


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
    private val STORAGE_REQUEST_CODE: Int = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({ navigate() }, 2000)

        setupPermissions()
    }

    private fun navigate() {
        this.findNavController().navigate(R.id.action_splashFragment_to_whatsAppFragment)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            STORAGE_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    activity?.finish()
                    Log.i("MAin", "Permission has been denied by user")
                } else {
                    Log.i("Main", "Permission has been granted by user")
                    navigate()
                }
            }
        }
    }


    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            context!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE

                ) && ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE

                )

            ) {
                val builder = AlertDialog.Builder(context!!)
                builder.setMessage("Permission to access the storage is required")
                    .setTitle("Permission required")

                builder.setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    makeRequest()
                }

                val dialog = builder.create()
                dialog.show()
            } else {
                makeRequest()
            }
        }
    }


    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            STORAGE_REQUEST_CODE
        )

    }

}
