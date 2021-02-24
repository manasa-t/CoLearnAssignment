package com.manasa.myapplication.ui.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.manasa.myapplication.data.repositoryimpl.PhotoRepositoryImpl
import com.manasa.myapplication.domain.entities.Photo
import com.manasa.myapplication.ui.search.SearchFilters

class SearchDataSourceFactory(val searchFilters: SearchFilters): DataSource.Factory<Int, Photo>() {

    var searchDataSourceLiveData = MutableLiveData<SearchDataSource>()
    val photosRepository = PhotoRepositoryImpl()
    lateinit var searchDataSource: SearchDataSource

    override fun create(): DataSource<Int, Photo> {
         searchDataSource = SearchDataSource(searchFilters,photosRepository)
        searchDataSourceLiveData.postValue(searchDataSource)
        return searchDataSource
    }

    fun setSearchFilters(searchFilters: SearchFilters){

         searchDataSource = SearchDataSource(searchFilters,photosRepository)
        searchDataSourceLiveData.postValue(searchDataSource)
         searchDataSourceLiveData.value?.invalidate()
    }
}