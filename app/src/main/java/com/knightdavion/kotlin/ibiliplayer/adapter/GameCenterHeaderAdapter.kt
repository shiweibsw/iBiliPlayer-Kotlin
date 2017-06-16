package com.knightdavion.kotlin.ibiliplayer.adapter

import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.extensions.ctx
import com.knightdavion.kotlin.ibiliplayer.model.BookGiftBean
import com.knightdavion.kotlin.ibiliplayer.ui.activitys.WebPageActivity
import org.jetbrains.anko.startActivity

/**
 * Created by shiwei on 2017/6/16.
 */
class GameCenterHeaderAdapter(var resId: Int, datas: MutableList<BookGiftBean>?) : BaseQuickAdapter<BookGiftBean, BaseViewHolder>(resId, datas) {
    override fun convert(holder: BaseViewHolder, p1: BookGiftBean) {
        holder.setText(R.id.title, p1.name)
        Glide.with(holder.itemView.context).load(p1.image).into(holder.getView(R.id.img))
        holder.itemView.setOnClickListener {
            holder.itemView.ctx.startActivity<WebPageActivity>(
                    WebPageActivity.TITLE to p1.name, WebPageActivity.URL to p1.link
            )
        }
        holder.getView<TextView>(R.id.button).setOnClickListener {
            holder.itemView.ctx.startActivity<WebPageActivity>(
                    WebPageActivity.TITLE to p1.name, WebPageActivity.URL to p1.link
            )
        }
    }
}