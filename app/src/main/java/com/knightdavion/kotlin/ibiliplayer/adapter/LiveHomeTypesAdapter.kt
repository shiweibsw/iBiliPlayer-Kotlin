package com.knightdavion.kotlin.ibiliplayer.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.extensions.ctx
import com.knightdavion.kotlin.ibiliplayer.model.LiveHomeTypeModel

/**
 * Created by shiwei on 2017/6/16.
 */
class LiveHomeTypesAdapter(var resId: Int, datas: List<LiveHomeTypeModel>) : BaseQuickAdapter<LiveHomeTypeModel, BaseViewHolder>(resId, datas) {
    override fun convert(holder: BaseViewHolder, p1: LiveHomeTypeModel) {
        val icon = holder.getView<ImageView>(R.id.icon)
        val params = icon.layoutParams
        params.width = p1.entrance_icon.width
        params.height = p1.entrance_icon.height
        icon.layoutParams = params
        Glide.with(holder.itemView.ctx).load(p1.entrance_icon.src).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate().into(icon)
        holder.setText(R.id.title, p1.name)
    }
}