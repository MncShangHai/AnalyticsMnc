package com.venpoo.data_sdk

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import java.lang.ref.WeakReference

/**
 * @ClassName MySDK
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/2/26 16:54
 */
object AnyalyticsManger : Anyalytics {

    private const val TAG = "AnyalyticsManger"

    private var context: WeakReference<Context> ?= null

    private val anyalyticsImpl: AnyalyticsImpl  by lazy { AnyalyticsImpl() }

    //调式模式
    var isDebug = false

    var productName:String = ""

    var uuid:String = ""

    var uid:String = ""

    var channel:String = ""


    //？？？
    val netWork:String = "未知"
    val location:String = "未知"
    val imei:String = "null"
    val idfa:String = "null"


    /**
     * TODO 设置参数
     *
     * @param product 产品名称，Product
     * @param uuid
     * @param uid
     * @param channel 打包渠道名称
     * @return
     */
    fun setParams(product:Procudt,uuid:String = "",uid:String = "", channel:String = "", isDebug:Boolean = false):AnyalyticsManger = apply {
        when(product){
            Procudt.HDQP -> productName = "haoduoqupu"
            Procudt.LECI -> productName = "leciyuepu"
            Procudt.WDNM -> productName = "wodunimo"
            Procudt.CWCT -> productName = "ciweicuotiben"
            Procudt.PXB  -> productName = "pixiaobao"
        }
        this.uuid = uuid
        this.uid = uid
        this.channel = channel
        this.isDebug = isDebug
    }

    /**
     * TODO 在Application中初始化
     * @param context applicationContext
     * @param uuid 不同产品对的应用户标识唯一值
     * @param isDebug 是否调式模式，调试时可以关闭
     */
    fun init(context: Context) {
        this.context = WeakReference(context.applicationContext)
    }

    fun getContext():Context{
        return context?.get()!!
    }

    override fun startApp() {
        if (isDebug) return
        anyalyticsImpl.startApp()
    }

    override fun closeApp() {
        if (isDebug) return
        anyalyticsImpl.closeApp()
    }

    override fun startPage(pageName: String) {
        if (isDebug) return
        anyalyticsImpl.startPage(pageName)
    }


}