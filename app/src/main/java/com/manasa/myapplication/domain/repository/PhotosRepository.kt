package com.manasa.myapplication.domain.repository

import SearchPhotoResponse
import androidx.lifecycle.LiveData
import com.manasa.myapplication.data.entities.networkentities.CollectionPhotosResponse
import com.manasa.myapplication.data.entities.networkentities.PhotoResponse
import com.manasa.myapplication.data.network.NetworkCallback
import com.manasa.myapplication.domain.entities.CollectionPhotoResult
import com.manasa.myapplication.domain.entities.Photo
import com.manasa.myapplication.domain.entities.SearchPhotoResult
import com.manasa.myapplication.ui.search.SearchFilters

interface PhotosRepository  {

    // for this id 2423569
     fun getCollectionPhotos( id: Int, page: Int,  per_page: Int, orientation: String? ): CollectionPhotoResult

    fun searchPhotos(searchFilters: SearchFilters, page:Int, per_page:Int): SearchPhotoResult

    suspend fun getPhoto(id:String) : Photo?
}