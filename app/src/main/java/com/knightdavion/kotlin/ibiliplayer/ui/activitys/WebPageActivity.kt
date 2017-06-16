package com.knightdavion.kotlin.ibiliplayer.ui.activitys

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.webkit.*
import com.github.ybq.android.spinkit.SpinKitView
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.ui.BaseActivity
import com.knightdavion.kotlin.ibiliplayer.ui.ProgressBarManager
import com.knightdavion.kotlin.ibiliplayer.ui.ToolBarManager
import com.knightdavion.kotlin.ibiliplayer.util.ClipboardUtil
import kotlinx.android.synthetic.main.activity_webpage.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast


/**
 * Created by shiwei on 2017/6/14.
 */
class WebPageActivity : BaseActivity(), ToolBarManager, ProgressBarManager {
    companion object {
        val URL = "WebPageActivity:url"
        val TITLE = "WebPageActivity:title"
    }

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override val loadingBar by lazy { find<SpinKitView>(R.id.psBar) }
    var url: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webpage)
        initToolBar()
        initView();
    }

    fun initToolBar() {
        url = intent.getStringExtra(URL)
        toolbarTitle = intent.getStringExtra(TITLE)
        enableHomeAsUp { onBackPressed() }
        toolbar.inflateMenu(R.menu.menu_webpage)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_share -> {
                    var intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain";
                    intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                    intent.putExtra(Intent.EXTRA_TEXT, "来自「哔哩哔哩」的分享:" + url);
                    startActivity(Intent.createChooser(intent, toolbarTitle));
                }
                R.id.action_browser -> {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                }
                R.id.action_copy -> {
                    ClipboardUtil.setText(this, url!!)
                    toast("已复制")
                }
            }
            true
        }
    }

    fun initView() {
        val webSettings: WebSettings = mWebView.settings;
        webSettings.javaScriptEnabled = true;
        webSettings.javaScriptCanOpenWindowsAutomatically = true;
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE;
        webSettings.domStorageEnabled = true;
        webSettings.setGeolocationEnabled(true);
        webSettings.useWideViewPort = true;
        webSettings.loadWithOverviewMode = true;
        webSettings.blockNetworkImage = false;
        mWebView.requestFocus(View.FOCUS_DOWN);
        webSettings.defaultTextEncodingName = "UTF-8";
        mWebView.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoadingBar()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                hideLoadingBar()
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                super.onReceivedError(view, request, error)
                val errorHtml = "<html><body><h2>找不到网页</h2></body></html>"
                view.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null)
            }
        })
        mWebView.loadUrl(url)
    }


    override fun onBackPressedSupport() {
        super.onBackPressedSupport()
        if (mWebView.canGoBack() && mWebView.copyBackForwardList().size > 0
                && !mWebView.url.equals(mWebView.copyBackForwardList()
                .getItemAtIndex(0).originalUrl)) {
            mWebView.goBack()
        } else {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mWebView.destroy();
    }
}