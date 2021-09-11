package dev.turnr.trainerdex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val webViewNoticeToast: Toast =
            Toast.makeText(this.context, R.string.webview_wip_notice, Toast.LENGTH_LONG)
        webViewNoticeToast.show()

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val webView: WebView = view.findViewById(R.id.webView)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val splashScreen: ImageView = view.findViewById(R.id.splashScreen)
        splashScreen.bringToFront()

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
        CookieManager.getInstance().setAcceptCookie(true)

        webView.loadUrl("https://trainerdex.app/profile/")

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                progressBar.progress = newProgress
                if (newProgress > 80) {
                    splashScreen.visibility = View.GONE
                    webView.visibility = View.VISIBLE
                }
            }
        }

        return view
    }


    override fun onResume() {
        super.onResume()
        val webViewNoticeToast: Toast =
            Toast.makeText(this.context, R.string.webview_wip_notice, Toast.LENGTH_LONG)
        webViewNoticeToast.show()
    }
}