package com.knightdavion.kotlin.ibiliplayer.ui

import android.support.v4.widget.SwipeRefreshLayout
import android.util.TypedValue
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.extensions.ctx

/**
 * Created by shiwei on 2017/6/17.
 */
interface SwipeRefreshManager {

    val mSwipeRefreshLayout: SwipeRefreshLayout

    fun initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(*intArrayOf(R.color.refresh_holo_blue_light, R.color.refresh_holo_orange_light, R.color.refresh_holo_green_light, R.color.refresh_holo_red_light))
        mSwipeRefreshLayout.setProgressViewOffset(false, 0,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, mSwipeRefreshLayout.ctx.resources.displayMetrics).toInt())
    }

    fun setResreshing(isRefresh: Boolean) {
        mSwipeRefreshLayout.isRefreshing = isRefresh
    }

}