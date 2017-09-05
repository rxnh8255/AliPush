package com.blanktrack.alipush;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.blanktrack.alipush.PushPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Blank on 2017-08-29.
 */

public class MyMessageReceiver extends MessageReceiver {

    // 消息接收部分的LOG_TAG
    public static final String REC_TAG = "receiver";
    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        JSONObject response = new JSONObject();
        try {
            response.put("type","notify");
            response.put("title",title);
            response.put("summary",summary);
            response.put("extraMap",extraMap);
            sendEvent(response);
        } catch (JSONException e) {
            sendError(e.getMessage());
        }
    }
    @Override
    public void onMessage(Context context, CPushMessage cPushMessage) {
        JSONObject response = new JSONObject();
        try {
            response.put("type","message");
            response.put("messageid",cPushMessage.getMessageId());
            response.put("title",cPushMessage.getTitle());
            response.put("content",cPushMessage.getContent());
            sendEvent(response);
        } catch (JSONException e) {
            sendError(e.getMessage());
        }

    }
    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        JSONObject response = new JSONObject();
        try {
            response.put("type","notifyopen");
            response.put("title",title);
            response.put("summary",summary);
            response.put("extraMap",extraMap);
            sendEvent(response);
        } catch (JSONException e) {
            sendError(e.getMessage());
        }
    }
    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        Log.e("MyMessageReceiver", "onNotificationClickedWithNoAction, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
    }
    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        Log.e("MyMessageReceiver", "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);
    }
    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        JSONObject response = new JSONObject();
        try {
            response.put("type","notifyremove");
            response.put("messageId",messageId);
            sendEvent(response);
        } catch (JSONException e) {
            sendError(e.getMessage());
        }
    }

    private void sendEvent(JSONObject _json) {
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, _json);
        pluginResult.setKeepCallback(true);

        CallbackContext pushCallback = PushPlugin.getCurrentCallbackContext();
        if (pushCallback != null) {
            pushCallback.sendPluginResult(pluginResult);
        }
    }

    public void sendError(String message) {
        PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, message);
        pluginResult.setKeepCallback(true);
        CallbackContext pushCallback = PushPlugin.getCurrentCallbackContext();
        if (pushCallback != null) {
            pushCallback.sendPluginResult(pluginResult);
        }
    }
}
