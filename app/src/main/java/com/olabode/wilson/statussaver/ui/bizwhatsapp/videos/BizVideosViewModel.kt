package com.olabode.wilson.statussaver.ui.bizwhatsapp.videos

import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olabode.wilson.statussaver.model.Status
import com.olabode.wilson.statussaver.utils.Utils
import kotlinx.coroutines.*
import java.io.File

class BizVideosViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _listVids = MutableLiveData<List<Status>>()
    val listVideo: LiveData<List<Status>>
        get() = _listVids


    init {
        uiScope.launch {
            loadVideos()
        }
    }

    private suspend fun loadVideos() {
        val list = mutableListOf<Status>()
        val location =
            File(Environment.getExternalStorageDirectory().toString() + Utils.WHATSAPP_DIR)

        val files: Array<File>? = location.listFiles()

        files?.let {
            withContext(Dispatchers.Default) {
                for (file in files) {
                    if (file.name.endsWith(".mp4") || file.name.endsWith(".gif")) {
                        val video =
                            Status(
                                file.name,
                                file.absolutePath
                            )
                        if (!list.contains(video)) {
                            list.add(video)
                        }
                    }

                }
                _listVids.postValue(list)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
