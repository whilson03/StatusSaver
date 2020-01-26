package com.olabode.wilson.statussaver.ui.viewers.imagesviewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olabode.wilson.statussaver.ui.model.Status

class ImagesViewerViewModel(status: Status) : ViewModel() {
    private val _image = MutableLiveData<Status>()
    val currentImage: LiveData<Status>
        get() = _image

    init {
        _image.value = status
    }

    override fun onCleared() {
        super.onCleared()
        _image.value = null
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
