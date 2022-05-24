package com.example.greedwebview.utils

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView

/**
 *
 * create time 2022/5/17 5:43 下午
 * create by 胡汉君
 * 滑动冲突处理webview
 */
class HWebview @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : WebView(context, attrs) {
    override fun onOverScrolled(scrollX: Int, scrollY: Int, clampedX: Boolean, clampedY: Boolean) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY)
        if (clampedY) {
            requestDisallowInterceptTouchEvent(false)
        } else {
            requestDisallowInterceptTouchEvent(true)
        }
    }
}