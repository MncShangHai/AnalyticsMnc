package com.venpoo.data_sdk.utils

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.google.gson.Gson
import com.venpoo.data_sdk.AnyalyticsManger
import com.venpoo.data_sdk.JSONTYPE
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


/**
 * @ClassName CommonUtil
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/3/1 11:18
 */

private val mContext by lazy {
    AnyalyticsManger.context
}


/**
 * 请求头转换
 */
fun map2RequestJson(map: Map<String, Any>): RequestBody {
    return Gson().toJson(map).toRequestBody(JSONTYPE.toMediaTypeOrNull())
}

/**
 * 获取设备为一值
 */
@SuppressLint("HardwareIds")
fun getUuid(): String {
    var uuid = Build.MODEL + Settings.Secure.getString(mContext.contentResolver, Settings.Secure.ANDROID_ID)
    //传入的uuid为优先级最高
    AnyalyticsManger.uuid?.let {
        uuid = it
    }
    return uuid
}

/**
 * 获取屏幕像素
 */
fun getScreenMetrics():String{
    return DensityUtil.getScreenWidth(mContext).toString()+"*"+DensityUtil.getScreenHeight(mContext)
}

/**
 * 获取渠道包名称
 */
fun getChannelName():String?{
    val appInfo: ApplicationInfo = mContext.packageManager.getApplicationInfo(mContext.packageName, PackageManager.GET_META_DATA)
    var appChannel = appInfo.metaData.getString("ATMAN_CHANNEL")
    if (appChannel.isNullOrEmpty()){
        //Log.d("CommonUtil", "null")
        appChannel = appInfo.metaData.getString("UMENG_CHANNEL")
    }
    //appChannel?.let { Log.d("CommonUtil", it) }
    return appChannel
}