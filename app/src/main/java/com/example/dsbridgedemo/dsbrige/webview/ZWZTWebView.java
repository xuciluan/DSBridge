package com.example.dsbridgedemo.dsbrige.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Toast;

import com.example.dsbridgedemo.dsbrige.JSBridge;
import com.example.dsbridgedemo.dsbrige.bean.JSCall;
import com.example.dsbridgedemo.dsbrige.interfaces.OnBridgeCallback;
import com.example.dsbridgedemo.dsbrige.interfaces.OnReturnValue;
import com.example.dsbridgedemo.dsbrige.ZWZTJavaScriptInterface;

public class ZWZTWebView extends DWebView implements IWebView {

    public ZWZTWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addJavascriptObject(new ZWZTJavaScriptInterface(new JSBridge(this),this),null);
    }

    public ZWZTWebView(Context context) {
        this(context,null);
    }

    @Override
    public void callJs(String url) {
        //解析协议
        JSCall jsCall = JSBridge.parse(url);
        if (jsCall == null) {
            showToast("当前版本不支持该协议，请升级到最新版本");
            return;
        }
        if (jsCall.callback != null) {
            callHandler(jsCall.method, new Object[]{jsCall.data});
        } else {
            callHandler(jsCall.method, new Object[]{jsCall.data}, new OnReturnValue<String>() {
                @Override
                public void onValue(String retValue) {
                    showToast(retValue);
                }
            });
        }
    }

    void showToast(Object o) {
        Toast.makeText(getContext(), o.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void callJs(String methodName, Object data) {
        callHandler(methodName,new Object[]{data});
    }

    @Override
    public void callJs(String methodName, Object data, final OnBridgeCallback responseCallback) {
        callHandler(methodName, new Object[]{data}, new OnReturnValue<String>() {
            @Override
            public void onValue(String retValue) {
                responseCallback.onCallBack(retValue);
            }
        });
    }

    public static void setWebContentsDebuggingEnabled(boolean enabled){
        DWebView.setWebContentsDebuggingEnabled(enabled);
    }
}
