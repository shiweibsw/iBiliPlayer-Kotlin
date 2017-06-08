package com.knightdavion.kotlin.ibiliplayer

import android.app.Application
import com.knightdavion.kotlin.ibiliplayer.extensions.DelegatesExt

/**
 * Created by knightdavion on 2017/6/8.
 */
class App : Application() {
    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}