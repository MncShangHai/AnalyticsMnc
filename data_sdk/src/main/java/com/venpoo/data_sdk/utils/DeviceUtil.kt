package com.venpoo.data_sdk.utils

import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import android.text.TextUtils
import com.venpoo.data_sdk.AnyalyticsManger.context
import java.lang.reflect.Method

/**
 * Created by zachary on 2020/04/02.
 * 获取设备信息
 */
object DeviceUtil {// No sim
    /**
     * 获取网络运营商名称
     *
     * 中国移动、如中国联通、中国电信
     *
     * @return 运营商名称
     */
    val networkOperatorName: String
        get() {
            var opeType = "unknown"
            // No sim
            if (!hasSim(context)) {
                return opeType
            }
            val tm: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val operator: String = tm.getSimOperator()
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

}