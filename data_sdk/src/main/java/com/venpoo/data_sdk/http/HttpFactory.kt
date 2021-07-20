package com.venpoo.data_sdk.http


import com.venpoo.data_sdk.api.Api
import com.venpoo.data_sdk.bean.BaseBean
import com.venpoo.data_sdk.utils.cacheExecutor
import com.venpoo.data_sdk.utils.subscribeOnNextConsumerAndErrorConsumer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import okhttp3.RequestBody

object HttpFactory {

    private val provider = HttpProvider.getInstance().create(Api::class.java)


    fun startApp(requestBody: RequestBody,productName:String, consumer: Consumer<BaseBean>, error: Consumer<Throwable>): Disposable {
        return provider.startApp(requestBody,productName).subscribeOnNextConsumerAndErrorConsumer(cacheExecutor, consumer,error)
    }

    fun closeApp(requestBody: RequestBody,productName:String, consumer: Consumer<BaseBean>, error: Consumer<Throwable>): Disposable {
        return provider.closeApp(requestBody,productName).subscribeOnNextConsumerAndErrorConsumer(cacheExecutor, consumer,error)
    }

    fun startPage(requestBody: RequestBody,productName:String, consumer: Consumer<BaseBean>, error: Consumer<Throwable>): Disposable {
        return provider.startPage(requestBody,productName).subscribeOnNextConsumerAndErrorConsumer(cacheExecutor, consumer,error)
    }
}