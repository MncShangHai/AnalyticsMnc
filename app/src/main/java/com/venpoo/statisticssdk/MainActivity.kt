package com.venpoo.statisticssdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.venpoo.data_sdk.AnyalyticsManger

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //打开app
    fun startApp(view: View) {
        AnyalyticsManger.startApp()
    }

    //关闭app
    fun closeApp(view: View) {
        //没找到有效监听APP关闭方法
        AnyalyticsManger.closeApp()
    }

    //打开页面
    fun startPage(view: View) {
        AnyalyticsManger.startPage("-----------")
    }

    //退出登录
    fun unLogin(view: View) {
    }

    //注册
    fun register(view: View) {
    }

    //支付
    fun payMoney(view: View) {
    }

}