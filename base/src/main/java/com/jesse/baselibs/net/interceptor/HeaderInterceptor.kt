package com.jesse.baselibs.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.HashMap


/**
 * @Description:请求头拦截器
 * @Author: Jesse
 * @Date: 2019-08-21 17:41
 */
class HeaderInterceptor(private val headers: HashMap<String, Any>?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        //如果公共请求头不为空,则构建新的请求
        if (headers != null) {
            for ((key, value) in headers) {
                requestBuilder.addHeader(key, value.toString())
            }
        }
        return chain.proceed(requestBuilder.build())
    }
}