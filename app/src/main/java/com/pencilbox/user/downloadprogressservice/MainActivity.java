package com.pencilbox.user.downloadprogressservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ProgressBar mProgressBar;
    ProgressBar mProgressBar2;
    CustomBroadcastReceiver receiver;
    int count1 = 0;
    int count2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progressbar);
        mProgressBar2 = findViewById(R.id.progressbar2);
        receiver = new CustomBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(CustomBroadcastReceiver.CUSTOM_ACTION));
    }

    public void startProgressOne(View view) {
        startService(new Intent(MainActivity.this,MyService.class));
    }

    public void startProgressTwo(View view) {
        startService(new Intent(MainActivity.this,MyService.class));
    }

    public class CustomBroadcastReceiver extends BroadcastReceiver{

        public static final String CUSTOM_ACTION = "com.pencilbox.user.downloadprogressservice.ACTION_CUSTOM";

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals(CustomBroadcastReceiver.CUSTOM_ACTION)){
                int count = intent.getIntExtra("p",0);
                int id = intent.getIntExtra("id",0);
                //Log.e("custom", "id: "+id);
                switch (id){
                    case 1:
                        count1 = count;
                        mProgressBar.setProgress(count1);
                        break;
                    case 2:
                        count2 = count;
                        mProgressBar2.setProgress(count2);
                        break;
                }


                //Log.e("custom", "onReceive: "+count);
            }
        }
    }
}
