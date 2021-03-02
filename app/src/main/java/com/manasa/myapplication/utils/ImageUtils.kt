package com.manasa.myapplication.utils

class ImageUtils {
companion object {

    fun calcLuminance(rgb: Int): Float {
        val r = rgb and 0xff0000 shr 16
        val g = rgb and 0xff00 shr 8
        val b = rgb and 0xff
        return (r * 0.299f + g * 0.587f + b * 0.114f) / 256
    }

}
}