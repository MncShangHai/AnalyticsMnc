package com.venpoo.data_sdk

import android.os.Build
import com.venpoo.data_sdk.AnyalyticsManger.context
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

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun startApp(product_id: String, user_id: String,channel_name:String?) {
        val map = HashMap<String, String>()
        map["product_id"] = product_id
        map["channel_target"] = "Android"
        map["MEID"] = getUuid()
        map["phone_model"] = Build.MODEL
        map["phone_resolution"] = getScreenMetrics()
        map["phone_system"] = Build.MANUFACTURER.toLowerCase()
        channel_name?.let {
            map["channel_name"] = it
        }
        map["operator_name"] = DeviceUtil.networkOperatorName
        map["network_name"] = NetWorkUtils.getNetworkTypeName(context)
        //地理区域不太好获取
        map["regional"] = "未知"
        map["behavior_time"] = TimeUtils.nowTime
        map["product_version"] = VersionUtils.getAppVersionName()
        map["user_id"] = user_id
        compositeDisposable.add(HttpFactory.startApp(map2RequestJson(map), Consumer {
            //请求成功
            if (it.success!!){

            }else{

            }
        }, Consumer {
            //请求失败 500 或者处理失败
        }))
    }


    override fun closeApp(product_id: String, user_id: String,channel_name:String?) {
        val map = HashMap<String, String>()
        map["product_id"] = product_id
        map["channel_target"] = "Android"
        map["MEID"] = getUuid()
        map["phone_model"] = Build.MODEL
        map["phone_resolution"] = getScreenMetrics()
        map["phone_system"] = Build.MANUFACTURER.toLowerCase()
        channel_name?.let {
            map["channel_name"] = it
        }
        map["operator_name"] = DeviceUtil.networkOperatorName
        map["network_name"] = NetWorkUtils.getNetworkTypeName(context)
        //地理区域不太好获取
        map["regional"] = "未知"
        map["behavior_time"] = TimeUtils.nowTime
        map["product_version"] = VersionUtils.getAppVersionName()
        map["user_id"] = user_id
        compositeDisposable.add(HttpFactory.closeApp(map2RequestJson(map), Consumer {
            //请求成功
            if (it.success!!){

            }else{

            }
        }, Consumer {
            //请求失败 500 或者处理失败
        }))
    }

    override fun login(product_id: String, user_id: String,channel_name:String?) {
        val map = HashMap<String, String>()
        map["product_id"] = product_id
        map["channel_target"] = "Android"
        map["MEID"] = getUuid()
        map["phone_model"] = Build.MODEL
        map["phone_resolution"] = getScreenMetrics()
        map["phone_system"] = Build.MANUFACTURER.toLowerCase()
        channel_name?.let {
            map["channel_name"] = it
        }
        map["operator_name"] = DeviceUtil.networkOperatorName
        map["network_name"] = NetWorkUtils.getNetworkTypeName(context)
        //地理区域不太好获取
        map["regional"] = "未知"
        map["behavior_time"] = TimeUtils.nowTime
        map["product_version"] = VersionUtils.getAppVersionName()
        map["user_id"] = user_id
        compositeDisposable.add(HttpFactory.login(map2RequestJson(map), Consumer {
            //请求成功
            if (it.success!!){

            }else{

            }
        }, Consumer {
            //请求失败 500 或者处理失败
        }))
    }

    override fun unLogin(product_id: String, user_id: String,channel_name:String?) {
        val map = HashMap<String, String>()
        map["product_id"] = product_id
        map["channel_target"] = "Android"
        map["MEID"] = getUuid()
        map["phone_model"] = Build.MODEL
        map["phone_resolution"] = getScreenMetrics()
        map["phone_system"] = Build.MANUFACTURER.toLowerCase()
        channel_name?.let {
            map["channel_name"] = it
        }
        map["operator_name"] = DeviceUtil.networkOperatorName
        map["network_name"] = NetWorkUtils.getNetworkTypeName(context)
        //地理区域不太好获取
        map["regional"] = "未知"
        map["behavior_time"] = TimeUtils.nowTime
        map["product_version"] = VersionUtils.getAppVersionName()
        map["user_id"] = user_id
        compositeDisposable.add(HttpFactory.unLogin(map2RequestJson(map), Consumer {
            //请求成功
            if (it.success!!){

            }else{

            }
        }, Consumer {
            //请求失败 500 或者处理失败
        }))
    }

    override fun register(product_id: String, user_id: String,channel_name: String?,user_nickname:String?,user_sex:String?,user_birthday:String?,user_phone_number:String?) {
        val map = HashMap<String, String>()
        map["product_id"] = product_id
        map["channel_target"] = "Android"
        map["MEID"] = getUuid()
        map["phone_model"] = Build.MODEL
        map["phone_resolution"] = getScreenMetrics()
        map["phone_system"] = Build.MANUFACTURER.toLowerCase()
        channel_name?.let {
            map["channel_name"] = it
        }
        map["operator_name"] = DeviceUtil.networkOperatorName
        map["network_name"] = NetWorkUtils.getNetworkTypeName(context)
        //地理区域不太好获取
        map["regional"] = "未知"
        map["behavior_time"] = TimeUtils.nowTime
        map["product_version"] = VersionUtils.getAppVersionName()
        map["user_id"] = user_id

        user_nickname?.let {
            map["user_nickname"] = it
        }
        user_sex?.let {
            map["user_sex"] = it
        }
        user_birthday?.let {
            map["user_birthday"] = it
        }
        user_phone_number?.let {
            map["user_phone_number"] = it
        }

        compositeDisposable.add(HttpFactory.register(map2RequestJson(map), Consumer {
            //请求成功
            if (it.success!!){

            }else{

            }
        }, Consumer {
            //请求失败 500 或者处理失败
        }))
    }

    override fun payMoney(product_id: String, user_id: String, channel_name: String?, order_id:String,goods:String?,money:String?,method:String?,order_state:String?) {
        val map = HashMap<String, String>()
        map["product_id"] = product_id
        map["channel_target"] = "Android"
        map["MEID"] = getUuid()
        map["phone_model"] = Build.MODEL
        map["phone_resolution"] = getScreenMetrics()
        map["phone_system"] = Build.MANUFACTURER.toLowerCase()
        channel_name?.let {
            map["channel_name"] = it
        }
        map["operator_name"] = DeviceUtil.networkOperatorName
        map["network_name"] = NetWorkUtils.getNetworkTypeName(context)
        //地理区域不太好获取
        map["regional"] = "未知"
        map["behavior_time"] = TimeUtils.nowTime
        map["product_version"] = VersionUtils.getAppVersionName()
        map["user_id"] = user_id

        map["order_id"] = order_id
        goods?.let {
            map["goods"] = it
        }
        money?.let {
            map["money"] = it
        }
        method?.let {
            map["method"] = it
        }
        order_state?.let {
            map["order_state"] = it
        }

        compositeDisposable.add(HttpFactory.payMoney(map2RequestJson(map), Consumer {
            //请求成功
            if (it.success!!){

            }else{

            }
        }, Consumer {
            //请求失败 500 或者处理失败
        }))

    }


}