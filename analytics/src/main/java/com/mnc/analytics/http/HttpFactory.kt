package com.mnc.analytics.http

import com.mnc.analytics.entity.BaseBean
import com.mnc.analytics.utils.cacheExecutor
import com.mnc.analytics.utils.subscribeOnNextConsumerAndErrorConsumer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import okhttp3.RequestBody

object HttpFactory {

    val provider = HttpProvider.getInstance().create(HttpService::class.java)

    fun open(str :String, requestBody: RequestBody, consumer: Consumer<BaseBean>, error: Consumer<Throwable>): Disposable {
        return provider.open(str,requestBody)
            .subscribeOnNextConsumerAndErrorConsumer(cacheExecutor, consumer,error)
    }
}