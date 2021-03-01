package com.venpoo.statisticssdk

import android.app.Application
import com.venpoo.data_sdk.AnyalyticsManger

/**
 * @ClassName App
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/3/1 10:07
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AnyalyticsManger.initSdk(applicationContext)
    }

}