package com.venpoo.data_sdk.utils

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Proxy
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException

object NetWorkUtils {
    /**
     * 网络类型 - 无连接
     */
    const val NETWORK_TYPE_NO_CONNECTION = -1231545315
    const val NETWORK_TYPE_WIFI = "wifi"
    const val NETWORK_TYPE_3G = "eg"
    const val NETWORK_TYPE_2G = "2g"
    const val NETWORK_TYPE_WAP = "wap"
    const val NETWORK_TYPE_UNKNOWN = "unknown"
    const val NETWORK_TYPE_DISCONNECT = "disconnect"

    /**
     * Get network type
     *
     * @param context context
     * @return 网络状态
     */
    fun getNetworkType(context: Context): Int {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val networkInfo = connectivityManager?.activeNetworkInfo
        return networkInfo?.type ?: -1
    }

    /**
     * Get network type name
     *
     * @param context context
     * @return NetworkTypeName
     */
    fun getNetworkTypeName(context: Context): String {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo: NetworkInfo
        var type = NETWORK_TYPE_DISCONNECT
        if (manager.activeNetworkInfo.also { networkInfo = it!! } == null) {
            return type
        }
        if (networkInfo.isConnected) {
            val typeName = networkInfo.typeName
            type = if ("WIFI".equals(typeName, ignoreCase = true)) {
                NETWORK_TYPE_WIFI
            } else if ("MOBILE".equals(typeName, ignoreCase = true)) {
                val proxyHost = Proxy.getDefaultHost()
                if (TextUtils.isEmpty(proxyHost)) if (isFastMobileNetwork(context)) NETWORK_TYPE_3G else NETWORK_TYPE_2G else NETWORK_TYPE_WAP
            } else {
                NETWORK_TYPE_UNKNOWN
            }
        }
        return type
    }

    /**
     * Whether is fast mobile network
     *
     * @param context context
     * @return FastMobileNetwork
     */
    private fun isFastMobileNetwork(context: Context): Boolean {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager ?: return false
        return if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            val netWorkType = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                telephonyManager.dataNetworkType
            } else {
                telephonyManager.networkType
            }
            when (netWorkType) {
                TelephonyManager.NETWORK_TYPE_1xRTT -> false
                TelephonyManager.NETWORK_TYPE_CDMA -> false
                TelephonyManager.NETWORK_TYPE_EDGE -> false
                TelephonyManager.NETWORK_TYPE_EVDO_0 -> true
                TelephonyManager.NETWORK_TYPE_EVDO_A -> true
                TelephonyManager.NETWORK_TYPE_GPRS -> false
                TelephonyManager.NETWORK_TYPE_HSDPA -> true
                TelephonyManager.NETWORK_TYPE_HSPA -> true
                TelephonyManager.NETWORK_TYPE_HSUPA -> true
                TelephonyManager.NETWORK_TYPE_UMTS -> true
                TelephonyManager.NETWORK_TYPE_EHRPD -> true
                TelephonyManager.NETWORK_TYPE_EVDO_B -> true
                TelephonyManager.NETWORK_TYPE_HSPAP -> true
                TelephonyManager.NETWORK_TYPE_IDEN -> false
                TelephonyManager.NETWORK_TYPE_LTE -> true
                TelephonyManager.NETWORK_TYPE_UNKNOWN -> false
                else -> false
            }
        } else {
            false
        }
    }

    /**
     * 获取当前网络的状态
     *
     * @param context 上下文
     * @return 当前网络的状态。具体类型可参照NetworkInfo.State.CONNECTED、NetworkInfo.State.CONNECTED.DISCONNECTED等字段。当前没有网络连接时返回null
     */
    fun getCurrentNetworkState(context: Context): NetworkInfo.State? {
        val networkInfo = (context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager).activeNetworkInfo
        return networkInfo?.state
    }

    /**
     * 获取当前网络的类型
     *
     * @param context 上下文
     * @return 当前网络的类型。具体类型可参照ConnectivityManager中的TYPE_BLUETOOTH、TYPE_MOBILE、TYPE_WIFI等字段。当前没有网络连接时返回NetworkUtils.NETWORK_TYPE_NO_CONNECTION
     */
    fun getCurrentNetworkType(context: Context): Int {
        val networkInfo = (context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager).activeNetworkInfo
        return networkInfo?.type ?: NETWORK_TYPE_NO_CONNECTION
    }

    /**
     * 获取当前网络的具体类型
     *
     * @param context 上下文
     * @return 当前网络的具体类型。具体类型可参照TelephonyManager中的NETWORK_TYPE_1xRTT、NETWORK_TYPE_CDMA等字段。当前没有网络连接时返回NetworkUtils.NETWORK_TYPE_NO_CONNECTION
     */
    fun getCurrentNetworkSubtype(context: Context): Int {
        val networkInfo = (context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager).activeNetworkInfo
        return networkInfo?.subtype ?: NETWORK_TYPE_NO_CONNECTION
    }

    /**
     * 判断当前网络是否已经连接
     *
     * @param context 上下文
     * @return 当前网络是否已经连接。false：尚未连接
     */
    fun isConnectedByState(context: Context): Boolean {
        return getCurrentNetworkState(context) == NetworkInfo.State.CONNECTED
    }

    /**
     * 判断当前网络是否正在连接
     *
     * @param context 上下文
     * @return 当前网络是否正在连接
     */
    fun isConnectingByState(context: Context): Boolean {
        return getCurrentNetworkState(context) == NetworkInfo.State.CONNECTING
    }

    /**
     * 判断当前网络是否已经断开
     *
     * @param context 上下文
     * @return 当前网络是否已经断开
     */
    fun isDisconnectedByState(context: Context): Boolean {
        return getCurrentNetworkState(context) ==
                NetworkInfo.State.DISCONNECTED
    }

    /**
     * 判断当前网络是否正在断开
     *
     * @param context 上下文
     * @return 当前网络是否正在断开
     */
    fun isDisconnectingByState(context: Context): Boolean {
        return getCurrentNetworkState(context) ==
                NetworkInfo.State.DISCONNECTING
    }

    /**
     * 判断当前网络是否已经暂停
     *
     * @param context 上下文
     * @return 当前网络是否已经暂停
     */
    fun isSuspendedByState(context: Context): Boolean {
        return getCurrentNetworkState(context) == NetworkInfo.State.SUSPENDED
    }

    /**
     * 判断当前网络是否处于未知状态中
     *
     * @param context 上下文
     * @return 当前网络是否处于未知状态中
     */
    fun isUnknownByState(context: Context): Boolean {
        return getCurrentNetworkState(context) == NetworkInfo.State.UNKNOWN
    }

    /**
     * 判断当前网络的类型是否是蓝牙
     *
     * @param context 上下文
     * @return 当前网络的类型是否是蓝牙。false：当前没有网络连接或者网络类型不是蓝牙
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    fun isBluetoothByType(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            false
        } else {
            getCurrentNetworkType(context) ==
                    ConnectivityManager.TYPE_BLUETOOTH
        }
    }

    /**
     * 判断当前网络的类型是否是虚拟网络
     *
     * @param context 上下文
     * @return 当前网络的类型是否是虚拟网络。false：当前没有网络连接或者网络类型不是虚拟网络
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    fun isDummyByType(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            false
        } else {
            getCurrentNetworkType(context) ==
                    ConnectivityManager.TYPE_DUMMY
        }
    }

    /**
     * 判断当前网络的类型是否是ETHERNET
     *
     * @param context 上下文
     * @return 当前网络的类型是否是ETHERNET。false：当前没有网络连接或者网络类型不是ETHERNET
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    fun isEthernetByType(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            false
        } else {
            getCurrentNetworkType(context) ==
                    ConnectivityManager.TYPE_ETHERNET
        }
    }

    /**
     * 判断当前网络的类型是否是移动网络
     *
     * @param context 上下文
     * @return 当前网络的类型是否是移动网络。false：当前没有网络连接或者网络类型不是移动网络
     */
    fun isMobileByType(context: Context): Boolean {
        return getCurrentNetworkType(context) ==
                ConnectivityManager.TYPE_MOBILE
    }

    /**
     * 判断当前网络的类型是否是MobileDun
     *
     * @param context 上下文
     * @return 当前网络的类型是否是MobileDun。false：当前没有网络连接或者网络类型不是MobileDun
     */
    fun isMobileDunByType(context: Context): Boolean {
        return getCurrentNetworkType(context) ==
                ConnectivityManager.TYPE_MOBILE_DUN
    }

    /**
     * 判断当前网络的类型是否是MobileHipri
     *
     * @param context 上下文
     * @return 当前网络的类型是否是MobileHipri。false：当前没有网络连接或者网络类型不是MobileHipri
     */
    fun isMobileHipriByType(context: Context): Boolean {
        return getCurrentNetworkType(context) ==
                ConnectivityManager.TYPE_MOBILE_HIPRI
    }

    /**
     * 判断当前网络的类型是否是MobileMms
     *
     * @param context 上下文
     * @return 当前网络的类型是否是MobileMms。false：当前没有网络连接或者网络类型不是MobileMms
     */
    fun isMobileMmsByType(context: Context): Boolean {
        return getCurrentNetworkType(context) ==
                ConnectivityManager.TYPE_MOBILE_MMS
    }

    /**
     * 判断当前网络的类型是否是MobileSupl
     *
     * @param context 上下文
     * @return 当前网络的类型是否是MobileSupl。false：当前没有网络连接或者网络类型不是MobileSupl
     */
    fun isMobileSuplByType(context: Context): Boolean {
        return getCurrentNetworkType(context) ==
                ConnectivityManager.TYPE_MOBILE_SUPL
    }

    /**
     * 判断当前网络的类型是否是Wifi
     *
     * @param context 上下文
     * @return 当前网络的类型是否是Wifi。false：当前没有网络连接或者网络类型不是wifi
     */
    fun isWifiByType(context: Context): Boolean {
        return getCurrentNetworkType(context) == ConnectivityManager.TYPE_WIFI
    }

    /**
     * 判断当前网络的类型是否是Wimax
     *
     * @param context 上下文
     * @return 当前网络的类型是否是Wimax。false：当前没有网络连接或者网络类型不是Wimax
     */
    fun isWimaxByType(context: Context): Boolean {
        return getCurrentNetworkType(context) == ConnectivityManager.TYPE_WIMAX
    }

    /**
     * 判断当前网络的具体类型是否是1XRTT
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是1XRTT。false：当前没有网络连接或者具体类型不是1XRTT
     */
    fun is1XRTTBySubtype(context: Context): Boolean {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_1xRTT
    }

    /**
     * 判断当前网络的具体类型是否是CDMA（Either IS95A or IS95B）
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是CDMA。false：当前没有网络连接或者具体类型不是CDMA
     */
    fun isCDMABySubtype(context: Context): Boolean {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_CDMA
    }

    /**
     * 判断当前网络的具体类型是否是EDGE
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是EDGE。false：当前没有网络连接或者具体类型不是EDGE
     */
    fun isEDGEBySubtype(context: Context): Boolean {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_EDGE
    }

    /**
     * 判断当前网络的具体类型是否是EHRPD
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是EHRPD。false：当前没有网络连接或者具体类型不是EHRPD
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun isEHRPDBySubtype(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            false
        } else {
            getCurrentNetworkSubtype(context) ==
                    TelephonyManager.NETWORK_TYPE_EHRPD
        }
    }

    /**
     * 判断当前网络的具体类型是否是EVDO_0
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是EVDO_0。false：当前没有网络连接或者具体类型不是EVDO_0
     */
    fun isEVDO_0BySubtype(context: Context): Boolean {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_EVDO_0
    }

    /**
     * 判断当前网络的具体类型是否是EVDO_A
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是EVDO_A。false：当前没有网络连接或者具体类型不是EVDO_A
     */
    fun isEVDO_ABySubtype(context: Context): Boolean {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_EVDO_A
    }

    /**
     * 判断当前网络的具体类型是否是EDGE
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是EVDO_B。false：当前没有网络连接或者具体类型不是EVDO_B
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    fun isEVDO_BBySubtype(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            false
        } else {
            getCurrentNetworkSubtype(context) ==
                    TelephonyManager.NETWORK_TYPE_EVDO_B
        }
    }

    /**
     * 判断当前网络的具体类型是否是GPRS
     * EVDO_Bam context 上下文
     *
     * @return false：当前网络的具体类型是否是GPRS。false：当前没有网络连接或者具体类型不是GPRS
     */
    fun isGPRSBySubtype(context: Context): Boolean {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_GPRS
    }

    /**
     * 判断当前网络的具体类型是否是HSDPA
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是HSDPA。false：当前没有网络连接或者具体类型不是HSDPA
     */
    fun isHSDPABySubtype(context: Context): Boolean {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_HSDPA
    }

    /**
     * 判断当前网络的具体类型是否是HSPA
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是HSPA。false：当前没有网络连接或者具体类型不是HSPA
     */
    fun isHSPABySubtype(context: Context): Boolean {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_HSPA
    }

    /**
     * 判断当前网络的具体类型是否是HSPAP
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是HSPAP。false：当前没有网络连接或者具体类型不是HSPAP
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    fun isHSPAPBySubtype(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            false
        } else {
            getCurrentNetworkSubtype(context) ==
                    TelephonyManager.NETWORK_TYPE_HSPAP
        }
    }

    /**
     * 判断当前网络的具体类型是否是HSUPA
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是HSUPA。false：当前没有网络连接或者具体类型不是HSUPA
     */
    fun isHSUPABySubtype(context: Context): Boolean {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_HSUPA
    }

    /**
     * 判断当前网络的具体类型是否是IDEN
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是IDEN。false：当前没有网络连接或者具体类型不是IDEN
     */
    fun isIDENBySubtype(context: Context): Boolean {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_IDEN
    }

    /**
     * 判断当前网络的具体类型是否是LTE
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是LTE。false：当前没有网络连接或者具体类型不是LTE
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun isLTEBySubtype(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            false
        } else {
            getCurrentNetworkSubtype(context) ==
                    TelephonyManager.NETWORK_TYPE_LTE
        }
    }

    /**
     * 判断当前网络的具体类型是否是UMTS
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是UMTS。false：当前没有网络连接或者具体类型不是UMTS
     */
    fun isUMTSBySubtype(context: Context): Boolean {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_UMTS
    }

    /**
     * 判断当前网络的具体类型是否是UNKNOWN
     *
     * @param context 上下文
     * @return false：当前网络的具体类型是否是UNKNOWN。false：当前没有网络连接或者具体类型不是UNKNOWN
     */
    fun isUNKNOWNBySubtype(context: Context): Boolean {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_UNKNOWN
    }

    /**
     * 判断当前网络是否是中国移动2G网络
     *
     * @param context 上下文
     * @return false：不是中国移动2G网络或者当前没有网络连接
     */
    fun isChinaMobile2G(context: Context): Boolean {
        return isEDGEBySubtype(context)
    }

    /**
     * 判断当前网络是否是中国联通2G网络
     *
     * @param context 上下文
     * @return false：不是中国联通2G网络或者当前没有网络连接
     */
    fun isChinaUnicom2G(context: Context): Boolean {
        return isGPRSBySubtype(context)
    }

    /**
     * 判断当前网络是否是中国联通3G网络
     *
     * @param context 上下文
     * @return false：不是中国联通3G网络或者当前没有网络连接
     */
    fun isChinaUnicom3G(context: Context): Boolean {
        return isHSDPABySubtype(context) || isUMTSBySubtype(context)
    }

    /**
     * 判断当前网络是否是中国电信2G网络
     *
     * @param context 上下文
     * @return false：不是中国电信2G网络或者当前没有网络连接
     */
    fun isChinaTelecom2G(context: Context): Boolean {
        return isCDMABySubtype(context)
    }

    /**
     * 判断当前网络是否是中国电信3G网络
     *
     * @param context 上下文
     * @return false：不是中国电信3G网络或者当前没有网络连接
     */
    fun isChinaTelecom3G(context: Context): Boolean {
        return isEVDO_0BySubtype(context) || isEVDO_ABySubtype(context) ||
                isEVDO_BBySubtype(context)
    }

    /**
     * 获取Wifi的状态，需要ACCESS_WIFI_STATE权限
     *
     * @param context 上下文
     * @return 取值为WifiManager中的WIFI_STATE_ENABLED、WIFI_STATE_ENABLING、WIFI_STATE_DISABLED、WIFI_STATE_DISABLING、WIFI_STATE_UNKNOWN之一
     * @throws Exception 没有找到wifi设备
     */
    @Throws(Exception::class)
    fun getWifiState(context: Context): Int {
        val wifiManager = context.getSystemService(
            Context.WIFI_SERVICE
        ) as WifiManager
        return wifiManager?.wifiState ?: throw Exception("wifi device not found!")
    }

    /**
     * 判断Wifi是否打开，需要ACCESS_WIFI_STATE权限
     *
     * @param context 上下文
     * @return true：打开；false：关闭
     */
    @Throws(Exception::class)
    fun isWifiOpen(context: Context): Boolean {
        val wifiState = getWifiState(context)
        return wifiState == WifiManager.WIFI_STATE_ENABLED || wifiState == WifiManager.WIFI_STATE_ENABLING
    }

    /**
     * 设置Wifi，需要CHANGE_WIFI_STATE权限
     *
     * @param context 上下文
     * @param enable wifi状态
     * @return 设置是否成功
     */
    @Throws(Exception::class)
    fun setWifi(context: Context, enable: Boolean): Boolean {
        //如果当前wifi的状态和要设置的状态不一样
        if (isWifiOpen(context) != enable) {
            (context.getSystemService(
                Context.WIFI_SERVICE
            ) as WifiManager).isWifiEnabled = enable
        }
        return true
    }

    /**
     * 判断移动网络是否打开，需要ACCESS_NETWORK_STATE权限
     *
     * @param context 上下文
     * @return true：打开；false：关闭
     */
    fun isMobileNetworkOpen(context: Context): Boolean {
        return (context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager).getNetworkInfo(
            ConnectivityManager.TYPE_MOBILE
        )!!.isConnected
    }


}