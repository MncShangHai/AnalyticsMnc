package com.venpoo.data_sdk.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors

val singLeExecutor: Executor = Executors.newSingleThreadExecutor()
val cacheExecutor:Executor= Executors.newCachedThreadPool()


fun <T> Observable<T>.schedule(executor: Executor): Observable<T> {
    return subscribeOn(Schedulers.from(executor)).observeOn(mainThread())
}

fun <T> Observable<T>.subscribeOnNextConsumer(executor: Executor, consumer: Consumer<T>): Disposable {
    return schedule(executor).subscribe(consumer)

}

fun <T> Observable<T>.subscribeOnNextConsumerAndErrorConsumer(executor: Executor, next: Consumer<T>,error: Consumer<Throwable>): Disposable {
    return schedule(executor).subscribe(next,error)
}

fun <T> Observable<T>.subscribeOnNextConsumerAndErrorAndComplete(executor: Executor, next: Consumer<T>,error: Consumer<Throwable>,complete : Action): Disposable {
    return schedule(executor).subscribe(next,error,complete)
}

