package com.knightdavion.kotlin.ibiliplayer.adapter

import android.text.Html
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.extensions.ctx
import com.knightdavion.kotlin.ibiliplayer.model.Lives

/**
 * Created by shiwei on 2017/6/16.
 */
class LiveHomeAreaAdapter(var resId: Int, datas: MutableList<Lives>, val isHot: Boolean) : BaseQuickAdapter<Lives, BaseViewHolder>(resId, datas) {
    override fun convert(holder: BaseViewHolder, p1: Lives) {
        val context = holder.itemView.ctx
        Glide.with(context).load(p1.cover.src).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate().into(holder.getView(R.id.img))
        if (isHot) {
            holder.setText(R.id.title, Html.fromHtml("<font color='#fb7299'>#${p1.area}#</font>${p1.title}"))
        } else {
            holder.setText(R.id.title, p1.title)
        }
        holder.setText(R.id.name, p1.owner.name)
        holder.setText(R.id.countTx, p1.online.toString())
    }
}