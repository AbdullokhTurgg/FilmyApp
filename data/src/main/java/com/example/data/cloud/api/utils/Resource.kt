package com.example.data.cloud.api.utils

import com.google.assistant.appactions.suggestions.client.ServiceException


sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(val serviceException: ServiceException) : Resource<Nothing>()
}
