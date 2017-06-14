package com.knightdavion.kotlin.ibiliplayer.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.knightdavion.kotlin.ibiliplayer.R

class HomePageFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_home_page, container, false)
    }

    companion object {
        fun newInstance(): HomePageFragment {
            return HomePageFragment()
        }
    }
}
