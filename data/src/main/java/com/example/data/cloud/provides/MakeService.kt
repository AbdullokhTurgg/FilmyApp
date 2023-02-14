package com.example.data.cloud.provides

interface MakeService {
    fun <T> service(clasz: Class<T>): T
}