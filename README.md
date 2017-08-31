#阿里云推送服务

集成的阿里云的push服务.还没完善.有问题

## Installing the plugin

1.如果小米id和小米key是空的.可以填null
```
cordova plugin add cordova-plugin-alipush --variable MIID=your miid --variable MIKEY=your mikey --variable APPKEY=yourappkey  --variable APPSECRET=yourappsecret
```

2.在阿里控制台的移动推送中按照官方文档配置appkey和appsecret,服务端请参考[阿里移动推送文档](https://help.aliyun.com/document_detail/48038.html?spm=5176.doc30066.6.591.hYl0WZ "移动推送文档")

3.application 查看一下属性 android:name="com.blanktrack.alipush.MyApplication",
    如果是其他的值,请在class里面添加
```java
import static com.blanktrack.alipush.PushPlugin.initCloudChannel;
@Override
public void onCreate() {
    super.onCreate();
    initCloudChannel(this);
}
```

## Using the plugin

使用init绑定帐号,根据该帐号单独推送消息给用户
```javascript
window.PushPlugin.init({account:'test'})
```

