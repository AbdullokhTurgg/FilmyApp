package com.example.data.network.cloud.provides

import com.example.data.network.retrofit.utils.Utils

class MakeServiceImpl(
    private val retrofitBuilder: ProvideRetrofitBuilder,
) : MakeService {

    private val retrofit by lazy(LazyThreadSafetyMode.NONE) {
        retrofitBuilder
            .provideRetrofitBuilder()
            .baseUrl(Utils.BASE_URL)
            .build()
    }

    override fun <T> service(clasz: Class<T>): T = retrofit.create(clasz)
}