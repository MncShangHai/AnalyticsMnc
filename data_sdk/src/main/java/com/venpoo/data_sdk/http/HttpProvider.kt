package com.venpoo.data_sdk.http

import android.os.Debug
import com.venpoo.data_sdk.AnyalyticsManger.isDebug
import com.venpoo.data_sdk.HTTP_DEBUG_URL
import com.venpoo.data_sdk.HTTP_RELEASE_URL
import com.venpoo.data_sdk.TIME_OUT
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class HttpProvider {
    private lateinit var retrofit: Retrofit

    companion object {
        @Volatile private var INSTANCE: HttpProvider?=null
        fun getInstance(): HttpProvider = INSTANCE ?: synchronized(this) {
            INSTANCE ?: HttpProvider().also { INSTANCE = it }
        }
    }

    init {
        initConfig()
    }

    var time: Long = TIME_OUT
    private fun initConfig() {
        retrofit = Retrofit.Builder()
            .baseUrl(HTTP_RELEASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    fun okHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(LogInterceptor())
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


    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

}