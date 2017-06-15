package com.knightdavion.kotlin.ibiliplayer.data.remote.subscriber

import com.google.gson.stream.MalformedJsonException
import com.knightdavion.kotlin.ibiliplayer.data.remote.ApiException
import com.knightdavion.kotlin.ibiliplayer.data.remote.OnResultCallBack
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Created by shiwei on 2017/6/15.
 */
class HttpSubscriber<T>(listener: OnResultCallBack<T>) : Observer<T> {

    var mOnResultListener: OnResultCallBack<T>? = null

    var mDisposable: Disposable? = null

    init {
        mOnResultListener = listener
    }

    override fun onNext(t: T) {
        mOnResultListener?.onSuccess(t);
    }

    override fun onSubscribe(d: Disposable?) {
        mDisposable = d;
    }

    override fun onError(e: Throwable) {
        if (e is CompositeException) {
            e.exceptions.forEach {
                if (it is SocketTimeoutException) {
                    mOnResultListener?.onError(ApiException.Code_TimeOut, ApiException.SOCKET_TIMEOUT_EXCEPTION);
                } else if (it is ConnectException) {
                    mOnResultListener?.onError(ApiException.Code_UnConnected, ApiException.CONNECT_EXCEPTION);
                } else if (it is UnknownHostException) {
                    mOnResultListener?.onError(ApiException.Code_UnConnected, ApiException.CONNECT_EXCEPTION);
                } else if (it is MalformedJsonException) {
                    mOnResultListener?.onError(ApiException.Code_MalformedJson, ApiException.MALFORMED_JSON_EXCEPTION);
                }
            }
        } else {
            var msg = e.message
            var code: Int = 0
            if (msg!!.contains("#")) {
                code = msg.split("#")[0].toInt()
                mOnResultListener?.onError(code, msg.split("#")[1]);
            } else {
                code = ApiException.Code_Default;
                mOnResultListener?.onError(code, msg);
            }
        }
    }

    override fun onComplete() {
    }

    fun unSubscribe() {
        if (mDisposable?.isDisposed!!) {
            mDisposable?.dispose()
        }
    }
}