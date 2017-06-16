package com.knightdavion.kotlin.ibiliplayer.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.ui.fragment.HomeLiveFragment
import com.knightdavion.kotlin.ibiliplayer.ui.fragment.HomeRecommendedFragment
import com.knightdavion.kotlin.ibiliplayer.ui.fragment.NullFragment

/**
 * Created by shiwei on 2017/6/14.
 */
class HomePagerAdapter(fm: FragmentManager, context: Context) : FragmentPagerAdapter(fm) {
    private var titles: Array<String?> = arrayOfNulls<String>(0)
    private var fragments: Array<Fragment?> = arrayOfNulls<Fragment>(0)

    init {
        titles = context.resources.getStringArray(R.array.sections)
        fragments = arrayOfNulls<Fragment>(titles.size)
    }

    override fun getItem(p0: Int): Fragment? {
        if (fragments[p0] == null) {
            when (p0) {
                0 -> {
                    fragments[p0] = HomeLiveFragment.newInstance()
                }
                1 -> {
                    fragments[p0] = HomeRecommendedFragment.newInstance()
                }
                2 -> {
                    fragments[p0] = NullFragment.newInstance()
                }
                3 -> {
                    fragments[p0] = NullFragment.newInstance()
                }
                4 -> {
                    fragments[p0] = NullFragment.newInstance()
                }
                5 -> {
                    fragments[p0] = NullFragment.newInstance()
                }
            }
        }
        return fragments[p0]
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int) = titles[position]
}