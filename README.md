# AnalyticsMnc

### Release Log
[Version 1.1]
1.修复Android11获取网络状态闪退


### 使用方式
1. 直接导入module使用，在主app中依赖：implementation project(path: ':data_sdk')
2. 打包成aar


##### 打包aar方式
选择data_sdk,Build Module"data_sdk",在output中找到生成的aar文件，在主app中进行依赖
```
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    compile(name:'data_sdk-debug', ext:'aar')
```

aar无法依赖远程第三方库和其他aar,需要再次导入依赖库
```
    //okhttp
    implementation 'com.squareup.okhttp3:okhttp:4.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.9.0'
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-scalars:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava3:2.9.0'
    //Kotlin协程核心库
    //implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2"
    //implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2"
    //rxjava
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation "io.reactivex.rxjava3:rxjava:3.0.10"
    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
```
需要用到的权限

```
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
```


### 调用

1.在Application中初始化
```
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化,uuid可不传
        AnyalyticsManger.initSdk(applicationContext,"uuid")
    }

}
```
2.调用：

```
    //打开app
    fun startApp(view: View) {
        AnyalyticsManger.startApp("ciwei","12345")
    }

```


