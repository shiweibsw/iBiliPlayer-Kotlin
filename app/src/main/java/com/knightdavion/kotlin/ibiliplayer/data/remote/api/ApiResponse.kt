package com.knightdavion.kotlin.ibiliplayer.data.remote.api

class ApiResponse<T> {
    private var error_code: String? = null
    private var reason: String? = null
    private var result: T? = null

    fun getCode(): String {
        return error_code!!
    }

    fun setCode(code: String) {
        this.error_code = code
    }

    fun getMsg(): String {
        return reason!!
    }

    fun setMsg(msg: String) {
        this.reason = msg
    }

    fun getDatas(): T {
        return result!!
    }

    fun setDatas(datas: T) {
        this.result = datas
    }

    override fun toString(): String {
        val sb = StringBuffer()
        sb.append("error_code=$error_code reason=$reason")
        if (null != result) {
            sb.append(" result:" + result.toString())
        }
        return sb.toString()
    }
}