package kr.sdbk.auth.impl.composable

import android.graphics.Bitmap
import android.net.Uri
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
internal fun LoginWebView(
    url: String,
    onLoginComplete: (code: String, state: String?) -> Unit,
    onLoginFailed: () -> Unit,
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true

                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        val pageUrl = url ?: return
                        val uri = Uri.parse(pageUrl)
                        val code = uri.getQueryParameter("code")
                        if (!code.isNullOrBlank()) {
                            onLoginComplete(code, uri.getQueryParameter("state"))
                        }
                    }

                    override fun onReceivedError(
                        view: WebView?,
                        request: WebResourceRequest?,
                        error: WebResourceError?
                    ) {
                        super.onReceivedError(view, request, error)
                        if (request?.isForMainFrame == true) {
                            onLoginFailed()
                        }
                    }
                }

                loadUrl(url)
            }
        },
        update = { webView ->
            if (webView.url.isNullOrBlank()) {
                webView.loadUrl(url)
            }
        }
    )
}