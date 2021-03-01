package com.venpoo.data_sdk.utils

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Build
import android.provider.Settings
import android.view.WindowManager
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
 * 获取设备为一组
 */
@SuppressLint("HardwareIds")
fun getUuid(): String {
    val uuid =  Build.MODEL + Settings.Secure.getString(
        mContext.contentResolver,
        Settings.Secure.ANDROID_ID
    )
    return uuid
}

/**
 * 获取屏幕像素
 */
fun getScreenMetrics():String{
    return DensityUtil.getScreenWidth(mContext).toString()+"*"+DensityUtil.getScreenHeight(mContext)
}