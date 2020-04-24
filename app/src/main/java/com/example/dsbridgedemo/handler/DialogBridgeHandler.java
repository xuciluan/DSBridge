package com.example.dsbridgedemo.handler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.dsbridgedemo.dsbrige.BridgeHandler;
import com.example.dsbridgedemo.dsbrige.interfaces.CallBackFunction;


public class DialogBridgeHandler extends BridgeHandler {

    @Override
    public void handleCallFromJs(Context context, String data, CallBackFunction function) {
       new AlertDialog.Builder(context)
               .setTitle("弹窗")
               .setMessage(data)
               .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                   }
               })
               .show();
    }
}
