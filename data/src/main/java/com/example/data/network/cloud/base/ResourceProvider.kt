package com.example.data.network.cloud.base

import android.content.Context
import androidx.annotation.StringRes
import com.example.data.R
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface ResourceProvider {

    fun getString(@StringRes id: Int): String
    fun handlerException(exception: Exception): String
    fun handleException(throwable: Throwable): String
    class Base(private val context: Context) : ResourceProvider {
        override fun getString(id: Int): String = context.getString(id)

        override fun handlerException(exception: Exception): String {
            return when (exception) {
                is UnknownHostException -> getString(R.string.network_error)
                is SocketTimeoutException -> getString(R.string.network_error)
                is ConnectException -> getString(R.string.network_error)
                is HttpException -> getString(R.string.service_unavailable)
                else -> getString(R.string.generic_error)
            }
        }

        override fun handleException(throwable: Throwable): String {
            return when (throwable) {
                is UnknownHostException -> getString(R.string.network_error)
                is SocketTimeoutException -> getString(R.string.network_error)
                is ConnectException -> getString(R.string.network_error)
                is HttpException -> getString(R.string.service_unavailable)
                else -> getString(R.string.generic_error)
            }
        }
    }
}