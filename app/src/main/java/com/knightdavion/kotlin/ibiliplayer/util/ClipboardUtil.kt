package com.knightdavion.kotlin.ibiliplayer.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build


/**
 * Created by shiwei on 2017/6/16.
 */
object ClipboardUtil {
    private var mClipboardManager: ClipboardManager? = null
    private var mNewCliboardManager: ClipboardManager? = null
    private fun isNew(): Boolean {

        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
    }

    private fun instance(context: Context) {
        if (isNew()) {
            if (mNewCliboardManager == null) {
                mNewCliboardManager = context.getSystemService(
                        Context.CLIPBOARD_SERVICE) as ClipboardManager
            }
        } else {
            if (mClipboardManager == null) {
                mClipboardManager = context.getSystemService(
                        Context.CLIPBOARD_SERVICE) as ClipboardManager
            }
        }
    }

    fun setText(context: Context, text: CharSequence) {
        if (isNew()) {
            instance(context)
            val clip = ClipData.newPlainText("simple text", text)
            mNewCliboardManager?.primaryClip = clip
        } else {
            instance(context)
            mClipboardManager?.text = text
        }
    }
}