package com.blanktrack.alipush;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.register.HuaWeiRegister;
import com.alibaba.sdk.android.push.register.MiPushRegister;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Blank on 2017-08-24.
 */

public class PushPlugin extends CordovaPlugin {
    public static final String TAG = "PushPlugin";
    private JSONObject params;

    public static void initCloudChannel(Context applicationContext) {

        PushServiceFactory.init(applicationContext);
        final CloudPushService pushService = PushServiceFactory.getCloudPushService();


        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(TAG, "init cloudchannel success");

            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e(TAG, "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });

        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String xiaomi = applicationInfo.metaData.getString("com.blanktrack.miid");
        String xiaomikey = applicationInfo.metaData.getString("com.blanktrack.mikey");

        MiPushRegister.register(applicationContext, xiaomi, xiaomikey);
        HuaWeiRegister.register(applicationContext);
    }
    @Override
    public boolean execute(final String action,final JSONArray args, final CallbackContext callbackContext) throws JSONException {

        JSONObject arg_object = args.getJSONObject(0);
        if("init".equals(action)){

            String account = "";
            if (arg_object.has("account")) {
                account = arg_object.getString("account");
            }
            Log.i(TAG, "call init bindaccount:"+account);

            final CloudPushService pushService = PushServiceFactory.getCloudPushService();

            final String deviceId = pushService.getDeviceId();
            pushService.bindAccount(account, new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.i(TAG, "bind account success,deviceId:"+deviceId);
                    callbackContext.success(deviceId);
                }

                @Override
                public void onFailed(String s, String s1) {
                    callbackContext.error(s);
                }
            });


        }else if("finish".equals(action)) {
            callbackContext.success();
        }

        return true;
    }

}
