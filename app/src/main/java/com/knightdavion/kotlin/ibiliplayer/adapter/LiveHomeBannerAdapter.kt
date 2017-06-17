package com.hengda.smart.kotlin.jinsha.main

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.extensions.ctx
import com.knightdavion.kotlin.ibiliplayer.model.Banner
import com.knightdavion.kotlin.ibiliplayer.ui.activitys.WebPageActivity
import org.jetbrains.anko.startActivity

/**
 * Created by shiwei on 2017/6/15.
 */
class LiveHomeBannerAdapter(val datas: List<Banner>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var img: ImageView = ImageView(container.context)
        Glide.with(container.ctx).load(datas[position].img).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate().into(img)
        img.scaleType = ImageView.ScaleType.FIT_XY
        container.addView(img)
        img.setOnClickListener {
            container.ctx.startActivity<WebPageActivity>(
                    WebPageActivity.TITLE to datas[position].title, WebPageActivity.URL to datas[position].link
            )
        }
        return img
    }

    override fun destroyItem(container: ViewGroup, p: Int, v: Any) {
        if (v is View)
            container.removeView(v)
    }

    override fun isViewFromObject(p0: View?, p1: Any?): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int = datas.size

}
