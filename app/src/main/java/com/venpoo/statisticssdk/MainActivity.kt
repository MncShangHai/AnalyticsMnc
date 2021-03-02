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

    //打开app，user_id可为null
    fun startApp(view: View) {
        AnyalyticsManger.startApp("ciwei",null)
    }

    //关闭app
    fun closeApp(view: View) {
        AnyalyticsManger.closeApp("ciwei","12345")
    }

    //登录
    fun login(view: View) {
        AnyalyticsManger.login("ciwei","12345")
    }

    //退出登录
    fun unLogin(view: View) {
        AnyalyticsManger.unLogin("ciwei","12345")
    }

    //注册
    fun register(view: View) {
        AnyalyticsManger.register("ciwei","12345",
                "nickname","sex","1997-xxx","13677291450")
    }

    //支付
    fun payMoney(view: View) {
        AnyalyticsManger.payMoney("ciwei","12345",
        "asdasdasdasda3er2aefraf","商品-1",12.0f,"微信支付",true)
    }

}