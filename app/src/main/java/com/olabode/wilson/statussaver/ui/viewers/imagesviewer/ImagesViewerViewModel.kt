package com.olabode.wilson.statussaver.ui.viewers.imagesviewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olabode.wilson.statussaver.ui.Utils
import com.olabode.wilson.statussaver.ui.model.Status
import kotlinx.coroutines.*
import java.io.FileNotFoundException

class ImagesViewerViewModel(status: Status) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _image = MutableLiveData<Status>()
    val currentImage: LiveData<Status>
        get() = _image

    init {
        _image.value = status
    }


    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error


    fun save() {
        uiScope.launch {
            withContext(Dispatchers.IO) {

                try {
                    Utils.saveFilestoDirectory(currentImage.value!!.path, Utils.WHATSAPP_SAVE_DIR)
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
    private val status: Status
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImagesViewerViewModel::class.java)) {
            return ImagesViewerViewModel(status) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
