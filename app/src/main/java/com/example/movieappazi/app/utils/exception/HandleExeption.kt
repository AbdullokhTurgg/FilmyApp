package com.example.movieappazi.app.utils.exception

import android.content.Context
import androidx.annotation.StringRes
import com.example.movieappazi.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface HandleExeption {

    fun getString(@StringRes id: Int): String
    fun hanEx(e: Exception): String
    fun hanEx(tr: Throwable): String

    class Base(private val context: Context) : HandleExeption {
        override fun getString(id: Int): String = context.getString(id)

        override fun hanEx(e: Exception): String {
            return when (e) {
                is UnknownHostException -> getString(R.string.network_error)
                is SocketTimeoutException -> getString(R.string.network_error)
                is ConnectException -> getString(R.string.network_error)
                is HttpException -> getString(R.string.service_unavailable)
                else -> getString(R.string.generic_error)
            }
        }

        override fun hanEx(tr: Throwable): String {
            return when (tr) {
                is UnknownHostException -> getString(R.string.network_error)
                is SocketTimeoutException -> getString(R.string.network_error)
                is ConnectException -> getString(R.string.network_error)
                is HttpException -> getString(R.string.service_unavailable)
                else -> getString(R.string.generic_error)
            }
        }

    }
}