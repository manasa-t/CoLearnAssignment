package com.manasa.myapplication.mappers

import SearchPhotoResponse
import com.manasa.myapplication.data.entities.networkentities.CollectionPhotosResponse
import com.manasa.myapplication.data.entities.networkentities.PhotoResponse
import com.manasa.myapplication.domain.entities.CollectionPhotoResult
import com.manasa.myapplication.domain.entities.Photo
import com.manasa.myapplication.domain.entities.SearchPhotoResult

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


    }
}