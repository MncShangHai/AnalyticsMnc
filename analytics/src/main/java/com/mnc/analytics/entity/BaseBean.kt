package com.mnc.analytics.entity

/**
 * http请求基类
 *
 */
open class BaseBean {
    var success: Boolean? = null
    var error_code: Int? = null
    var error_message: String? = null
}
