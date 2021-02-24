package com.manasa.myapplication.ui.fullscreen

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manasa.myapplication.data.network.NetworkHelper
import com.manasa.myapplication.data.repositoryimpl.PhotoRepositoryImpl
import com.manasa.myapplication.domain.repository.PhotosRepository
import com.manasa.myapplication.ui.App
import kotlinx.coroutines.launch

class FullScreenViewModel() : ViewModel() {

    var _fullScreenData = MutableLiveData<FullScreenState>()
    var fullScreenData: LiveData<FullScreenState> = _fullScreenData


    var photosRepository: PhotosRepository = PhotoRepositoryImpl()


    init {
        _fullScreenData.postValue(FullScreenState(null, null, true))

    }

    fun getPhoto(id: String) {
        viewModelScope.launch {
            var bitmap: Bitmap? = null
            var result = photosRepository.getPhoto(id)
            if (result != null) {
                bitmap = NetworkHelper.getImageBitmap(result!!.fullUrl)
                _fullScreenData.postValue(FullScreenState(
                    success = bitmap!!,
                    error = null,
                    isLoading = false
                ))
            } else {
                _fullScreenData.postValue(FullScreenState(null, "Error getting image! ", false))
            }
        }
    }
}