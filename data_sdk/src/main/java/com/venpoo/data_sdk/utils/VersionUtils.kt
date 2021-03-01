package com.venpoo.data_sdk.utils

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log
import com.venpoo.data_sdk.AnyalyticsManger

/**
 * @ClassName VersionUtils
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2020/12/9 15:57
 */
object VersionUtils {

    /**
     * 返回当前程序版本号
     */
    fun getAppVersionCode(): Int {
        var versioncode = 0
        try {
            val pm: PackageManager = AnyalyticsManger.context.packageManager
            val pi: PackageInfo = pm.getPackageInfo(AnyalyticsManger.context.packageName, 0)
            // versionName = pi.versionName;
            versioncode = pi.versionCode
            Log.d("VersionInfo",versioncode.toString())
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
            val pm: PackageManager = AnyalyticsManger.context.packageManager
            val pi: PackageInfo = pm.getPackageInfo(AnyalyticsManger.context.packageName, 0)
            versionName = pi.versionName
            Log.d("VersionInfo",versionName)
        } catch (e: Exception) {
            Log.e("VersionInfo", "Exception", e)
        }
        return versionName
    }

}