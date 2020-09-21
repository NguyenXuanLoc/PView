package com.example.pview.common.ext

import android.webkit.WebView
import android.webkit.WebViewClient

fun WebView.settings() {
    this.settings.domStorageEnabled = true
    this.settings.javaScriptEnabled = true
    this.settings.loadWithOverviewMode = true
    this.settings.useWideViewPort = true
    this.settings.builtInZoomControls = true
    this.settings.mediaPlaybackRequiresUserGesture = false
}

fun WebView.openWebView(url: String) {
    this.loadUrl(url)
    this.webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            //wvContent.loadUrl("javascript:(function() { document.getElementsByTagName('video')[0].play(); })()");
            //save acc to webview
            view?.loadUrl(
                "javascript:(function() { " +
                        "var element = document.getElementById('hplogo');"
                        + "element.parentNode.removeChild(element);" +
                        "})()"
            )
            super.onPageFinished(view, url)
        }
    }
}

fun WebView.loadUrlAutoPlay(url: String, v: WebViewInterface) {
    this.loadUrl(url)
    this.webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            v.readyPlayVideo()
            //Auto play when loadVideo success
            view?.loadUrl("javascript:(function() { document.getElementsByTagName('video')[0].play(); })()");
            super.onPageFinished(view, url)
        }
    }
}

interface WebViewInterface {
    fun readyPlayVideo()
}