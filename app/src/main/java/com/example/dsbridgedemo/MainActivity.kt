package com.example.dsbridgedemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.dsbridgedemo.dsbrige.webview.ZWZTWebView
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.QbSdk.PreInitCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QbSdk.initX5Environment(this, object : PreInitCallback {
            override fun onCoreInitFinished() {
                Log.d("jsbridge", "onCoreInitFinished")
            }

            override fun onViewInitFinished(b: Boolean) {
                Log.d("jsbridge", "onCoreInitFinished")
            }
        })
        setContentView(R.layout.activity_main)
        ZWZTWebView.setWebContentsDebuggingEnabled(true)
        webview.loadUrl("file:///android_asset/js.html")

        calljs.setOnClickListener {
            val url = "jsbridge://alert?{\"data\": \"我是来自原生的数据\",\n" +
                    "\"callback\":\"callSuccess\"}"
            webview.callJs(url)
        }
    }
}
