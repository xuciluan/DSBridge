package com.example.dsbridgedemo.dsbrige;

import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;

import com.example.dsbridgedemo.dsbrige.bean.JSCall;
import com.example.dsbridgedemo.dsbrige.interfaces.CallBackFunction;
import com.example.dsbridgedemo.dsbrige.interfaces.CompletionHandler;
import com.example.dsbridgedemo.dsbrige.webview.IWebView;
import com.google.gson.Gson;
/**
* @Author: xuciluan
* @Date : 2020/4/24
* @Desc : 唯一注册给H5进行使用的对象，作用是：进行Native方法分发
* @Version : 1.0
*/
public class ZWZTJavaScriptInterface {

    Handler mainHandler = new Handler(Looper.getMainLooper());
    private JSBridge mJsBridge;
    private IWebView mWebView;

    public ZWZTJavaScriptInterface(JSBridge bridge, IWebView webView) {
        this.mJsBridge = bridge;
        this.mWebView = webView;
    }

    @JavascriptInterface
    public void callNativeMethod(Object data , CompletionHandler completionHandler) {
        final JSCall jsCall = parse(data);
        if (jsCall == null){
            return;
        }
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mJsBridge.getMessageHandlers().containsKey(jsCall.method)) {
                    BridgeHandler bridgeHandler = mJsBridge.getMessageHandlers().get(jsCall.method);
                    if (bridgeHandler != null) {
                        bridgeHandler.handleCallFromJs(mWebView.getContext(), jsCall.data, new CallBack(jsCall.callback));
                    }
                }
            }
        });
    }

    private JSCall parse(Object obj){
        return new Gson().fromJson(obj.toString(),JSCall.class);
    }

    public class CallBack implements CallBackFunction {

        private String callbackMethod;

        public CallBack(String callbackId) {
            this.callbackMethod = callbackId;
        }

        @Override
        public void onCallBack(String data) {
            mJsBridge.sendResponse(data, callbackMethod);
        }
    }

}
