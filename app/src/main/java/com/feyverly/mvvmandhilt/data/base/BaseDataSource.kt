package com.feyverly.mvvmandhilt.data.base

import com.feyverly.mvvmandhilt.utils.Resource
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {
    protected suspend fun <T> getResults(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call.invoke()
            if(response.isSuccessful){
                val body = response.body()
                if(body != null) return Resource.success(body)
            }
            return error(response.message())
        } catch (e: Exception) {
            return error(e.message ?: "error")
        }
    }

    private fun <T> error(message: String): Resource<T> =
        Resource.error("network error !! : $message")
}

