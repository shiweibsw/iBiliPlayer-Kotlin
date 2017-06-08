package com.knightdavion.kotlin.ibiliplayer.ui

import android.os.Bundle
import com.jaeger.library.StatusBarUtil
import com.knightdavion.kotlin.ibiliplayer.R
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

/**
 * Created by shiwei on 2017/6/8.
 */
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        StatusBarUtil.setTranslucentForImageView(this, 0, null)
        splashCenterIv.animate().alpha(1.0f).setDuration(1000).start()
        splashCenterIv.postDelayed(Runnable {
            if (isLogin) {
                startActivity<MainActivity>()
            } else {
                startActivity<LoginActivity>()
            }
            finish()
        }, 2000)
    }
}