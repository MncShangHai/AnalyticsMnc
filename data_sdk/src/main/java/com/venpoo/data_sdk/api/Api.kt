package com.venpoo.data_sdk.api

import com.venpoo.data_sdk.AnyalyticsManger
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


    /**
     * 启动app
     */
    @POST("api/startover/{name}")
    fun startApp(@Body body: RequestBody,@Path("name") name:String) : Observable<BaseBean>

    /**
     * 关闭app
     */
    @POST("api/end/{name}")
    fun closeApp(@Body body: RequestBody,@Path("name") name:String): Observable<BaseBean>

    /**
     * 进入页面
     */
    @POST("api/pageview/{name}")
    fun startPage(@Body body: RequestBody,@Path("name") name:String) : Observable<BaseBean>



}