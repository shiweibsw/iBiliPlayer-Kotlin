package com.knightdavion.kotlin.ibiliplayer.adapter

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.model.GameListBean
import org.jetbrains.anko.toast

/**
 * Created by shiwei on 2017/6/16.
 */
class GameCenterAdapter(var resId: Int, datas: List<GameListBean>) : BaseQuickAdapter<GameListBean, BaseViewHolder>(resId, datas) {
    override fun convert(holder: BaseViewHolder, p1: GameListBean) {
        holder.setText(R.id.title, p1.title)
        holder.setText(R.id.subtitle, p1.summary)
        Glide.with(holder.itemView.context).load(p1.icon).into(holder.getView(R.id.img))
        var status = holder.getView<ImageView>(R.id.status)
        status.visibility = View.VISIBLE
        if (p1.hot == 1) {
            holder.setImageResource(R.id.status, R.drawable.ic_game_center_hot)
        } else if (p1.new == 1) {
            holder.setImageResource(R.id.status, R.drawable.ic_game_center_new)
        } else {
            status.visibility = View.GONE
        }
        holder.getView<Button>(R.id.download).setOnClickListener { holder.itemView.context.toast("建设中") }
    }
}