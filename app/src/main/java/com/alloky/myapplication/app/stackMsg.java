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
    int DATA = 1;
    int STOP =2;
    public  void  run(){
        outputStream = client.getOutputStream();
        Looper.prepare();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
               super.handleMessage(msg);
                if(msg.what == DATA){
                try {
                    out = (float[]) msg.obj;
                    Log.i(null, "InLoop: " + out[0] + " | " + out[1] + " | " + out[2]);
                    outputStream.flush();
                    outputStream.writeObject(out);
                    outputStream.flush();
                } catch (java.lang.Exception e) {
                    Log.i(null, "Плохо");
                    e.printStackTrace();
                    //  textView.setText("BAAAD");
                }}
                if(msg.what==STOP) {
                    
                }
            }
        };
        Looper.loop();
    }
    void addInStack(float[] mas) {
        Log.i(null,"AddIn: "+ mas[0] + " | " + mas[1] + " | " +mas[2] );
        Message m = handler.obtainMessage(1, 1, 1, mas.clone());
        handler.sendMessage(m);
    }
    void stopStack(){
        Log.i(null,"stoping stack" );
        Message m = handler.obtainMessage(2, 1, 1, 666);
        handler.sendMessage(m);
    }

}
