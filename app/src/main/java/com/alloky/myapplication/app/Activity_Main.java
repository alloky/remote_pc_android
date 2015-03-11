package com.alloky.myapplication.app;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.*;
import android.view.View.OnClickListener;

public class Activity_Main extends Activity implements OnTouchListener
{
    client client;

    TextView textView;

    String textOfView;

   float  xPos,yPos;

    OnClickListener clickListener;

    OnTouchListener touchListener;

    Space space ;

    Button buttonLeftCl;

    Button buttonRightCl;

    Button startButton;

    EditText textField;

    float[] outMas = {0,0,0};

    Button stopButton;

    Thread thread;

   RelativeLayout area;

    SurfaceView surfaceView;

    final static String TAG = "MyLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
       textView =(TextView)findViewById(R.id.area);
        buttonLeftCl=(Button)findViewById(R.id.buttonLeftCl);
        buttonRightCl =(Button)findViewById(R.id.buttonRightCl);
        startButton = (Button)findViewById(R.id.start_button);
        stopButton = (Button)findViewById(R.id.Stop_button);
         area = (RelativeLayout)findViewById(R.id.rel);
        textField = (EditText)findViewById(R.id.port);
        clickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.start_button:
                        textView.setText("started") ;
                        if(textField.getText().toString()!="port")
                        {
                            client.port = textField.getText().toString();
                        }
                        thread.start();
                        break;
                    case R.id.Stop_button:
                        client.close();
                        textView.setText("stoped");
                        break;
                    case R.id.buttonLeftCl:
                        outMas[0]= 10;
                        outMas[1] = xPos;
                        outMas[2] = yPos;
                       // client.sendData(outMas);
                        new SendTrdTask().execute(outMas);
                        break;
                    case R.id.buttonRightCl:
                        outMas[0]= 20;
                        outMas[1] = xPos;
                        outMas[2] = yPos;
                       // client.sendData(outMas);
                        new SendTrdTask().execute(outMas);
                        break;
                }
            }
        };
       textView.setOnTouchListener(this);
        client = new client();
        thread = new Thread(client);
        buttonRightCl.setOnClickListener(clickListener);
        buttonLeftCl.setOnClickListener(clickListener);
        stopButton.setOnClickListener(clickListener);
        startButton.setOnClickListener(clickListener);

    }
    public boolean onTouch(View v, MotionEvent event) {

        xPos = event.getX();
        yPos =event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                textOfView = "Down: X - " + xPos + "Y: -"+ yPos;
                outMas[0] = 1;
                outMas[1] = xPos;
                outMas[2] = yPos;
               // client.sendData(outMas);
                new SendTrdTask().execute(outMas);
                break;
            case MotionEvent.ACTION_MOVE:
                textOfView = "Move: X - " + xPos + "Y: -"+ yPos;
                outMas[0] = 2;
                outMas[1] = xPos;
                outMas[2] = yPos;
                //client.sendData(outMas);
                new SendTrdTask().execute(outMas);
                break;
            case MotionEvent.ACTION_UP:
                textOfView = "Up: X - " + xPos + "Y: -"+ yPos;
                outMas[0] = 3;
                outMas[1] = xPos;
                outMas[2] = yPos;
                //client.sendData(outMas);
                new SendTrdTask().execute(outMas);
                break;
            case MotionEvent.ACTION_CANCEL:
                textOfView = "Up: X - " + xPos + "Y: -"+ yPos;
                outMas[0] = 3;
                outMas[1] = xPos;
                outMas[2] = yPos;
                //client.sendData(outMas);
                new SendTrdTask().execute(outMas);
                break;
        }

      //  textView.setText(textOfView);
       // client.close();
       // client = new client();
        return true;
    }



}
