package com.manasa.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.manasa.myapplication.data.repositoryimpl.PhotoRepositoryImpl
import com.manasa.myapplication.domain.entities.CollectionPhotoResult
import com.manasa.myapplication.domain.entities.Photo
import com.manasa.myapplication.domain.repository.PhotosRepository
import com.manasa.myapplication.ui.paging.PhotosDataSourceFactory
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var photosList: LiveData<PagedList<Photo>>? = null
   var photoDataSourceFactory: PhotosDataSourceFactory = PhotosDataSourceFactory()

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()
        photosList = LivePagedListBuilder<Int,Photo>(photoDataSourceFactory,config).build()

       // loadPhotos(1, 20)
    }


}