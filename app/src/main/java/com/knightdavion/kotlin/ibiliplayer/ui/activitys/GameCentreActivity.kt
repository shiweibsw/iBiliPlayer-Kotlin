package com.knightdavion.kotlin.ibiliplayer.ui.activitys

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import com.github.ybq.android.spinkit.SpinKitView
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.data.remote.HttpManager
import com.knightdavion.kotlin.ibiliplayer.data.remote.OnResultCallBack
import com.knightdavion.kotlin.ibiliplayer.data.remote.subscriber.HttpSubscriber
import com.knightdavion.kotlin.ibiliplayer.model.TestBean
import com.knightdavion.kotlin.ibiliplayer.ui.BaseActivity
import com.knightdavion.kotlin.ibiliplayer.ui.ProgressBarManager
import com.knightdavion.kotlin.ibiliplayer.ui.ToolBarManager
import org.jetbrains.anko.find


/**
 * Created by shiwei on 2017/6/14.
 */
class GameCentreActivity : BaseActivity(), ToolBarManager, ProgressBarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override val progressBar by lazy { find<SpinKitView>(R.id.psBar) }

    var mHttpObserver: HttpSubscriber<TestBean>? = null

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
        showProgressBar()
        mHttpObserver = HttpSubscriber<TestBean>(object : OnResultCallBack<TestBean> {
            override fun onSuccess(tb: TestBean) {
                Log.e("TAG", tb.toString())
            }

            override fun onError(code: Int, errorMsg: String) {
                Log.e("TAG", "code:$code errorMsg:$errorMsg")
            }
        })
        HttpManager.getDatasWithCache(mHttpObserver as HttpSubscriber<TestBean>, 1, 10, "json1", true)
    }

}