package com.pencilbox.user.downloadprogressservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int count = 0;
        int id = intent.getIntExtra("id",0);
        for(int i = 1; i <= 10; i++){
            try {
                Thread.sleep(1000);
                count++;
                setCount(i,id);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setCount(int count, int id) {
        sendBroadcast(new Intent(MainActivity.CustomBroadcastReceiver.CUSTOM_ACTION).putExtra("p",count)
        .putExtra("id",id));
    }
}
