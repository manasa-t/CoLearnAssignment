package com.manasa.myapplication.data.repositoryimpl

import PhotoStatsResponse
import SearchPhotoResponse
import android.util.Log
import com.google.gson.Gson
import com.manasa.myapplication.data.entities.networkentities.CollectionPhotosResponse
import com.manasa.myapplication.data.entities.networkentities.PhotoResponse
import com.manasa.myapplication.data.network.NetworkHelper
import com.manasa.myapplication.data.network.NetworkResponse
import com.manasa.myapplication.domain.entities.CollectionPhotoResult
import com.manasa.myapplication.domain.entities.Photo
import com.manasa.myapplication.domain.entities.PhotoStatsResult
import com.manasa.myapplication.domain.entities.SearchPhotoResult
import com.manasa.myapplication.domain.repository.PhotosRepository
import com.manasa.myapplication.mappers.PhotoMapper
import com.manasa.myapplication.ui.App
import com.manasa.myapplication.ui.search.SearchFilters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.*


class PhotoRepositoryImpl : PhotosRepository {

    val baseUrl: String = "https://api.unsplash.com/"
    val apiKey: String = "client_id=FZpIl7feLseFHwV4DScQqiaVULO54C7GRBiqlmDrxdI"
    val gson = Gson()

    override fun getCollectionPhotos(
        id: Int,
        page: Int,
        per_page: Int,
        orientation: String?

    ): CollectionPhotoResult {

        var requestUrl =
            baseUrl + "collections/" + id + "/photos?" + "page=" + page + "&per_page=" + per_page + "&" + apiKey
        Log.d("network", "request url formed $requestUrl")
        var stringResponse: String?
        var collectionPhotoResult: CollectionPhotoResult?
        var networkResponse : NetworkResponse?

      /*  if(!memoryResponse.isNullOrEmpty()) stringResponse = memoryResponse
        else if(fileResponse!=null)stringResponse = getStringFromFile(fileResponse)
        else {*/
             networkResponse = NetworkHelper.getNetworkData(requestUrl, "GET")
            stringResponse = networkResponse.stringResponse

       // }
        Log.d("network", "string response $stringResponse")
        if (!stringResponse.isNullOrEmpty()) {


            var collectionPhotosResponse =
                gson.fromJson(stringResponse, Array<CollectionPhotosResponse>::class.java).asList()
            Log.d(
                "network",
                "formed object " + collectionPhotosResponse.size
            )
            collectionPhotoResult = PhotoMapper.mapCollectionPhoto(collectionPhotosResponse,networkResponse.total)
        } else {
            collectionPhotoResult = CollectionPhotoResult(emptyList(),0)
        }
        return collectionPhotoResult

        //callback.onSuccess(collectionPhotoResult)
    }


    override fun searchPhotos(
        searchFilters: SearchFilters,
        page: Int,
        per_page: Int
    ): SearchPhotoResult {
        var params = ""
        if(searchFilters.color!=null)params+="&color="+searchFilters.color
        if(searchFilters.order_by!=null)params+="&order_by="+searchFilters.order_by
        if(searchFilters.orientation!=null)params+="&orientation="+searchFilters.orientation
        var requestUrl = baseUrl + "search/photos?" + "page=" + page + "&per_page=" + per_page +
                "&query=" + searchFilters.query +params+ "&" + apiKey
        Log.d("network", "request url formed $requestUrl")
        var stringResponse: String?
        var searchPhotoResult: SearchPhotoResult?


       /* if(!memoryResponse.isNullOrEmpty()) stringResponse = memoryResponse
        else if(fileResponse!=null)stringResponse = getStringFromFile(fileResponse)
        else {*/
            var networkResponse = NetworkHelper.getNetworkData(requestUrl, "GET")
            stringResponse = networkResponse.stringResponse
      //  }
        Log.d("network", "string response $stringResponse")
        if (!stringResponse.isNullOrEmpty()) {

           // saveStringToFile(requestUrl,stringResponse)
            var searchPhotoResponse =
                gson.fromJson(stringResponse, SearchPhotoResponse::class.java)
            Log.d(
                "network",
                "formed object " + searchPhotoResponse.results.size + " id " + searchPhotoResponse.total
            )
            searchPhotoResult = PhotoMapper.mapSearchPhotos(searchPhotoResponse)
        } else {
            searchPhotoResult = SearchPhotoResult(emptyList())
        }

        return searchPhotoResult
    }

    override suspend fun getPhoto(id: String): Photo? {
       return withContext(Dispatchers.IO) {
           // var result = MutableLiveData<Photo>()

            var requestUrl = baseUrl + "photos/" + id + "?" + apiKey
           var stringResponse: String?

          /* if(!memoryResponse.isNullOrEmpty()) stringResponse = memoryResponse
           else if(fileResponse!=null)stringResponse = getStringFromFile(fileResponse)
           else {*/
               var networkResponse = NetworkHelper.getNetworkData(requestUrl, "GET")
               stringResponse = networkResponse.stringResponse
          // }
            Log.d("network", "string response $stringResponse")
            if (!stringResponse.isNullOrEmpty()) {

              //  saveStringToFile(requestUrl,stringResponse)
                var photoResponse =
                    gson.fromJson(stringResponse, PhotoResponse::class.java)
                Log.d(
                    "network",
                    "formed object " + photoResponse.id
                )
                var photo = PhotoMapper.mapPhoto(photoResponse)
                photo
            } else {
                 null
            }

        }
    }



    override suspend fun getPhotoStats(
        id: String,
        resolution: String?,
        quantity: Int?
    ): PhotoStatsResult? {
        return withContext(Dispatchers.IO) {
            var reqUrl = baseUrl + "photos/" + id + "/statistics" + "?" + apiKey
            var stringResponse: String?

            var networkResponse = NetworkHelper.getNetworkData(reqUrl, "GET")
            stringResponse = networkResponse.stringResponse
            Log.d("network", "photo stats response $stringResponse")
            if (!stringResponse.isNullOrEmpty()) {
                var photoStatsResponse =
                    gson.fromJson(stringResponse, PhotoStatsResponse::class.java)

                PhotoMapper.mapPhotoStats(photoStatsResponse)
            } else  null


        }
    }
}