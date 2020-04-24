package com.example.dsbridgedemo.dsbrige.bean;

/**
* @Author: xuciluan
* @Date : 2020/4/23
* @Desc : 通信协议实体对象
* @Version :
*/
public class JSCall {

    public String method;
    public String data;
    //当H5端处理成功后，客户端显示的数据--》感觉有点鸡肋，其实成功后直接调用原生的方法就可以了
    public String callback;

    public void setMethod(String method) {
        this.method = method;
    }
    public void setData(String datas) {
        this.data = datas;
    }
    public void setCallback(String callback) {
        this.callback = callback;
    }
}
