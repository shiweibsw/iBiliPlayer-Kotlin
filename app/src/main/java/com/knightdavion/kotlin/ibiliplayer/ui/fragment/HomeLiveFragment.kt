package com.knightdavion.kotlin.ibiliplayer.ui.fragment

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.data.remote.HttpManager
import com.knightdavion.kotlin.ibiliplayer.data.remote.OnResultCallBack
import com.knightdavion.kotlin.ibiliplayer.data.remote.subscriber.HttpSubscriber
import com.knightdavion.kotlin.ibiliplayer.model.LiveTypeModel
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
        mViewPager = rootView.viewPager

        HttpManager.getLiveTypes(HttpSubscriber<List<LiveTypeModel>>(object : OnResultCallBack<List<LiveTypeModel>> {
            override fun onSuccess(list: List<LiveTypeModel>) {
                list.forEach {
                    Log.e("TAG", it.toString())
                }
            }
            override fun onError(code: Int, errorMsg: String) {
                Log.e("TAG", errorMsg)
            }
        }))


        return rootView
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        Log.e("TAG", "loaded")
    }
}
