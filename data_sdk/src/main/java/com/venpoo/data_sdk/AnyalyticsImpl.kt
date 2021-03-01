package com.venpoo.data_sdk

import com.google.gson.Gson
import com.venpoo.data_sdk.http.HttpFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * @ClassName AnyalyticsImpl
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/2/26 17:03
 */
class AnyalyticsImpl : Anyalytics {

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    /**
     * #产品号(ciwei/haoduo/leci/skinDetection/wodunimo)
    (刺猬错题本/好多曲谱/乐此乐谱/皮肤检测/我读你默)（不可为空）String
     */
    fun startApp(product_id:String,
                 channel_target:String,
                 MEID:String,
                 phone_model:String?=null,
                 phone_resolution:String?=null,
                 phone_system:String?=null,
                 channel_name:String?=null,) {
        val map = HashMap<String, String>()
        map["product_id"] = "get_android_uuid"
        //键值对结构体
        val objectString = Gson().toJson(map)
        val requestBody = objectString.toRequestBody(JSONTYPE.toMediaTypeOrNull())
        //post 转body发送
        compositeDisposable.add(HttpFactory.startApp(requestBody, Consumer {
            //请求成功
            if (it.success!!){

            }else{

            }
        }, Consumer {
            //请求失败 500 或者处理失败
        }))
    }

    override fun startApp() {
        val map = HashMap<String, String>()
        map["product_id"] = "ciwei"
        map["channel_target"] = "Android"
        map["MEID"] = "test"
        //键值对结构体
        val objectString = Gson().toJson(map)
        val requestBody = objectString.toRequestBody(JSONTYPE.toMediaTypeOrNull())
        //post 转body发送
        compositeDisposable.add(HttpFactory.startApp(requestBody, Consumer {
            //请求成功
            if (it.success!!){

            }else{

            }
        }, Consumer {
            //请求失败 500 或者处理失败
        }))
    }

    override fun closeApp() {
        test()
    }

    override fun login() {
        TODO("Not yet implemented")
    }

    override fun unLogin() {
        TODO("Not yet implemented")
    }

    override fun register() {
        TODO("Not yet implemented")
    }

    override fun payMoney() {
        TODO("Not yet implemented")
    }

    fun test(){
        //发送数据
        val map = HashMap<String, String>()
        map["uuid"] = "get_android_uuid"
        //键值对结构体
        val objectString = Gson().toJson(map)
        val requestBody = objectString.toRequestBody(JSONTYPE.toMediaTypeOrNull())
        //post 转body发送
        compositeDisposable.add(HttpFactory.open( Consumer {
            //请求成功
            if (it.success!!){

            }else{

            }
        }, Consumer {
            //请求失败 500 或者处理失败
        }))
    }

}