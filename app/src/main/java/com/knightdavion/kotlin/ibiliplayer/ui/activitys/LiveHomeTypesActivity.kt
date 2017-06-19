package com.knightdavion.kotlin.ibiliplayer.ui.activitys

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import com.github.ybq.android.spinkit.SpinKitView
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.adapter.LiveHomeTypesAdapter
import com.knightdavion.kotlin.ibiliplayer.data.remote.HttpManager
import com.knightdavion.kotlin.ibiliplayer.data.remote.OnResultCallBack
import com.knightdavion.kotlin.ibiliplayer.data.remote.subscriber.HttpSubscriber
import com.knightdavion.kotlin.ibiliplayer.model.LiveHomeTypeModel
import com.knightdavion.kotlin.ibiliplayer.ui.BaseActivity
import com.knightdavion.kotlin.ibiliplayer.ui.LoadingBarManager
import com.knightdavion.kotlin.ibiliplayer.ui.ToolBarManager
import kotlinx.android.synthetic.main.activity_live_home_types.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast


/**
 * Created by shiwei on 2017/6/14.
 */
class LiveHomeTypesActivity : BaseActivity(), ToolBarManager, LoadingBarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override val loadingBar by lazy { find<SpinKitView>(R.id.psBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_home_types)
        initToolBar()
        initView()
        loadLiveHomeTypesDatas();
    }

    fun initToolBar() {
        toolbarTitle = "全部分类"
        enableHomeAsUp { onBackPressed() }
    }

    fun initView() {
        mRecyclerView.layoutManager = GridLayoutManager(this, 3)
    }

    fun loadLiveHomeTypesDatas() {
        showLoadingBar()
        HttpManager.getLiveHomeTypes(HttpSubscriber<List<LiveHomeTypeModel>>(object : OnResultCallBack<List<LiveHomeTypeModel>> {
            override fun onSuccess(list: List<LiveHomeTypeModel>) {
                val mAdapter = LiveHomeTypesAdapter(R.layout.layout_live_home_types_iteml, list)
                mRecyclerView.adapter = mAdapter
                hideLoadingBar()
            }
            override fun onError(code: Int, errorMsg: String) {
                hideLoadingBar()
                toast(errorMsg)
            }
        }),false)
    }
}
