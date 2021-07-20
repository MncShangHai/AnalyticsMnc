package com.venpoo.data_sdk.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.venpoo.data_sdk.AnyalyticsManger
import com.venpoo.data_sdk.JSONTYPE
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.SimpleDateFormat
import java.util.*


/**
 * @ClassName CommonUtil
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/3/1 11:18
 */

private val mContext:Context by lazy {
    AnyalyticsManger.getContext()
}

/**
 * 请求头转换
 */
fun map2RequestJson(map: Map<String, Any>): RequestBody {
    return Gson().toJson(map).toRequestBody(JSONTYPE.toMediaTypeOrNull())
}


/**
 * 获取渠道包名称
 */
fun getChannelName():String?{
    val appInfo: ApplicationInfo = mContext.packageManager.getApplicationInfo(
        mContext.packageName,
        PackageManager.GET_META_DATA
    )
    var appChannel = appInfo.metaData.getString("ATMAN_CHANNEL")
    if (appChannel.isNullOrEmpty()){
        //Log.d("CommonUtil", "null")
        appChannel = appInfo.metaData.getString("UMENG_CHANNEL")
    }
    //appChannel?.let { Log.d("CommonUtil", it) }
    return appChannel
}

/**
 * 获取系统时间
 */
@SuppressLint("SimpleDateFormat")
fun getCurentTime(): String {
    val timeStamp = System.currentTimeMillis() //获取当前时间戳
    //SimpleDateFormat("yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒")
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val sd = sdf.format(Date(timeStamp.toString().toLong())) // 时间戳转换成时间
    return sd
}


fun getGMTtime():String{
    val cd = Calendar.getInstance()
    val sdf = SimpleDateFormat("EEE d MMM yyyy HH:mm:ss 'GMT'", Locale.US)
    sdf.timeZone = TimeZone.getTimeZone("GMT") // 设置时区为GMT

    return sdf.format(cd.time)
}

/**
 * 获取网络运营商名称
 * 中国移动、如中国联通、中国电信
 */
val networkOperatorName: String
    get() {
        var opeType = "unknown"
        // No sim
        if (!hasSim(mContext)) {
            return opeType
        }
        val tm: TelephonyManager = mContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val operator: String = tm.simOperator
        opeType = if ("46001" == operator || "46006" == operator || "46009" == operator) {
            "中国联通"
        } else if ("46000" == operator || "46002" == operator || "46004" == operator || "46007" == operator) {
            "中国移动"
        } else if ("46003" == operator || "46005" == operator || "46011" == operator) {
            "中国电信"
        } else {
            "unknown"
        }
        return opeType
    }

/**
 * 检查手机是否有sim卡
 */
private fun hasSim(context: Context): Boolean {
    val tm: TelephonyManager =
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    val operator: String = tm.simOperator
    return !TextUtils.isEmpty(operator)
}

/**
 * 获取屏幕像素
 */
fun getScreenSize(): String {
    return "${mContext.resources.displayMetrics.widthPixels} x ${mContext.resources.displayMetrics.heightPixels}"
}

fun getScreenWidth():String {
    return mContext.resources.displayMetrics.widthPixels.toString()
}

fun getScreenHeight():String{
    return mContext.resources.displayMetrics.heightPixels.toString()
}

/**
 * 返回当前程序版本号
 */
fun getAppVersionCode(): Int {
    var versioncode = 0
    try {
        val pm: PackageManager = mContext.packageManager
        val pi: PackageInfo = pm.getPackageInfo(mContext.packageName, 0)
        versioncode = pi.versionCode
    } catch (e: Exception) {
        Log.e("VersionInfo", "Exception", e)
    }
    return versioncode
}

/**
 * 返回当前程序版本名
 */
fun getAppVersionName(): String {
    var versionName = ""
    try {
        val pm: PackageManager = mContext.packageManager
        val pi: PackageInfo = pm.getPackageInfo(mContext.packageName, 0)
        versionName = pi.versionName
    } catch (e: Exception) {
        Log.e("VersionInfo", "Exception", e)
    }
    return versionName
}

/**
 * 手机系统版本
 */
fun getSdkVersion(): String {
    return Build.VERSION.RELEASE
}

/**
 * 手机型号
 */
fun getPhoneMode():String {
    return Build.MODEL
}