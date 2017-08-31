package com.blanktrack.alipush;

import android.app.Application;

import static com.blanktrack.alipush.PushPlugin.initCloudChannel;

/**
 * Created by Blank on 2017-08-28.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initCloudChannel(this);
    }

}
