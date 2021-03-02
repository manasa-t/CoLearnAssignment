package com.manasa.myapplication.ui.fullscreen

import android.graphics.Bitmap
import com.manasa.myapplication.domain.entities.Photo
import com.manasa.myapplication.domain.entities.PhotoStatsResult
import java.util.*


data class FullScreenState (val success: Bitmap?, val error: String?, val isLoading: Boolean = false)

data class PhotoStats(val success: Stats?, val error: String?, val isLoading: Boolean = false)

data class ChartValue(val date: Date, val count: Int)

data class Stats(val likes: List<ChartValue>, val downloads: List<ChartValue>, val views: List<ChartValue>)