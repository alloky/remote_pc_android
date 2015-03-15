package com.alloky.myapplication.app;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by admin on 10.03.2015.
 */
public class SendTrdTask  {
    client client;
    stackMsg stackMsg;
    float[] f;
    private  ObjectOutputStream outputStream;
   // TextView textView =(TextView) main.findViewById(R.id.textView);
    SendTrdTask(){
        stackMsg =new stackMsg();
        outputStream = client.getOutputStream();

    }
    protected void doInBackground(float[] params) {
        try {
            //TODO addInStack using

            outputStream.flush();
            outputStream.writeObject(params[0]);
            outputStream.flush();
            client.close();
        } catch (java.lang.Exception e) {
            Log.i(null, "Плохо");
            e.printStackTrace();
          //  textView.setText("BAAAD");
          };

    }
}
