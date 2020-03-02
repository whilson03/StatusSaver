package com.olabode.wilson.statussaver.ui.viewers.imagesviewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olabode.wilson.statussaver.model.Status
import com.olabode.wilson.statussaver.utils.StatusType
import com.olabode.wilson.statussaver.utils.Utils
import kotlinx.coroutines.*
import java.io.FileNotFoundException

class ImagesViewerViewModel(
    status: Status,
    statusType: StatusType
) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _image = MutableLiveData<Status>()
    val currentImage: LiveData<Status>
        get() = _image
    private lateinit var _statusType: StatusType

    init {
        _image.value = status
        _statusType = statusType
        println(_statusType)
    }



    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error


    fun save() {
        uiScope.launch {
            withContext(Dispatchers.IO) {

                try {
                    Utils.saveFilestoDirectory(currentImage.value!!.path, _statusType)
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
        _image.value = null
        resetErrorMessage()
        viewModelJob.cancel()
    }
}


class ImageViewerFactory(
    private val status: Status, val statusType: StatusType
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImagesViewerViewModel::class.java)) {
            return ImagesViewerViewModel(status, statusType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
