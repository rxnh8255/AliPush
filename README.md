#阿里云推送服务

集成的阿里云的push服务.

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

1.使用init绑定帐号,根据该帐号单独推送消息给用户
也可以用返回的设备ID绑定以后进行其他操作
```javascript
window.pushPlugin.init({account:'test'},function(deviceId){})
```
2.使用initstate查看SDK注册情况
```javascript
window.pushPlugin.initstate(function (res) {
    console.log("ok");
},function (err){
    console.log(err);
});
```

3.使用registerNotify接收来通知的回调函数
```
//type说明
* notify:       通知接收回调,title 标题,summary 内容,extraMap 通知额外参数
* notifyopen:   通知打开回调,title 标题,summary 内容,extraMap 通知额外参数
* notifyremove: 通知删除回调,messageid 消息ID
* message:      消息接收回调,messageid 消息ID,title 消息标题,content 消息内容
//未完待续...
```

```javascript
window.pushPlugin.registerNotify(function (res) {
    //res参数都带有一个type
    console.log(res);
},function(err){
    console.log(err);
});
```

4.使用unbind解绑推送的帐号
```javascript
window.pushPlugin.unbind(function () {
    console.log("ok");
},function (err){
    console.log(err);
});
```