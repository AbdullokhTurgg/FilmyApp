package com.example.data.network.cloud.handler

import com.example.domain.base.BaseMapper
import com.example.domain.state.DataRequestState
import retrofit2.Response

interface ResponseHandler {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): DataRequestState<T>
    suspend fun <T, K> safeApiMapperCall(
        mapper: BaseMapper<T, K>,
        apiCall: suspend () -> Response<T>,
    ): DataRequestState<K>
}