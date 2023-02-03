package com.example.data.base

import com.example.data.R
import com.example.domain.assistant.Resource
import com.example.data.network.cloud.base.ResourceProvider
import com.example.domain.base.BaseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseApiResponse(private val resourceProvider: ResourceProvider) {

    suspend fun <T, K> safeApiMapperCall(
        mapper: BaseMapper<T, K>,
        apiCall: suspend () -> Response<T>,
    ): Resource<K> {
        try {
            val response = withContext(Dispatchers.IO) { apiCall() }
            if (response.isSuccessful) {
                val body = withContext(Dispatchers.Default) { response.body() }
                body?.let { return Resource.success(data = mapper.map(body)) }
            }
            return when {
                response.code() == 400 -> Resource.error(message = resourceProvider.getString(R.string.network_error))
                response.code() == 404 -> Resource.error(message = resourceProvider.getString(R.string.generic_error1))
                response.code() == 209 -> Resource.empty()
                else -> Resource.error(message = response.message())
            }
        } catch (exception: Exception) {
            return Resource.error(message = resourceProvider.handlerException(exception = exception))
        }
    }

    suspend fun <T, K> safeApiMapperListCall(
        mapper: BaseMapper<T, K>,
        apiCall: suspend () -> Response<List<T>>,
    ): Resource<List<K>> {
        try {
            val response = withContext(Dispatchers.IO) { apiCall() }
            if (response.isSuccessful) {
                val body = withContext(Dispatchers.Default) { response.body() }
                body?.let { return Resource.success(data = body.map { mapper.map(it) }) }
            }
            return when {
                response.code() == 400 -> Resource.error(message = resourceProvider.getString(R.string.account_already1))
                response.code() == 404 -> Resource.error(message = resourceProvider.getString(R.string.invalid_email_password1))
                else -> Resource.error(message = response.message())
            }
        } catch (exception: Exception) {
            return Resource.error(message = resourceProvider.handlerException(exception = exception))
        }
    }

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>,
    ): Resource<T> {
        try {
            val response = withContext(Dispatchers.IO) { apiCall() }
            if (response.isSuccessful) {
                val body = withContext(Dispatchers.Default) { response.body() }
                body?.let { return Resource.success(data = body) }
            }
            return Resource.error(message = response.message())

        } catch (exception: Exception) {
            return Resource.error(message = resourceProvider.handlerException(exception = exception))
        }
    }
}