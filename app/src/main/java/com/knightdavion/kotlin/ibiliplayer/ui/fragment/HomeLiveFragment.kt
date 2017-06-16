package com.knightdavion.kotlin.ibiliplayer.ui.fragment

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.knightdavion.kotlin.ibiliplayer.R
import kotlinx.android.synthetic.main.fragment_live.view.*
import me.yokeyword.fragmentation.SupportFragment


class HomeLiveFragment : SupportFragment() {
    var mViewPager: ViewPager? = null
    companion object {
        fun newInstance(): HomeLiveFragment {
            return HomeLiveFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_live, container, false)
        mViewPager=rootView.viewPager

        return rootView
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        Log.e("TAG", "loaded")
    }
}
