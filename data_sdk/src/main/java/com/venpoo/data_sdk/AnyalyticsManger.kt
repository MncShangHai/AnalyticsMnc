package com.venpoo.data_sdk

import android.content.Context
import android.util.Log

/**
 * @ClassName MySDK
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/2/26 16:54
 */
object AnyalyticsManger : Anyalytics {

    private val TAG = "AnyalyticsManger"
    lateinit var context: Context

    //实现类私有化
    private val anyalyticsImpl: AnyalyticsImpl  by lazy { AnyalyticsImpl() }


    /**
     * 调用之前执行初始化
     */
    fun initSdk(context: Context) {
        AnyalyticsManger.context = context.applicationContext
    }


    /**
     * @param product_id 产品名称(ciwei/haoduo/leci/skinDetection/wodunimo)
     * @param user_id 用户唯一值
     * @param channel_name 渠道包名称（小米/华为/vivo/oppo/360/。。。）可为空
     */
    override fun startApp(product_id: String, user_id: String,channel_name:String?) {
        Log.d(TAG,"startApp()")
        anyalyticsImpl.startApp(product_id, user_id,channel_name)
    }

    override fun closeApp(product_id: String, user_id: String,channel_name:String?) {
        anyalyticsImpl.closeApp(product_id, user_id,channel_name)
    }

    override fun login(product_id: String, user_id: String,channel_name:String?) {
        anyalyticsImpl.login(product_id, user_id,channel_name)
    }

    override fun unLogin(product_id: String, user_id: String,channel_name:String?) {
        anyalyticsImpl.unLogin(product_id, user_id,channel_name)
    }


    /**
     * @param product_id
     * @param user_id
     * @param channel_name
     * @param user_nickname 用户昵称
     * @param user_sex 用户性别
     * @param user_birthday 用户生日
     * @param user_phone_number 用户电话号码
     */
    override fun register(product_id: String, user_id: String,channel_name: String?,user_nickname:String?,user_sex:String?,user_birthday:String?,user_phone_number:String?) {
        anyalyticsImpl.register(product_id, user_id,channel_name,user_nickname, user_sex, user_birthday, user_phone_number)
    }


    /**
     * @param product_id
     * @param user_id
     * @param channel_name
     * @param order_id 订单号（不可为空）
     * @param goods 商品
     * @param money 金额
     * @param method 支付方式
     * @param order_state 订单状态
     */
    override fun payMoney(product_id: String, user_id: String, channel_name: String?, order_id:String,goods:String?,money:String?,method:String?,order_state:String?) {
        anyalyticsImpl.payMoney(product_id, user_id, channel_name, order_id, goods, money, method, order_state)
    }


}