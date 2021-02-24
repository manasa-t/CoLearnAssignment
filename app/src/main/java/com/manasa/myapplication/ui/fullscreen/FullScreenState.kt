package com.manasa.myapplication.ui.fullscreen

import android.graphics.Bitmap
import com.manasa.myapplication.domain.entities.Photo


data class FullScreenState (val success: Bitmap?, val error: String?, val isLoading: Boolean = false)