package com.venpoo.data_sdk

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @ClassName Anyalytics
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/2/26 16:57
 */
interface Anyalytics {

    /**
     * 启动app
     */
    fun startApp(product_id:String,user_id:String)

    /**
     * 关闭app
     */
    fun closeApp(product_id:String,user_id:String)

    /**
     * 登录
     */
    fun login(product_id:String,user_id:String)

    /**
     * 退出登录
     */
    fun unLogin(product_id:String,user_id:String)

    /**
     * 注册
     */
    fun register(product_id: String, user_id: String,user_nickname:String?,user_sex:String?,user_birthday:String?,user_phone_number:String?)

    /**
     * 付费
     */
    fun payMoney(product_id: String, user_id: String, order_id:String,goods:String?,money:String?,method:String?,order_state:Boolean?)

}