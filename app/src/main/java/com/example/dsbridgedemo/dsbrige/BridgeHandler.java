package com.example.dsbridgedemo.dsbrige;

import android.content.Context;

import com.example.dsbridgedemo.dsbrige.interfaces.CallBackFunction;

public abstract class BridgeHandler {

    public abstract void handleCallFromJs(Context context, String data, CallBackFunction function);

}