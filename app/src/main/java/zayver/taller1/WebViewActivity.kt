package zayver.taller1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewActivity : AppCompatActivity() {
    private lateinit var web: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        inflate()
        initialize()
    }
    private fun inflate() {
        web = findViewById(R.id.web_view)
    }
    private fun initialize() {
        val thisIntent = intent
        web.webViewClient = WebViewClient()
        web.loadUrl(thisIntent.getStringExtra("url_web").toString())
    }
}