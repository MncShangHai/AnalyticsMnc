package com.mnc.analytics.http

import com.mnc.analytics.HTTP_POST_URL
import com.mnc.analytics.TIME_OUT
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpProvider {
    private lateinit var retrofit: Retrofit
    var time: Long = TIME_OUT
    private fun initConfig() {
        retrofit = Retrofit.Builder()
            .baseUrl(HTTP_POST_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    fun okHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
//        .addInterceptor(TokenInterceptor())
        .connectTimeout(time, TimeUnit.MILLISECONDS)
        .readTimeout(time, TimeUnit.MILLISECONDS)
        .writeTimeout(time, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .build()

    /**指定超时时间*/
    fun setOutTime(time: Long): HttpProvider = let {
        it.time = time
        return it
    }

    companion object {
        @Volatile private var INSTANCE: HttpProvider?=null
        fun getInstance(): HttpProvider = INSTANCE ?: synchronized(this) {
            INSTANCE ?: HttpProvider().also { INSTANCE = it }
        }
    }

    init {
        initConfig()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}