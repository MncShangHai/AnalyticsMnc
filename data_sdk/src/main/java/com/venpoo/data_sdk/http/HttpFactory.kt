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


    fun startApp(requestBody: RequestBody, consumer: Consumer<BaseBean>, error: Consumer<Throwable>): Disposable {
        return provider.startApp(requestBody)
            .subscribeOnNextConsumerAndErrorConsumer(cacheExecutor, consumer,error)
    }

    fun closeApp(requestBody: RequestBody, consumer: Consumer<BaseBean>, error: Consumer<Throwable>): Disposable {
        return provider.closeApp(requestBody)
            .subscribeOnNextConsumerAndErrorConsumer(cacheExecutor, consumer,error)
    }

    fun login(requestBody: RequestBody, consumer: Consumer<BaseBean>, error: Consumer<Throwable>): Disposable {
        return provider.login(requestBody)
            .subscribeOnNextConsumerAndErrorConsumer(cacheExecutor, consumer,error)
    }

    fun unLogin(requestBody: RequestBody, consumer: Consumer<BaseBean>, error: Consumer<Throwable>): Disposable {
        return provider.unLogin(requestBody)
            .subscribeOnNextConsumerAndErrorConsumer(cacheExecutor, consumer,error)
    }

    fun register(requestBody: RequestBody, consumer: Consumer<BaseBean>, error: Consumer<Throwable>): Disposable {
        return provider.register(requestBody)
            .subscribeOnNextConsumerAndErrorConsumer(cacheExecutor, consumer,error)
    }

    fun payMoney(requestBody: RequestBody, consumer: Consumer<BaseBean>, error: Consumer<Throwable>): Disposable {
        return provider.payMoney(requestBody)
            .subscribeOnNextConsumerAndErrorConsumer(cacheExecutor, consumer,error)
    }

}