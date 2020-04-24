package com.example.dsbridgedemo.dsbrige;

import android.net.Uri;

import com.example.dsbridgedemo.dsbrige.bean.JSCall;
import com.example.dsbridgedemo.dsbrige.webview.IWebView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xuciluan
 * @Date : 2020/4/23
 * @Desc : 用于H5和Native进行通信的类
 * @Version : 1.0
 */
public class JSBridge {

    private IWebView mWebView;
    private Map<String, BridgeHandler> mMessageHandlers = new HashMap<>();

    public Map<String, BridgeHandler> getMessageHandlers() {
        return mMessageHandlers;
    }

    public JSBridge(IWebView webView) {
        this.mWebView = webView;
        mMessageHandlers.putAll(JSBridgeManager.getInstance().getMessageHandlers());
    }

    private static String PROTOCOL_NAME = "jsbridge";
    private static String DATA = "data";
    private static String CALLBACK = "callback";

    public static JSCall parse(String urlString) {
        if (!urlString.equals("") && urlString.startsWith(PROTOCOL_NAME)) {
            Uri uri = Uri.parse(urlString);
            String methodName = uri.getHost();
            try {
                JSONObject args = new JSONObject(uri.getQuery());
                String cbName = args.getString(CALLBACK);
                JSCall jsCall = new JSCall();
                jsCall.method = methodName;
                jsCall.data = args.getString(DATA);
                jsCall.callback = cbName;
                return jsCall;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将结果回调给H5
     *
     * @param data
     * @param callbackName
     */
    public void sendResponse(Object data, String callbackName) {
        if (data == null || callbackName == null) {
            return;
        }
        mWebView.callJs(callbackName, data);
    }

}
