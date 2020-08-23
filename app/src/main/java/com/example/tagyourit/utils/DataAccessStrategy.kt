package com.example.tagyourit.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.tagyourit.utils.Resource.Status.*
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(
    databaseQuery: (() -> LiveData<T>?)? = null,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: (suspend (A) -> Unit?)? = null): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery?.invoke()?.map { Resource.success(it) }
        if (source != null) {
            emitSource(source)
        }

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            if (saveCallResult != null) {
                saveCallResult(responseStatus.data!!)
            }

        } else if (responseStatus.status == ERROR) {
            emit(Resource.error(responseStatus.message!!))
            if (source != null) {
                emitSource(source)
            }
        }
    }