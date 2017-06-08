package com.knightdavion.kotlin.ibiliplayer.ui

import android.os.Bundle
import com.knightdavion.kotlin.ibiliplayer.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initToolBar();
        isLogin = true
    }

    private fun initToolBar() {
        toolbar.setNavigationIcon(R.drawable.ic_action_back)
        toolbar.title = getString(R.string.login)
        toolbar.setNavigationOnClickListener { finish() }
    }
}
