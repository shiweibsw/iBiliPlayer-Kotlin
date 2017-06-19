package com.knightdavion.kotlin.ibiliplayer.ui.fragment

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.LinearLayout
import com.flyco.tablayout.SlidingTabLayout
import com.knightdavion.kotlin.ibiliplayer.App
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.adapter.HomePagerAdapter
import com.knightdavion.kotlin.ibiliplayer.data.remote.HttpManager
import com.knightdavion.kotlin.ibiliplayer.data.remote.OnResultCallBack
import com.knightdavion.kotlin.ibiliplayer.data.remote.subscriber.HttpSubscriber
import com.knightdavion.kotlin.ibiliplayer.model.SearchResultModle
import com.knightdavion.kotlin.ibiliplayer.ui.activitys.GameCentreActivity
import com.knightdavion.kotlin.ibiliplayer.ui.activitys.MainActivity
import com.knightdavion.kotlin.ibiliplayer.view.NoScrollViewPager
import com.wyt.searchbox.SearchFragment
import com.wyt.searchbox.custom.IOnSearchClickListener
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class HomePageFragment : SupportFragment() {
    var navigationHeaderLayout: LinearLayout? = null
    var mViewPager: NoScrollViewPager? = null
    var mSlidingTabs: SlidingTabLayout? = null
    var mToolbar: Toolbar? = null
    val searchFragment = SearchFragment.newInstance("搜索视频、番剧、up主或av号")!!

    companion object {
        fun newInstance(): HomePageFragment {
            return HomePageFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_home_page, container, false)
        navigationHeaderLayout = rootView.navigationHeaderLayout
        mToolbar = rootView.toolbar
        mViewPager = rootView.viewPager
        mSlidingTabs = rootView.slidingTabs
        initView()
        return rootView
    }

    fun initView() {
        mToolbar?.title = ""
        (activity as MainActivity).setSupportActionBar(mToolbar)
        var mHomeAdapter: HomePagerAdapter = HomePagerAdapter(childFragmentManager, App.instance)
        mViewPager?.adapter = mHomeAdapter
        mSlidingTabs?.setViewPager(mViewPager)
        mViewPager?.currentItem = 0
        navigationHeaderLayout?.setOnClickListener {
            val activity = activity
            if (activity is MainActivity) {
                activity.toggleDrawer()
            }
        }
        searchFragment.setOnSearchClickListener(object : IOnSearchClickListener {
            override fun OnSearchClick(keyword: String) {
                activity.toast(keyword)
            }
            override fun OnTextChanged(keyword: String) {
                HttpManager.getSearchSuggests(HttpSubscriber<SearchResultModle>(object : OnResultCallBack<SearchResultModle> {
                    override fun onSuccess(model: SearchResultModle) {
                        val suggests = mutableListOf<String>()
                        if (model.upuser != null) suggests.add(model.upuser[0].name)
                        if (model.bangumi != null) suggests.add(model.bangumi[0].name)
                        suggests.addAll(model.suggest)
                        searchFragment.setSuggests(suggests)
                    }
                    override fun onError(code: Int, errorMsg: String) {
                    }
                }), keyword)

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_action_game -> {
                activity.startActivity<GameCentreActivity>()
            }
            R.id.id_action_download -> {
            }
            R.id.id_action_search -> {
                searchFragment.show(activity.supportFragmentManager, SearchFragment.TAG);
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
