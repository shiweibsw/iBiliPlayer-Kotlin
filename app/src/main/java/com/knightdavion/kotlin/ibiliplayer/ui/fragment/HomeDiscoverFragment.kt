package com.knightdavion.kotlin.ibiliplayer.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.data.remote.HttpManager
import com.knightdavion.kotlin.ibiliplayer.data.remote.OnResultCallBack
import com.knightdavion.kotlin.ibiliplayer.data.remote.subscriber.HttpSubscriber
import com.knightdavion.kotlin.ibiliplayer.model.DiscoverTagModel
import com.knightdavion.kotlin.ibiliplayer.model.SearchResultModle
import com.wyt.searchbox.SearchFragment
import com.wyt.searchbox.custom.IOnSearchClickListener
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class HomeDiscoverFragment : SupportFragment() {
    val searchFragment = SearchFragment.newInstance("搜索视频、番剧、up主或av号")!!

    companion object {
        fun newInstance(): HomeDiscoverFragment {
            return HomeDiscoverFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
        initSearch()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_discover, container, false)
        rootView.find<TextView>(R.id.searchEdit).setOnClickListener { searchFragment.show(activity.supportFragmentManager, SearchFragment.TAG); }
        return rootView
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        Log.e("TAG", "loaded")
        getDiscoverTagList()
    }
    fun getDiscoverTagList(){
        HttpManager.getDiscoverTagList(HttpSubscriber<DiscoverTagModel>(object : OnResultCallBack<DiscoverTagModel>{
            override fun onSuccess(model: DiscoverTagModel) {
                Log.e("TAG",model.toString())
            }
            override fun onError(code: Int, errorMsg: String) {
            }
        }))
    }

    fun initSearch() {
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
                        if (model.suggest != null) suggests.addAll(model.suggest)
                        searchFragment.setSuggests(suggests)
                    }

                    override fun onError(code: Int, errorMsg: String) {
                    }
                }), keyword)

            }
        })
    }
}
