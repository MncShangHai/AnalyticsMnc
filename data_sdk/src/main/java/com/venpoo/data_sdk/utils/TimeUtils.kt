package com.venpoo.data_sdk.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @ClassName TimeUtils
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/3/1 14:30
 */
object TimeUtils {
    /**
     * 获取当前时间
     * @return
     */
    val nowTime: String
        get() {
            val simpleDateFormat = SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss")
            val date = Date(System.currentTimeMillis())
            return simpleDateFormat.format(date)
        }

    /**
     * 获取时间戳
     *
     * @return 获取时间戳
     */
    val timeString: String
        get() {
            val df = SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss")
            val calendar = Calendar.getInstance()
            return df.format(calendar.time)
        }

    /**
     * 时间转换为时间戳
     * @param time:需要转换的时间
     * @return
     */
    fun dateToStamp(time: String?): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val ts = date!!.time
        return ts.toString()
    }

    /**
     * 时间戳转换为字符串
     * @param time:时间戳
     * @return
     */
    operator fun times(time: String): String {
        val sdr = SimpleDateFormat("yyyy年MM月dd日 HH时mm分")
        val lcc = java.lang.Long.valueOf(time)
        val i = time.toInt()
        return sdr.format(Date(i * 1000L))
    }

    /**
     * 获取距现在某一小时的时刻
     * @param hour hour=-1为上一个小时，hour=1为下一个小时
     * @return
     */
    fun getLongTime(hour: Int): String {
        val c = Calendar.getInstance() // 当时的日期和时间      
        val h: Int // 需要更改的小时      
        h = c[Calendar.HOUR_OF_DAY] - hour
        c[Calendar.HOUR_OF_DAY] = h
        val df = SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss")
        Log.v("time", df.format(c.time))
        return df.format(c.time)
    }

    /**
     * 比较时间大小
     * @param str1：要比较的时间
     * @param str2：要比较的时间
     * @return
     */
    fun isDateOneBigger(str1: String?, str2: String?): Boolean {
        var isBigger = false
        val sdf = SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss")
        var dt1: Date? = null
        var dt2: Date? = null
        try {
            dt1 = sdf.parse(str1)
            dt2 = sdf.parse(str2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (dt1!!.time > dt2!!.time) {
            isBigger = true
        } else if (dt1.time < dt2.time) {
            isBigger = false
        }
        return isBigger
    }
}