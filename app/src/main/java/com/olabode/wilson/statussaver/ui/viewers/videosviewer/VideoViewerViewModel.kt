package com.olabode.wilson.statussaver.ui.viewers.videosviewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olabode.wilson.statussaver.ui.StatusType
import com.olabode.wilson.statussaver.ui.Utils
import com.olabode.wilson.statussaver.ui.model.Status
import kotlinx.coroutines.*
import java.io.FileNotFoundException

/**
 *   Created by OLABODE WILSON on 2020-02-20.
 */
class VideoViewerViewModel(
    status: Status,
    statusType: StatusType
) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _video = MutableLiveData<Status>()
    val currentVideo: LiveData<Status>
        get() = _video

    private lateinit var _statusType: StatusType


    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    init {
        _video.value = status
        _statusType = statusType
        println(_statusType)
    }

    fun save() {
        uiScope.launch {
            withContext(Dispatchers.IO) {

                try {
                    Utils.saveFilestoDirectory(currentVideo.value!!.path, _statusType)
                } catch (e: FileAlreadyExistsException) {
                    _error.postValue("File Already Exist")
                    return@withContext

                } catch (e: NoSuchFileException) {
                    _error.postValue("File does not exist")
                    return@withContext

                } catch (e: FileNotFoundException) {
                    _error.postValue("Cannot locate file")
                    return@withContext
                }

            }

        }

    }


    fun resetErrorMessage() {
        _error.value = null
    }

    override fun onCleared() {
        super.onCleared()
        _video.value = null
        viewModelJob.cancel()
        resetErrorMessage()
    }

}


class VideoViewerFactory(
    private val status: Status, val statusType: StatusType
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoViewerViewModel::class.java)) {
            return VideoViewerViewModel(status, statusType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
