package com.manasa.myapplication.data.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception

import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.max

class NetworkHelper {

    companion object{

        fun getNetworkData(reqeuestUrl: String, method: String): NetworkResponse {
            var networkResponse = NetworkResponse();
                var reader: BufferedReader? = null
                try {
                    var url = URL(reqeuestUrl)
                    var httpConnection = url.openConnection() as HttpURLConnection
                    httpConnection.requestMethod = method
                    var stringBuilder = StringBuilder()
                     reader = BufferedReader(InputStreamReader(httpConnection.inputStream))
                    var line = reader.readLine()
                    while (line != null) {
                        stringBuilder.append(line + "\n")
                        line = reader.readLine()
                    }
                    networkResponse.responseCode = httpConnection.responseCode
                    if(httpConnection.responseCode == 200){
                        var total = httpConnection.getHeaderField("X-Total")
                        Log.i("Network Helper","total "+total)
                        networkResponse.total = if(total!=null)Integer.parseInt(total)else 0
                    }
                    networkResponse.stringResponse = stringBuilder.toString()
                    return networkResponse
                } catch (e:Exception){
                    e.printStackTrace()
                  return networkResponse
                }
            finally {
                if(reader!=null){
                    try {
                        reader.close()
                    }catch (e:Exception){
                        e.printStackTrace()
                        return networkResponse
                    }
                }
            }
        }

       suspend fun getImageBitmap(imageUrl: String): Bitmap? {
           if(imageUrl.isNullOrEmpty()) return null
           return withContext(Dispatchers.IO) {
               var url = URL(imageUrl)
               var httpURLConnection = url.openConnection()
               var inputStream = httpURLConnection.getInputStream()
                BitmapFactory.decodeStream(inputStream)
           }

        }

    }


}