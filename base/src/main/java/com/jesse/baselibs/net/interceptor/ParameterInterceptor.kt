package com.jesse.baselibs.net.interceptor

import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.HashMap


/**
 * @Description:参数拦截器，支持post get方式
 * @Author: Jesse
 * @Date: 2019-08-21 17:54
 */
class ParameterInterceptor(private val params: HashMap<String, Any>?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (params != null && params.size != 0) {
            if (request.method() == "GET") {// 为GET方式统一添加请求参数
                val modifiedUrl = request.url().newBuilder()
                    .scheme(request.url().scheme())
                    .host(request.url().host())

                if (params.size != 0) {
                    for ((key, value) in params) {
                        modifiedUrl.addQueryParameter(key, value.toString())
                    }
                }

                request = request.newBuilder()
                    .method(request.method(), request.body())
                    .url(modifiedUrl.build())
                    .build()

            } else if (request.method() == "POST") {// 为POST方式统一添加请求参数
                if (request.body() is FormBody) {
                    val body = FormBody.Builder()
                    if (params.size != 0) {
                        for ((key, value) in params) {
                            body.addEncoded(key, value.toString())
                        }
                    }
                    body.build()

                    val oldBody = request.body() as FormBody?
                    if (oldBody != null && oldBody.size() != 0) {
                        for (i in 0 until oldBody.size()) {
                            body.addEncoded(oldBody.encodedName(i), oldBody.encodedValue(i))
                        }
                    }

                    request = request.newBuilder()
                        .post(body.build())
                        .build()
                }
            }
        }
        return chain.proceed(request)
    }

}