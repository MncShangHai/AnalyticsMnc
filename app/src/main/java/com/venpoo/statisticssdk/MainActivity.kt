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

    fun startApp(view: View) {
        AnyalyticsManger.startApp()
    }

    fun closeApp(view: View) {
        AnyalyticsManger.closeApp()
    }

}