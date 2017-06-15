package com.knightdavion.kotlin.ibiliplayer.ui

import android.view.View
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.style.Wave

/**
 * Created by shiwei on 2017/6/15.
 */
interface ProgressBarManager {

    val loadingBar: SpinKitView

    fun showLoadingBar() {
        loadingBar.setIndeterminateDrawable(Wave())
        loadingBar.visibility = View.VISIBLE
    }

    fun hideLoadingBar() {
        loadingBar.visibility = View.GONE
    }
}