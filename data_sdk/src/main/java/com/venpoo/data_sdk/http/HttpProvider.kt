package com.venpoo.data_sdk.http


import com.venpoo.data_sdk.DEBUG_BASE_URL
import com.venpoo.data_sdk.HTTP_POST_URL
import com.venpoo.data_sdk.TIME_OUT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Level

class HttpProvider {
    private lateinit var retrofit: Retrofit

    companion object {
        @Volatile private var INSTANCE: HttpProvider?=null
        fun getInstance(): HttpProvider = INSTANCE ?: synchronized(this) {
            INSTANCE ?: HttpProvider().also { INSTANCE = it }
        }
    }

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
        .addInterceptor(getLogInterceptor())
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



    init {
        initConfig()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    /**
     * 获取日志拦截器
     */
    private fun getLogInterceptor(): Interceptor {
        //http log 拦截器
        return HttpLoggingInterceptor("StatisticsSDK").apply {
            setPrintLevel(HttpLoggingInterceptor.Level.BODY)
            setColorLevel(Level.INFO)
        }
    }
}