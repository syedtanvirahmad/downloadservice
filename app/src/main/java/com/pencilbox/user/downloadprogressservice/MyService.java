package com.pencilbox.user.downloadprogressservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class MyService extends Service {
    Handler handler = new Handler();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 10; i++){
                    try {
                        Thread.sleep(1000);
                        setCount(i,startId);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf(startId);
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }
    private void setCount(int count, int id) {
        sendBroadcast(new Intent(MainActivity.CustomBroadcastReceiver.CUSTOM_ACTION).putExtra("p",count)
                .putExtra("id",id));

    }
}
