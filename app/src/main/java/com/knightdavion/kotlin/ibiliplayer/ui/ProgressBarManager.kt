package com.knightdavion.kotlin.ibiliplayer.ui

import android.view.View
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.style.Wave

/**
 * Created by shiwei on 2017/6/15.
 */
interface ProgressBarManager {

    val progressBar: SpinKitView

    fun showProgressBar() {
        progressBar.setIndeterminateDrawable(Wave())
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}