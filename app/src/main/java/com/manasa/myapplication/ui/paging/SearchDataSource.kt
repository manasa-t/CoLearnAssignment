package com.manasa.myapplication.ui.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.manasa.myapplication.domain.entities.Photo
import com.manasa.myapplication.domain.repository.PhotosRepository
import com.manasa.myapplication.ui.search.SearchFilters

class SearchDataSource(val searchFilters: SearchFilters, val photosRepository: PhotosRepository): PageKeyedDataSource<Int, Photo>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {

        var result = photosRepository.searchPhotos(searchFilters, 1,params.requestedLoadSize)

        callback.onResult(result.photos ?: emptyList(),null,2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        var result = photosRepository.searchPhotos(searchFilters, params.key,
            params.requestedLoadSize)
        callback.onResult(result.photos ?: emptyList(), params.key+1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {

    }
}