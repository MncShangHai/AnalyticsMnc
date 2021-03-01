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
     * 获取ApplicationContext防止内存泄漏
     */
    fun initSdk(context: Context) {
        AnyalyticsManger.context = context.applicationContext
    }


    override fun startApp() {
        Log.d(TAG,"startApp()")
        anyalyticsImpl.startApp()
    }

    override fun closeApp() {
        anyalyticsImpl.closeApp()
    }

    override fun login() {
        anyalyticsImpl.login()
    }

    override fun unLogin() {
        anyalyticsImpl.unLogin()
    }

    override fun register() {
        anyalyticsImpl.register()
    }

    override fun payMoney() {
        anyalyticsImpl.payMoney()
    }


}