package com.jesse.baselibs.net

import com.jesse.baselibs.net.interceptor.HeaderInterceptor
import com.jesse.baselibs.net.interceptor.ParameterInterceptor
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Description: retrofit基础类，业务代码需要继承此类，重写相应的配置
 * created by Jesse at 2019-08-21
 */
abstract class BaseRetrofitManager {

    protected fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(getConverterFactory())
            .build()

    }

    open fun getOkHttpClient(): OkHttpClient {
        //添加一个log拦截器,打印所有的log
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        //可以设置请求过滤的水平,body,basic,headers
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(ParameterInterceptor(params()))  //参数添加
            .addInterceptor(HeaderInterceptor(headers()))// token过滤
            .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .build()
    }


    /**
     * 自定义baseUrl
     */
    abstract fun getBaseUrl(): String

    /**
     * 自定义数据转换器
     */
    open fun getConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    /**
     * 自定义公共请求头
     */
    open fun headers(): HashMap<String, Any>? {
        return null
    }

    /**
     * 自定义公共参数
     */
    open fun params(): HashMap<String, Any>? {
        return null
    }



}
