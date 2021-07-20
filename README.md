# AnalyticsMnc

### Release Log
> [Version 1.4]
>
> 1.更新接口
>


> [Version 1.1]
>
> 1.修复Android11获取网络状态闪退


### 快速接入

**1.复制aar文件到libs目录下**

```
    //导入全部aar，jar文件
    implementation fileTree(dir: 'libs', include: ['*.aar', '*.jar'], exclude: [])
```
或者
```
    implementation (name:'mnc_data_sdk', ext:'aar')
```

**2.使用远程依赖：**


```
    //project build.gradle
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	//app build.gradle
	dependencies {
	        implementation 'com.github.MncShangHai:AnalyticsMnc:1.4'
	}

```

---

**需要依赖库**
```
    //okhttp
    implementation 'com.squareup.okhttp3:okhttp:4.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.9.0'
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-scalars:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava3:2.9.0'
    //rxjava
    implementation "io.reactivex.rxjava3:rxjava:3.0.10"
    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
    implementation 'com.google.code.gson:gson:2.8.6'
```
**需要用到的权限**

```
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
```


### 调用

1.在Application中初始化

kotlin
```
        AnyalyticsManger.setParams(
            product = Procudt.HDQP,
            uuid = "xxx",
            uid = "xxx",
            channel = "xxx"
        ).init(this)
```

java
```
        AnyalyticsManger.INSTANCE.setParams(
                Procudt.HDQP,
                "xxx",
                "xxx",
                "xxx",
                false
        ).init(this);
```

2.调用：

```
    //打开app
    fun startApp(view: View) {
        AnyalyticsManger.startApp()
    }

```

### 参数

     * @param product 产品名称，通过Product枚举类设置
     * @param uuid
     * @param uid
     * @param channel 打包渠道名称



