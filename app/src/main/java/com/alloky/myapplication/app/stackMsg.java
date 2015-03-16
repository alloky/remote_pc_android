package com.alloky.myapplication.app;

import android.os.Handler;
import android.os.Message;
import android.os.Looper;
import android.util.Log;

import java.io.ObjectOutputStream;
import java.util.logging.LogRecord;

/**
 * Created by admin on 15.03.2015.
 */
class LooperThread extends Thread {
    client client;
    Activity_Main main;
    private ObjectOutputStream outputStream;
    public Handler handler;
    float[] out;
    public  void  run(){
        outputStream = client.getOutputStream();
        Looper.prepare();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
               super.handleMessage(msg);
                try {
                    out = (float[]) msg.obj;
                    outputStream.flush();
                    outputStream.writeObject(out);
                    outputStream.flush();
                } catch (java.lang.Exception e) {
                    Log.i(null, "Плохо");
                    e.printStackTrace();
                    //  textView.setText("BAAAD");
                };
            }
        };
        Looper.loop();
    }
    void addInStack(float[] mas) {
        Message m = handler.obtainMessage(1, 1, 1, mas);
        handler.sendMessage(m);
    }
    
}
