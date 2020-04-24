package com.example.dsbridgedemo.handler;

import android.content.Context;
import android.widget.Toast;

import com.example.dsbridgedemo.dsbrige.BridgeHandler;
import com.example.dsbridgedemo.dsbrige.interfaces.CallBackFunction;


public class ToastBridgeHandler extends BridgeHandler {

    @Override
    public void handleCallFromJs(Context context, String data, CallBackFunction function) {
        Toast.makeText(context,data, Toast.LENGTH_LONG).show();
    }
}
