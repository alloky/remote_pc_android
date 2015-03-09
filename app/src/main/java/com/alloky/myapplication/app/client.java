package com.alloky.myapplication.app;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.*;import java.lang.ClassNotFoundException;import java.lang.Exception;import java.lang.Runnable;import java.lang.String;import java.lang.System;import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by admin on 17.02.2015.
 */
public class client implements Runnable {
    private static Socket connection;
    private  static ObjectInputStream inputStream;
    private static ObjectOutputStream outputStream;
    public String port = "192.168.1.2";

    String  x_str;
    int x;
    boolean isRunning = false;
    public  client(){
        this.isRunning = true;
    }
    public void  run(){
        try {
            System.out.println("Runned1");
            while (isRunning)
            {
                System.out.println("Runned");
                connection = new Socket(InetAddress.getByName(port),1234);
                connection.setTcpNoDelay(true);
                inputStream = new ObjectInputStream(connection.getInputStream());
                outputStream = new ObjectOutputStream(connection.getOutputStream());
                /*x_str =(String) inputStream.readObject();
                x = Integer.parseInt(x_str);
                robot.mouseMove(x,100);*/
            }
        } catch (IOException e) {
            Log.i(null,"bad");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            // isRunning = false;
        }
    }


    public void sendData(float[] s){
        try {
            System.out.println( this.isRunning);
          /*  if(s[0]==(float)1.0 || s[0] == (float)2.0){
                connection.setTcpNoDelay(true);
            }                                           Bad idea! :(
            if(s[0]==(float)3.0){
                connection.setTcpNoDelay(false);
            } */
            outputStream.flush();
            outputStream.writeObject(s);
            outputStream.flush();
        } catch (java.lang.Exception e) {Log.i(null,"Плохо");
            try {
            Thread.sleep(100);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }}
    }

    public void close() {
        try {
            outputStream.close();
            inputStream.close();
            connection.close();
        } catch (Exception e) {e.printStackTrace();}
    }
}
