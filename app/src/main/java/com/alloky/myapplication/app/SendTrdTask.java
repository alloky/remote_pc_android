package com.alloky.myapplication.app;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by admin on 10.03.2015.
 */
public class SendTrdTask extends AsyncTask<float[],float[],Void> {
    Activity_Main main;
    client client;
    float[] f;
    private static ObjectOutputStream outputStream;
   // TextView textView =(TextView) main.findViewById(R.id.textView);
    @Override
    protected Void doInBackground(float[]... params) {
        try {


            outputStream = client.getOutputStream();
//            System.out.println( client.isRunning);
            outputStream.flush();
            outputStream.writeObject(params[0]);
            outputStream.flush();

        } catch (java.lang.Exception e) {
            Log.i(null, "Плохо");
            e.printStackTrace();
          //  textView.setText("BAAAD");
          }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    //   Toast.makeText(main,"start",Toast.LENGTH_SHORT);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
      //  Toast.makeText(main,"start",Toast.LENGTH_SHORT);
    }
}
