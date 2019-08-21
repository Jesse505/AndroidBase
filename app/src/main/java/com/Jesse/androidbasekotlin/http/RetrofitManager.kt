package com.Jesse.androidbasekotlin.http

import com.jesse.baselibs.net.BaseRetrofitManager

/**
 * @Description:
 * @Author: Jesse
 * @Date: 2019-08-21 16:25
 */
object RetrofitManager : BaseRetrofitManager() {

    val service: ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        getRetrofit().create(ApiService::class.java)
    }

    override fun getBaseUrl(): String {
        return ""
    }

}