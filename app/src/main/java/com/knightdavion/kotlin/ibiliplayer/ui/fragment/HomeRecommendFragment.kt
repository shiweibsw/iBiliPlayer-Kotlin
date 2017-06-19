package com.knightdavion.kotlin.ibiliplayer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.knightdavion.kotlin.ibiliplayer.R
import me.yokeyword.fragmentation.SupportFragment


class HomeRecommendFragment : SupportFragment() {

    companion object {
        fun newInstance(): HomeRecommendFragment {
            return HomeRecommendFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_recommended, container, false)

        return rootView
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
    }
}
