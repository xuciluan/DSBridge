package com.example.dsbridgedemo.dsbrige.webview;

import android.content.Context;

import com.example.dsbridgedemo.dsbrige.interfaces.OnBridgeCallback;

public interface IWebView {

    void loadUrl(String url);

    void addJavascriptInterface(Object obj, String interfaceName);

    void callJs(String url);

    void callJs(String var1, Object object);

    void callJs(String handlerName, Object data, OnBridgeCallback responseCallback);

    Context getContext();
}
