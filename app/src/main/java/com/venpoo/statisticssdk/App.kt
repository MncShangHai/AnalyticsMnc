package com.venpoo.statisticssdk

import android.app.Application
import com.venpoo.data_sdk.AnyalyticsManger
//import com.venpoo.data_sdk.BuildConfig.DEBUG

/**
 * @ClassName App
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/3/1 10:07
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化,uuid可不传,isDebug表示是否调式模式
        AnyalyticsManger.initSdk(applicationContext,null, false)
    }

}