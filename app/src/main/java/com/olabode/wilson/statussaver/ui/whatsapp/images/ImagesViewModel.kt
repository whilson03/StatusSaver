package com.olabode.wilson.statussaver.ui.whatsapp.images

import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olabode.wilson.statussaver.model.Status
import com.olabode.wilson.statussaver.utils.Utils
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

        val files: List<File>? = location.listFiles()?.filter { file ->
            file.name.endsWith(".jpg")
        }

        files?.let {
            withContext(Dispatchers.IO) {
                files.forEach { file ->
                    val video =
                        Status(
                            file.name,
                            file.absolutePath
                        )
                    if (!list.contains(video)) {
                        list.add(video)
                        _listImages.postValue(list)
                    }


                }
                //_listVids.postValue(list)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

