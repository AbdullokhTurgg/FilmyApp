package com.example.data.network.cloud.provides

interface MakeService {
    fun <T> service(clasz: Class<T>): T
}