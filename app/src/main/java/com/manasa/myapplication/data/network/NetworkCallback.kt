package com.manasa.myapplication.data.network

interface NetworkCallback<T: Any> {

    fun onSuccess(response: T)

    fun onError(errorCode: Int, errorMessage: String)
}