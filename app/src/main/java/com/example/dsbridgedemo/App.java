package com.example.dsbridgedemo;

import android.app.Application;

import com.example.dsbridgedemo.dsbrige.JSBridgeManager;
import com.example.dsbridgedemo.handler.DialogBridgeHandler;
import com.example.dsbridgedemo.handler.ToastBridgeHandler;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JSBridgeManager.getInstance().registerHandler("toast", new ToastBridgeHandler());
        JSBridgeManager.getInstance().registerHandler("dialog", new DialogBridgeHandler());
    }
}
