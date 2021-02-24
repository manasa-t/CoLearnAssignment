package com.manasa.myapplication.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.manasa.myapplication.data.repositoryimpl.PhotoRepositoryImpl
import com.manasa.myapplication.domain.entities.Photo

class PhotosDataSourceFactory: DataSource.Factory<Int, Photo>() {

    val photosDataSourceLiveData = MutableLiveData<PhotosDataSource>()
    val photosRepository = PhotoRepositoryImpl()

    override fun create(): DataSource<Int, Photo> {
        val photosDataSource = PhotosDataSource(photosRepository)
        photosDataSourceLiveData.postValue(photosDataSource)
        return photosDataSource
    }

}