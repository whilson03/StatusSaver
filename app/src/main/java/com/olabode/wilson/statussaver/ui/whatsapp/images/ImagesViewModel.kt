package com.olabode.wilson.statussaver.ui.whatsapp.images

import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olabode.wilson.statussaver.ui.Utils
import com.olabode.wilson.statussaver.ui.model.Status
import kotlinx.coroutines.*
import java.io.File

@Suppress("DEPRECATION")
class ImagesViewModel : ViewModel() {


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
            withContext(Dispatchers.Default) {
                for (file in files) {
                    if (file.name.endsWith(".jpg")) {
                        val image =
                            Status(
                                file.name,
                                file.absolutePath
                            )
                        if (!list.contains(image)) {
                            list.add(image)

                        }
                        _listImages.postValue(list)
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

