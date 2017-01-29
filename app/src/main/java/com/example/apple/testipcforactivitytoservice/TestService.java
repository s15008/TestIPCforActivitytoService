package com.example.apple.testipcforactivitytoservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by apple on 2017/01/30.
 */

public class TestService extends Service {
    private static final String TAG = "TestService";

    private MyBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {
        TestService getService() {
            return TestService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "TestServiceはバインドされました", Toast.LENGTH_SHORT).show();
        return mBinder;
    }

    public void test() {
        Toast.makeText(getApplicationContext(), "testを実行しました", Toast.LENGTH_SHORT).show();
    }

    public void test2() {
        Toast.makeText(getApplicationContext(), "test2を実行しました", Toast.LENGTH_SHORT).show();
    }
}
