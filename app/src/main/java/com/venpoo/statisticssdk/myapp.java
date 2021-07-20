package com.venpoo.statisticssdk;

import android.app.Application;

import com.venpoo.data_sdk.AnyalyticsManger;
import com.venpoo.data_sdk.Procudt;

/**
 * @ClassName myapp
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/20 16:23
 */
public class myapp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AnyalyticsManger.INSTANCE.setParams(
                Procudt.HDQP,
                "xxx",
                "xxx",
                "xxx",
                false
        ).init(this);
    }
}
