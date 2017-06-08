package com.knightdavion.kotlin.ibiliplayer.ui

import android.support.v7.app.AppCompatActivity
import com.knightdavion.kotlin.ibiliplayer.extensions.DelegatesExt

/**
 * Created by shiwei on 2017/6/8.
 */
open class BaseActivity : AppCompatActivity() {
    companion object {
        val LOGIN_STATUS = "LOGIN_STATUS"
        val DEFAULT_LOGIN_STATUS = false
    }

    var isLogin: Boolean by DelegatesExt.preference(this, LOGIN_STATUS, DEFAULT_LOGIN_STATUS)

}