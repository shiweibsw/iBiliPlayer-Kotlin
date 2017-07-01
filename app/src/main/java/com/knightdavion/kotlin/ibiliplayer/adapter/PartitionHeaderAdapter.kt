package com.knightdavion.kotlin.ibiliplayer.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.model.PartitionItem

/**
 * Created by shiwei on 2017/7/01.
 */
class PartitionHeaderAdapter(var resId: Int, datas: MutableList<PartitionItem>) : BaseQuickAdapter<PartitionItem, BaseViewHolder>(resId, datas) {
    override fun convert(holder: BaseViewHolder, p1: PartitionItem) {
        holder.setImageResource(R.id.icon,p1.icon)
        holder.setText(R.id.title,p1.name)
    }
}