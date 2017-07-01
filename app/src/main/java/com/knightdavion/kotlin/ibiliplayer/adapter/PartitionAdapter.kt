package com.knightdavion.kotlin.ibiliplayer.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.knightdavion.kotlin.ibiliplayer.model.PartitionItem

/**
 * Created by shiwei on 2017/7/01.
 */
class PartitionAdapter(var resId: Int, datas: MutableList<PartitionItem>?) : BaseQuickAdapter<PartitionItem, BaseViewHolder>(resId, datas) {
    override fun convert(holder: BaseViewHolder, p1: PartitionItem) {
    }
}