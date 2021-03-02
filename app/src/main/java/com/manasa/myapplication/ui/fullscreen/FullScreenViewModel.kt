package com.manasa.myapplication.ui.fullscreen

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manasa.myapplication.data.network.NetworkHelper
import com.manasa.myapplication.data.repositoryimpl.PhotoRepositoryImpl
import com.manasa.myapplication.domain.entities.PhotoStatsResult
import com.manasa.myapplication.domain.repository.PhotosRepository
import com.manasa.myapplication.ui.App
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FullScreenViewModel() : ViewModel() {

    var _fullScreenData = MutableLiveData<FullScreenState>()
    var fullScreenData: LiveData<FullScreenState> = _fullScreenData

    var _photosStatsData = MutableLiveData<PhotoStats>()
    var photosStatsData : LiveData<PhotoStats> = _photosStatsData

    var photosRepository: PhotosRepository = PhotoRepositoryImpl()


    init {
        _fullScreenData.postValue(FullScreenState(null, null, true))

    }

    fun getPhoto(id: String) {
        viewModelScope.launch {
            var bitmap: Bitmap? = null
            var result = photosRepository.getPhoto(id)
            if (result != null) {
                bitmap = NetworkHelper.getImageBitmap(result!!.fullUrl)
                _fullScreenData.postValue(FullScreenState(
                    success = bitmap!!,
                    error = null,
                    isLoading = false
                ))
            } else {
                _fullScreenData.postValue(FullScreenState(null, "Error getting image! ", false))
            }
        }
    }

    fun getPhotoStats(id: String){
        viewModelScope.launch {
            var result = photosRepository.getPhotoStats(id,null,null)

            if(result!=null){
                var stats = PhotoStats(Stats(result.downloadsValue.map { it ->
                    ChartValue(mapDate(it.dateString),it.count) },
                    result.likesValue.map { it -> ChartValue(mapDate(it.dateString) , it.count)},
                    result.viewsValue.map { it -> ChartValue(mapDate(it.dateString), it.count) }),null,false)

                Log.d("Full view model"," downloads value "+stats.success?.downloads?.get(0)?.date+" , "+
                        stats.success?.downloads?.get(0)?.count)
              _photosStatsData.postValue(stats)

            }else{
                _photosStatsData.postValue(PhotoStats(null,"Error getting stats", false))
            }
        }
    }

    fun mapDate(dateString: String): Date{
        var format = SimpleDateFormat("yyyy-MM-dd")
        return format.parse(dateString)
    }

    fun mapDateToString(date: Date): String{
        var format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }




}