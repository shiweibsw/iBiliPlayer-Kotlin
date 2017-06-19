package com.knightdavion.kotlin.ibiliplayer.ui.activitys

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.SpinKitView
import com.knightdavion.kotlin.ibiliplayer.App
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.adapter.GameCenterAdapter
import com.knightdavion.kotlin.ibiliplayer.adapter.GameCenterHeaderAdapter
import com.knightdavion.kotlin.ibiliplayer.data.remote.HttpManager
import com.knightdavion.kotlin.ibiliplayer.data.remote.OnResultCallBack
import com.knightdavion.kotlin.ibiliplayer.data.remote.subscriber.HttpSubscriber
import com.knightdavion.kotlin.ibiliplayer.model.BookGiftBean
import com.knightdavion.kotlin.ibiliplayer.model.GameCenterModle
import com.knightdavion.kotlin.ibiliplayer.model.VipGameInfo
import com.knightdavion.kotlin.ibiliplayer.ui.BaseActivity
import com.knightdavion.kotlin.ibiliplayer.ui.LoadingBarManager
import com.knightdavion.kotlin.ibiliplayer.ui.ToolBarManager
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.activity_game_center.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


/**
 * Created by shiwei on 2017/6/14.
 */
class GameCentreActivity : BaseActivity(), ToolBarManager, LoadingBarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override val loadingBar by lazy { find<SpinKitView>(R.id.psBar) }
    var mAdapter: GameCenterAdapter? = null
    var headerList: MutableList<BookGiftBean> = mutableListOf()
    var mHttpObserver: HttpSubscriber<GameCenterModle>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_center)
        initToolBar()
        initView()
    }

    fun initToolBar() {
        toolbarTitle = "游戏中心"
        enableHomeAsUp { onBackPressed() }
    }

    fun initView() {
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(this).color(resources.getColor(R.color.driver_line)).sizeResId(R.dimen.divider_height).build())
        getGameList()
    }

    fun getGameList() {
        showLoadingBar()
        HttpManager.getGameList(HttpSubscriber<GameCenterModle>(object : OnResultCallBack<GameCenterModle> {
            override fun onSuccess(tb: GameCenterModle) {
                tb.book_gift.forEach { headerList?.add(it) }
                mAdapter = GameCenterAdapter(R.layout.layout_game_center_item, tb.game_list)
                mRecyclerView.adapter = mAdapter
                initHeaderView()
            }

            override fun onError(code: Int, errorMsg: String) {
            }
        }), this, "gamecenter.json")
    }

    fun initHeaderView() {
        var headeView: View = LayoutInflater.from(this).inflate(R.layout.layout_game_center_header, null)
        var banner = headeView.find<ImageView>(R.id.banner)
        var headerRecyclerView = headeView.find<RecyclerView>(R.id.headerRecyclerView)
        mAdapter?.addHeaderView(headeView)
        headerRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var headerAdapter = GameCenterHeaderAdapter(R.layout.layout_game_center_header_item, headerList)
        headerRecyclerView.adapter = headerAdapter
        getHeaderImage(banner)
    }

    fun getHeaderImage(banner: ImageView) {
        HttpManager.getVipGameInfo(HttpSubscriber<VipGameInfo>(object : OnResultCallBack<VipGameInfo> {
            override fun onSuccess(tb: VipGameInfo) {
                hideLoadingBar()
                Glide.with(App.instance).load(tb.imgPath).into(banner)
                banner.setOnClickListener {
                    startActivity<WebPageActivity>(
                            WebPageActivity.TITLE to "年度大会员礼包", WebPageActivity.URL to tb.link
                    )
                }
            }

            override fun onError(code: Int, errorMsg: String) {
                hideLoadingBar()
            }
        }), false)
    }

}