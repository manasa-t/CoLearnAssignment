package com.manasa.myapplication.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.manasa.myapplication.domain.entities.Photo
import com.manasa.myapplication.ui.paging.PhotosDataSourceFactory
import com.manasa.myapplication.ui.paging.SearchDataSourceFactory

class FilterViewModel: ViewModel() {

    var photosList: LiveData<PagedList<Photo>>? = null
    lateinit var searchDataSourceFactory: SearchDataSourceFactory

    var searchFilters: SearchFilters? = null;

    fun setFilters(searchFilters: SearchFilters) {
        this.searchFilters = searchFilters
        searchDataSourceFactory = SearchDataSourceFactory(searchFilters)
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(22)
            .setEnablePlaceholders(false)
            .build()
        photosList = LivePagedListBuilder<Int, Photo>(searchDataSourceFactory, config).build()
    }

    fun refreshData(searchFilters: SearchFilters){
        this.searchFilters = searchFilters

        searchDataSourceFactory.setSearchFilters(searchFilters)
    }

    fun clearFilters(){
        searchFilters?.color = null;
        searchFilters?.orientation = null
        searchFilters?.order_by = null
        searchDataSourceFactory.setSearchFilters(searchFilters!!)
    }



}