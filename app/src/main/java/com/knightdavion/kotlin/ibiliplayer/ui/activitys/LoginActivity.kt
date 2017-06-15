package com.knightdavion.kotlin.ibiliplayer.ui.activitys

import android.os.Bundle
import android.text.TextUtils
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initToolBar();
        login.setOnClickListener {
            if (!TextUtils.isEmpty(username.text) && !TextUtils.isEmpty(password.text)) {
                isLogin = true
                toast("登录成功")
                startActivity<MainActivity>()
                finish()
            } else {
                toast("用户名和密码不能为空")
            }
        }
    }

    private fun initToolBar() {
        toolbar.setNavigationIcon(R.drawable.ic_action_back)
        toolbar.title = getString(R.string.login)
        toolbar.setNavigationOnClickListener { finish() }
    }
}
