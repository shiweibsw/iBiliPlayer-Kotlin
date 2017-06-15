package com.knightdavion.kotlin.ibiliplayer.data.remote

import java.lang.RuntimeException

/**
 * Created by shiwei on 2017/6/15.
 */
class ApiException(resultCode: Int, msg: String) : RuntimeException(getApiExceptionMessage(resultCode, msg)) {
    companion object {
        val Code_TimeOut = 1000
        val Code_UnConnected = 1001
        val Code_MalformedJson = 1020
        val Code_Default = 1003
        val CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态"
        val SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试"
        val MALFORMED_JSON_EXCEPTION = "数据解析错误"
    }

    init {
        var result = getApiExceptionMessage(resultCode, msg)
    }
}

fun getApiExceptionMessage(resultCode: Int, msg: String): String {
    val message: String
    when (resultCode) {
        else -> message = resultCode.toString() + "#" + msg
    }
    return message
}
