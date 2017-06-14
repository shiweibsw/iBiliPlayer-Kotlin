package com.knightdavion.kotlin.ibiliplayer.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet

class NoScrollViewPager : ViewPager {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun scrollTo(x: Int, y: Int) {
        super.scrollTo(x, y)
    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        super.setCurrentItem(item, smoothScroll)
    }

    override fun setCurrentItem(item: Int) {

        super.setCurrentItem(item, false)
    }
}
