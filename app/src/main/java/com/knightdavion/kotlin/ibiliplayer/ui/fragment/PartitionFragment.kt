package com.knightdavion.kotlin.ibiliplayer.ui.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.adapter.PartitionAdapter
import com.knightdavion.kotlin.ibiliplayer.adapter.PartitionHeaderAdapter
import com.knightdavion.kotlin.ibiliplayer.model.PartitionItem
import kotlinx.android.synthetic.main.fragment_partition.*
import me.yokeyword.fragmentation.SupportFragment


class PartitionFragment : SupportFragment() {
    var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    var partitionItems = mutableListOf<PartitionItem>()

    companion object {
        fun newInstance(): PartitionFragment {
            return PartitionFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
        partitionItems.add(PartitionItem("直播", R.drawable.ic_category_live))
        partitionItems.add(PartitionItem("番剧", R.drawable.ic_category_t13))
        partitionItems.add(PartitionItem("动画", R.drawable.ic_category_t1))
        partitionItems.add(PartitionItem("国创", R.drawable.ic_category_t167))
        partitionItems.add(PartitionItem("音乐", R.drawable.ic_category_t3))
        partitionItems.add(PartitionItem("舞蹈", R.drawable.ic_category_t129))
        partitionItems.add(PartitionItem("游戏", R.drawable.ic_category_t4))
        partitionItems.add(PartitionItem("科技", R.drawable.ic_category_t36))
        partitionItems.add(PartitionItem("生活", R.drawable.ic_category_t160))
        partitionItems.add(PartitionItem("鬼畜", R.drawable.ic_category_t119))
        partitionItems.add(PartitionItem("时尚", R.drawable.ic_category_t155))
        partitionItems.add(PartitionItem("广告", R.drawable.ic_category_t165))
        partitionItems.add(PartitionItem("娱乐", R.drawable.ic_category_t5))
        partitionItems.add(PartitionItem("电影", R.drawable.ic_category_t23))
        partitionItems.add(PartitionItem("电视剧", R.drawable.ic_category_t11))
        partitionItems.add(PartitionItem("游戏中心", R.drawable.ic_category_game_center))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_partition, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var headerRv = RecyclerView(activity)
        headerRv.layoutManager = GridLayoutManager(activity, 4)
        headerRv.adapter = PartitionHeaderAdapter(R.layout.layout_partition_header_item, partitionItems)
        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        var mAdapter = PartitionAdapter(R.layout.layout_partition_header_item, null)
        mRecyclerView.adapter = mAdapter
        mAdapter.setHeaderView(headerRv)
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
    }
}
