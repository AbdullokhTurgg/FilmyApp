package com.example.movieappazi.exception

import retrofit2.HttpException

interface HandleExeption {
    fun hanEx(e: Exception): String
    fun hanEx(tr: Throwable): String

    class Base : HandleExeption {
        override fun hanEx(e: Exception): String {
            return when (e) {
                is HttpException -> "Http not work"
                else -> "I do not know"
            }
        }

        override fun hanEx(tr: Throwable): String {
            return when (tr) {
                is NotImplementedError -> "Something went sworng"
                else -> "I do not know"
            }
        }

    }
}