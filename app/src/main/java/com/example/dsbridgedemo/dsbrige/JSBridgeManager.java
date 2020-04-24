package com.example.dsbridgedemo.dsbrige;

import java.util.HashMap;
import java.util.Map;
/**
* @Author: xuciluan
* @Date : 2020/4/24
* @Desc : 暴露给外部使用的API
* @Version : 1.0
*/
public class JSBridgeManager {

    private JSBridgeManager(){}
    private static class JSBridgeManagerHolder{
        private static JSBridgeManager HOLDER = new JSBridgeManager();
    }
    public static JSBridgeManager getInstance(){
        return JSBridgeManagerHolder.HOLDER;
    }

    private Map<String, BridgeHandler> mMessageHandlers = new HashMap<>();
    public Map<String, BridgeHandler> getMessageHandlers(){
        return mMessageHandlers;
    }

    public void registerHandler(String handlerName, BridgeHandler handler) {
        if (handler != null) {
            mMessageHandlers.put(handlerName, handler);
        }
    }

    public void unregisterHandler(String handlerName) {
        if (handlerName != null) {
            mMessageHandlers.remove(handlerName);
        }
    }


}
