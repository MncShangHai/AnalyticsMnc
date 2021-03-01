package com.venpoo.data_sdk.api

import com.venpoo.data_sdk.bean.BaseBean
import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody
import okhttp3.internal.concurrent.Task
import retrofit2.Call
import retrofit2.http.*


/**
 * @ClassName Api
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2020/12/14 11:40
 */
interface Api {


    @GET("prices")
    fun test(): Observable<BaseBean>


    /**
     * 启动app
     */
    @POST("Save_data/Save_start")
    fun startApp(@Body body: RequestBody) : Observable<BaseBean>

    /**
     * 关闭app
     */
    @POST("Save_data/Save_close")
    fun closeApp(@Body body: RequestBody): Observable<BaseBean>

    /**
     * 登录
     */
    @POST("Save_data/Save_login")
    fun login(@Body body: RequestBody) : Observable<BaseBean>

    /**
     * 退出登录
     */
    fun unLogin(@Body body: RequestBody) : Observable<BaseBean>

    /**
     * 注册
     */
    fun register(@Body body: RequestBody) : Observable<BaseBean>

    /**
     * 付费
     */
    fun payMoney(@Body body: RequestBody) : Observable<BaseBean>


}