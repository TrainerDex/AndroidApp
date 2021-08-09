package dev.turnr.trainerdex

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val splashScreen: ImageView = findViewById(R.id.splashScreen)
        splashScreen.bringToFront()

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
        CookieManager.getInstance().setAcceptCookie(true)

        webView.loadUrl("https://trainerdex.app/")

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                progressBar.progress = newProgress
                if (newProgress > 80) {
                    splashScreen.visibility = View.GONE
                    webView.visibility = View.VISIBLE
                }
                Log.d("Progress", newProgress.toString())
            }
        }

    }

    override fun onBackPressed() {
        val webView: WebView = findViewById(R.id.webView)
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}