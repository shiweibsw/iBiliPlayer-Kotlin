package com.knightdavion.kotlin.ibiliplayer.ui.fragment

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.adapter.LiveHomeBaseAdapter
import com.knightdavion.kotlin.ibiliplayer.data.remote.HttpManager
import com.knightdavion.kotlin.ibiliplayer.data.remote.OnResultCallBack
import com.knightdavion.kotlin.ibiliplayer.data.remote.subscriber.HttpSubscriber
import com.knightdavion.kotlin.ibiliplayer.model.LiveHomeHotModle
import com.knightdavion.kotlin.ibiliplayer.model.LiveHomeModel
import com.knightdavion.kotlin.ibiliplayer.model.Partitions
import kotlinx.android.synthetic.main.fragment_live.view.*
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.find


class HomeLiveFragment : SupportFragment() {
    var mViewPager: ViewPager? = null
    var mRecyclerView: RecyclerView? = null
    var mBaseAdapter: LiveHomeBaseAdapter? = null
    val datas: MutableList<Partitions> = mutableListOf()

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
        mRecyclerView = rootView.mRecyclerView
        mRecyclerView?.layoutManager = LinearLayoutManager(activity)
        mBaseAdapter = LiveHomeBaseAdapter(R.layout.layout_live_home_base_item, datas)
        mRecyclerView?.adapter = mBaseAdapter
        return rootView
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        loadLiveHomeHotDatas()
    }

    fun initHeaderAndFooterView() {
        val headeView: View = LayoutInflater.from(activity).inflate(R.layout.layout_live_home_header, null)
        val footerView: View = LayoutInflater.from(activity).inflate(R.layout.layout_live_home_footer, null)
        mViewPager = headeView.find(R.id.viewPager)
        mBaseAdapter?.addHeaderView(headeView)
        mBaseAdapter?.addFooterView(footerView)
    }

    fun loadLiveHomeHotDatas() {
        HttpManager.getLiveHomeHotDatas(HttpSubscriber<LiveHomeHotModle>(object : OnResultCallBack<LiveHomeHotModle> {
            override fun onSuccess(model: LiveHomeHotModle) {
                var p: Partitions = Partitions(model.recommend_data.partition, model.recommend_data.lives)
                datas.add(p)
                loadLiveHomeDatas()
            }
            override fun onError(code: Int, errorMsg: String) {
            }
        }))
    }
    fun loadLiveHomeDatas() {
        HttpManager.getLiveHomeDatas(HttpSubscriber<LiveHomeModel>(object : OnResultCallBack<LiveHomeModel> {
            override fun onSuccess(model: LiveHomeModel) {
                datas.addAll(model.partitions)
                mBaseAdapter?.notifyDataSetChanged()
                initHeaderAndFooterView()
            }
            override fun onError(code: Int, errorMsg: String) {
            }
        }))
    }
}
