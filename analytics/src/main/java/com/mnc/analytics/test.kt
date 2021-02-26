package com.mnc.analytics

import com.google.gson.Gson
import com.mnc.analytics.http.HttpFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

object test {

    private val compositeDisposable = CompositeDisposable()

    fun sendMessage(){
        //发送数据
        val map = HashMap<String, String>()
        map["uuid"] = "get_android_uuid"
        //键值对结构体
        val objectString = Gson().toJson(map)
        val requestBody = objectString.toRequestBody(JSONTYPE.toMediaTypeOrNull())
        //post 转body发送
        compositeDisposable.add(HttpFactory.open("android",requestBody, Consumer {
            //请求成功
            if (it.success!!){

            }else{

            }
        }, Consumer {
            //请求失败 500 或者处理失败
        }))
    }
}