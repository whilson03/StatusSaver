package com.olabode.wilson.statussaver.ui.whatsapp.saved

import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olabode.wilson.statussaver.model.Status
import com.olabode.wilson.statussaver.utils.Utils
import kotlinx.coroutines.*
import java.io.File

class SavedViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _listImages = MutableLiveData<List<Status>>()
    val listImages: LiveData<List<Status>>
        get() = _listImages


    init {
        uiScope.launch {
            loadImages()
        }
    }

    private suspend fun loadImages() {
        val list = mutableListOf<Status>()
        val location =
            File(Environment.getExternalStorageDirectory().toString() + Utils.WHATSAPP_DIR)

        val files: Array<File>? = location.listFiles()

        files?.let {
            withContext(Dispatchers.IO) {
                for (file in files) {
                    if (file.name.endsWith(".jpg") || file.name.endsWith(".mp4")) {
                        val image =
                            Status(
                                file.name,
                                file.absolutePath
                            )
                        if (!list.contains(image)) {
                            list.add(image)
                            _listImages.postValue(list)
                        }
                        // _listImages.postValue(list)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
