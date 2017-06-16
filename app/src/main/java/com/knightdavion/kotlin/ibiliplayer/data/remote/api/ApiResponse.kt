package com.knightdavion.kotlin.ibiliplayer.data.remote.api

class ApiResponse<T> {
    private var code: String? = null
    private var message: String? = null
    private var data: T? = null

    fun getCode(): String {
        return code!!
    }

    fun setCode(code: String) {
        this.code = code
    }

    fun getMsg(): String {
        return message!!
    }

    fun setMsg(msg: String) {
        this.message = msg
    }

    fun getDatas(): T {
        return data!!
    }

    fun setDatas(datas: T) {
        this.data = datas
    }

    override fun toString(): String {
        val sb = StringBuffer()
        sb.append("error_code=$code reason=$message")
        if (null != data) {
            sb.append(" result:" + data.toString())
        }
        return sb.toString()
    }
}