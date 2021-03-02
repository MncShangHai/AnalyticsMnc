package com.venpoo.data_sdk.http

import android.os.SystemClock
import android.text.TextUtils
import android.util.Log
import okhttp3.*
import okio.Buffer
import java.io.IOException
import java.util.*

/**
 * @ClassName LogInterceptor
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/3/2 10:26
 */
class LogInterceptor : Interceptor {
    companion object {
        var TAG = LogInterceptor::class.java.simpleName
        private val headerIgnoreMap = HashMap<String, String>()

        init {
            headerIgnoreMap["Host"] = ""
            headerIgnoreMap["Connection"] = ""
            headerIgnoreMap["Accept-Encoding"] = ""
        }
    }

    protected fun log(message: String?) {
        Log.d(TAG, message!!)
    }

    private fun isPlainText(mediaType: MediaType?): Boolean {
        if (null != mediaType) {
            var mediaTypeString = if (null != mediaType) mediaType.toString() else null
            if (!TextUtils.isEmpty(mediaTypeString)) {
                mediaTypeString = mediaTypeString!!.toLowerCase()
                if (mediaTypeString.contains("text") || mediaTypeString.contains("application/json")) {
                    return true
                }
            }
        }
        return false
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val startTime = SystemClock.elapsedRealtime()
        val response: Response = chain.proceed(chain.request())
        val endTime = SystemClock.elapsedRealtime()
        val duration = endTime - startTime


        //url
        val url = request.url.toString()
        log("----------Request Start----------")
        log("" + request.method + " " + url)

        //headers
        val headers = request.headers
        if (null != headers) {
            var i = 0
            val count = headers.size
            while (i < count) {
                if (!headerIgnoreMap.containsKey(headers.name(i))) {
                    log(headers.name(i) + ": " + headers.value(i))
                }
                i++
            }
        }

        //param
        val requestBody = request.body
        val paramString = readRequestParamString(requestBody)
        if (!TextUtils.isEmpty(paramString)) {
            log("Params:$paramString")
        }

        //response
        val responseBody = response.body
        var responseString = ""
        if (null != responseBody) {
            responseString = if (isPlainText(responseBody.contentType())) {
                readContent(response)
            } else {
                "other-type=" + responseBody.contentType()
            }
        }
        log("Response Body:$responseString")
        log("Time:$duration ms")
        log("----------Request End----------")
        return response
    }

    private fun readRequestParamString(requestBody: RequestBody?): String {
        val paramString: String
        if (requestBody is MultipartBody) { //判断是否有文件
            val sb = StringBuilder()
            val parts: List<MultipartBody.Part> = requestBody.parts
            var partBody: RequestBody
            var i = 0
            val size = parts.size
            while (i < size) {
                partBody = parts[i].body
                if (null != partBody) {
                    if (sb.length > 0) {
                        sb.append(",")
                    }
                    if (isPlainText(partBody.contentType())) {
                        sb.append(readContent(partBody))
                    } else {
                        sb.append("other-param-type=").append(partBody.contentType())
                    }
                }
                i++
            }
            paramString = sb.toString()
        } else {
            paramString = readContent(requestBody)
        }
        return paramString
    }

    private fun readContent(response: Response?): String {
        var content = ""
        response?.let {
            try {
                //不知道为啥报错=====
                content = response.peekBody(Long.MAX_VALUE).string()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return content
    }

    private fun readContent(body: RequestBody?): String {
        if (body == null) {
            return ""
        }
        val buffer = Buffer()
        try {
            //小于2m
            if (body.contentLength() <= 2 * 1024 * 1024) {
                body.writeTo(buffer)
            } else {
                return "content is more than 2M"
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return buffer.readUtf8()
    }
}