package com.venpoo.data_sdk

import android.os.Build
import com.venpoo.data_sdk.http.HttpFactory
import com.venpoo.data_sdk.utils.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer

/**
 * @ClassName AnyalyticsImpl
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/2/26 17:03
 */
class AnyalyticsImpl : Anyalytics {

    private val TAG = "AnyalyticsImpl"

    private val clientType = "Android"


    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun startApp() {
        val map = HashMap<String, Any>()
        map["uuid"] = AnyalyticsManger.uuid
        map["uid"] = AnyalyticsManger.uid
        map["product_version"] = getAppVersionName()
        map["client_type"] = clientType
        map["download_channel"] = AnyalyticsManger.channel
        map["system_num"] = getSdkVersion()
        map["phone_type"] = getPhoneMode()
        map["screen_width"] = getScreenWidth()
        map["screen_height"] = getScreenHeight()
        map["net"] = AnyalyticsManger.netWork
        map["operator_sys"] = networkOperatorName
        map["area"] = AnyalyticsManger.location
        map["time"] = getGMTtime()
        map["IMEI"] = AnyalyticsManger.imei
        map["IDFA"] = AnyalyticsManger.idfa
        map["login_method"] = 1
        compositeDisposable.add(HttpFactory.startApp(map2RequestJson(map), AnyalyticsManger.productName, {
            //请求成功
            if (it.code==200){

            }else{

            }
        }, {
            //请求失败 500 或者处理失败
        }))
    }

    override fun closeApp() {
        val map = HashMap<String, Any>()
        map["uuid"] = AnyalyticsManger.uuid
        map["uid"] = AnyalyticsManger.uid
        map["product_version"] = getAppVersionName()
        map["client_type"] = clientType
        map["download_channel"] = AnyalyticsManger.channel
        map["system_num"] = getSdkVersion()
        map["phone_type"] = getPhoneMode()
        map["screen_width"] = getScreenWidth()
        map["screen_height"] = getScreenHeight()
        map["net"] = AnyalyticsManger.netWork
        map["operator_sys"] = networkOperatorName
        map["area"] = AnyalyticsManger.location
        map["time"] = getGMTtime()
        map["IMEI"] = AnyalyticsManger.imei
        map["IDFA"] = AnyalyticsManger.idfa
        map["login_method"] = 1
        compositeDisposable.add(HttpFactory.closeApp(map2RequestJson(map), AnyalyticsManger.productName, {
            //请求成功
            if (it.code==200){

            }else{

            }
        }, {
            //请求失败 500 或者处理失败
        }))
    }

    override fun startPage(pageName:String) {
        val map = HashMap<String, Any>()
        map["uuid"] = AnyalyticsManger.uuid
        map["uid"] = AnyalyticsManger.uid
        map["product_version"] = getAppVersionName()
        map["client_type"] = clientType
        map["download_channel"] = AnyalyticsManger.channel
        map["system_num"] = getSdkVersion()
        map["phone_type"] = getPhoneMode()
        map["screen_width"] = getScreenWidth()
        map["screen_height"] = getScreenHeight()
        map["net"] = AnyalyticsManger.netWork
        map["operator_sys"] = networkOperatorName
        map["area"] = AnyalyticsManger.location
        map["time"] = getGMTtime()
        map["IMEI"] = AnyalyticsManger.imei
        map["IDFA"] = AnyalyticsManger.idfa
        map["login_method"] = 1
        map["page_name"] = pageName
        compositeDisposable.add(HttpFactory.startPage(map2RequestJson(map), AnyalyticsManger.productName, {
            //请求成功
            if (it.code==200){

            }else{

            }
        }, {
            //请求失败 500 或者处理失败
        }))
    }

}