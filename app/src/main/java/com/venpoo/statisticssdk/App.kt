package com.venpoo.statisticssdk

import android.app.Application
import com.venpoo.data_sdk.AnyalyticsManger
import com.venpoo.data_sdk.Procudt

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

        AnyalyticsManger.setParams(
            product = Procudt.HDQP,
            uuid = "xxx",
            uid = "xxx",
            channel = "xxx"
        ).init(this)
    }


    /**
     * TODO 监听App退出
     * 仅对模拟器有效
     */
    override fun onTerminate() {
        super.onTerminate()
        //AnyalyticsManger.closeApp()
    }

}