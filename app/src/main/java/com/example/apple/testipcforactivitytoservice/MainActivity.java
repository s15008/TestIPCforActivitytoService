package com.example.apple.testipcforactivitytoservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements ServiceConnection {
    private static final String TAG = "MainActivity";

    private boolean mBind = false;
    private TestService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent service = new Intent(this, TestService.class);
        bindService(service, this, Context.BIND_AUTO_CREATE);

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBind) {
            unbindService(this);
            mBind = false;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mBind = true;
        Toast.makeText(getApplicationContext(), "サービスに接続しました", Toast.LENGTH_SHORT).show();

        mService = ((TestService.MyBinder) service).getService();
        
        mService.test();
        mService.test2();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Toast.makeText(getApplicationContext(), "サービスに切断しました", Toast.LENGTH_SHORT).show();
    }
}
