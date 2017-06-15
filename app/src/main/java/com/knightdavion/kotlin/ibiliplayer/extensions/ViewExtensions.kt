package com.knightdavion.kotlin.ibiliplayer.extensions

import android.content.Context
import android.view.View

/**
 * Created by shiwei on 2017/6/14.
 */
val View.ctx: Context
    get() = context

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}