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
    fun startApp()


    /**
     * 关闭app
     */
    fun closeApp()

    /**
     * 登录
     */
    fun login()

    /**
     * 退出登录
     */
    fun unLogin()

    /**
     * 注册
     */
    fun register()


    /**
     * 付费
     */
    fun payMoney()

}