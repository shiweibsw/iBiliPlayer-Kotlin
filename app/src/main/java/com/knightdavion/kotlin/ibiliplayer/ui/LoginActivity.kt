package com.knightdavion.kotlin.ibiliplayer.ui

import android.os.Bundle
import com.knightdavion.kotlin.ibiliplayer.R

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        isLogin = true
    }
}
