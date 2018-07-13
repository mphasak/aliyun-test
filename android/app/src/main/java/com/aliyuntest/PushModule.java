package com.aliyuntest;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class PushModule extends ReactContextBaseJavaModule {
    private static ReactContext context;

    public PushModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
    }

    public static ReactContext getContext() {
        return context;
    }

    //模块名，在JavaScript中调用相关方法时需要首先引入MPush模块
    @Override
    public String getName() {
        return "MPush";
    }

    @ReactMethod
    public void getDeviceId(Callback callback) {
        callback.invoke(PushServiceFactory.getCloudPushService().getDeviceId());
    }

    @ReactMethod
    public void bindAccount(String account, final Callback callback) {
        PushServiceFactory.getCloudPushService().bindAccount(account, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                callback.invoke("bind account success");
            }
            @Override
            public void onFailed(String s, String s1) {
                callback.invoke("bind account failed. errorCode:" + s + ", errorMsg:" + s1);
            }
        });
    }
}
