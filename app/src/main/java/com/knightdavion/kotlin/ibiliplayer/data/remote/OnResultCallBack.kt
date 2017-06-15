package com.knightdavion.kotlin.ibiliplayer.data.remote



/**
 * Created by shiwei on 2017/6/15.
 */
interface OnResultCallBack<in T> {

    fun onSuccess(t: T)

    fun onError(code: Int, errorMsg: String)

}