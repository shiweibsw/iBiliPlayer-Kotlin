package com.knightdavion.kotlin.ibiliplayer.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.extensions.ctx
import com.knightdavion.kotlin.ibiliplayer.model.Partition
import com.knightdavion.kotlin.ibiliplayer.model.Partitions
import org.jetbrains.anko.find
import org.jetbrains.anko.toast


/**
 * Created by shiwei on 2017/6/16.
 */
class LiveHomeBaseAdapter(var resId: Int, datas: MutableList<Partitions>) : BaseQuickAdapter<Partitions, BaseViewHolder>(resId, datas) {
    var mAdapter: LiveHomeAreaAdapter? = null
    override fun convert(holder: BaseViewHolder, p1: Partitions) {
        val context = holder.itemView.ctx

        var mRecyclerView = holder.getView<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView.layoutManager = GridLayoutManager(holder.itemView.ctx, 2)
        if (holder.layoutPosition == 1) {
            mAdapter = LiveHomeAreaAdapter(R.layout.layout_live_home_area_item, p1.lives,true)
        } else {
            mAdapter = LiveHomeAreaAdapter(R.layout.layout_live_home_area_item, p1.lives.subList(0, 4), false)
        }
        mRecyclerView.adapter = mAdapter

        val headeView: View = LayoutInflater.from(context).inflate(R.layout.layout_live_home_area_item_header, null)
        mAdapter?.addHeaderView(headeView)
        initHeaderView(context, headeView, p1.partition)

        val footerView: View = LayoutInflater.from(context).inflate(R.layout.layout_live_home_area_item_footer, null)
        mAdapter?.addFooterView(footerView)
        initFooterView(context, footerView)
    }

    fun initHeaderView(context: Context, headeView: View, p1: Partition) {
        val areaIcon = headeView.find<ImageView>(R.id.areaIcon)
        val params = areaIcon.layoutParams
        params.height = p1.sub_icon.height.toInt()
        params.width = p1.sub_icon.width.toInt()
        areaIcon.layoutParams = params
        val name = headeView.find<TextView>(R.id.name)
        val online = headeView.find<TextView>(R.id.online)
        Glide.with(context).load(p1.sub_icon.src).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().into(areaIcon)
        name.text = p1.name ?: ""
        online.text = Html.fromHtml("当前<font color='#fb7299'>${p1.count}</font>个直播")
    }

    fun initFooterView(context: Context, headeView: View) {
        val more = headeView.find<Button>(R.id.more)
        val refresh = headeView.find<TextView>(R.id.refresh)
        more.setOnClickListener { context.toast("建设中") }
        refresh.setOnClickListener { context.toast("建设中") }
    }

}