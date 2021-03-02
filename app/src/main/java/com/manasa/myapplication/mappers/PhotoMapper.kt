package com.manasa.myapplication.mappers

import PhotoStatsResponse
import SearchPhotoResponse
import android.util.Log
import com.manasa.myapplication.data.entities.networkentities.CollectionPhotosResponse
import com.manasa.myapplication.data.entities.networkentities.PhotoResponse
import com.manasa.myapplication.domain.entities.*

class PhotoMapper {

    companion object {
        fun mapCollectionPhoto(response: List<CollectionPhotosResponse>, total: Int): CollectionPhotoResult {
           var photos = response.map { it ->
                Photo(
                    it.id,
                    it.urls.small,
                    it.urls.full
                )
            }.toMutableList()
           return CollectionPhotoResult(photos,total)
        }

        fun mapPhoto(response: PhotoResponse): Photo {
            return Photo(response.id,response.urls.small,response.urls.full)
        }

        fun mapSearchPhotos(response: SearchPhotoResponse): SearchPhotoResult {
           var result = response.results.map { it ->
                Photo(it.id, it.urls.small,it.urls.full)
            }.toMutableList()
            return SearchPhotoResult(result)
        }

        fun mapPhotoStats(response: PhotoStatsResponse): PhotoStatsResult {
            Log.i("Mapper","downloads value "+response.downloads.historical.values.get(2).value)
            var result = PhotoStatsResult(response.downloads.historical.values.map { it ->

                Value(it.date, it.value) }.toMutableList() as ArrayList<Value>,
            response.likes.historical.values.map { it -> Value(it.date, it.value) }.toMutableList() as ArrayList<Value>,
            response.views.historical.values.map { it -> Value(it.date,it.value) }.toMutableList() as ArrayList<Value>)
            Log.d("Mapper ","downloads count size "+result.downloadsValue.size)
            Log.d("Mapper ","likes count size "+result.likesValue.size)
            Log.d("Mapper ","views count size "+result.viewsValue.size)
            return result
        }




    }
}