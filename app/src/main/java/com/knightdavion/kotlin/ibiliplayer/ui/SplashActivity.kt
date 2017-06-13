package com.knightdavion.kotlin.ibiliplayer.ui

import android.Manifest
import android.os.Bundle
import com.jaeger.library.StatusBarUtil
import com.knightdavion.kotlin.ibiliplayer.R
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


/**
 * Created by shiwei on 2017/6/8.
 */
class SplashActivity : BaseActivity(), EasyPermissions.PermissionCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        StatusBarUtil.setTranslucentForImageView(this, 0, null)
        splashCenterIv.animate().alpha(1.0f).setDuration(1000).start()
        splashCenterIv.postDelayed(Runnable {
            if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                goMain();
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.app_name) + "需要使用您的权限", 0, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        }, 2000)
    }

    fun goMain() {
        if (isLogin) {
            startActivity<MainActivity>()
        } else {
            startActivity<LoginActivity>()
        }
        finish()
    }

    override fun onPermissionsDenied(p0: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsGranted(p0: Int, p1: MutableList<String>?) {
        goMain()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        if (grantResults[0] == -1) {
            goMain()
        }
    }
}