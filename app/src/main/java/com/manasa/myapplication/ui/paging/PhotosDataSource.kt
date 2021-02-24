package com.manasa.myapplication.ui.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.manasa.myapplication.domain.entities.Photo
import com.manasa.myapplication.domain.repository.PhotosRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PhotosDataSource(val photosRepository: PhotosRepository): PageKeyedDataSource<Int, Photo>() {

    val COLLECTION_ID = 2423569
    var total = 0;
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {

            var result = photosRepository.getCollectionPhotos(
                COLLECTION_ID,
                1,
                params.requestedLoadSize,
                null
            )
           total = result.total ?: 0
            Log.i("PhotoDataSource ","Thread info "+Thread.currentThread())
            callback.onResult(result.photos ?: emptyList(),null,2)


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {

                var result = photosRepository.getCollectionPhotos(
                    COLLECTION_ID,
                    params.key,
                    params.requestedLoadSize,
                    null
                )
                callback.onResult(result.photos ?: emptyList(), params.key + 1)


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {

    }

}