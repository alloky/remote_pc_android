package com.alloky.myapplication.app;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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

    public static ObjectInputStream getInputStream() {
        return inputStream;
    }

    public static ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public String port = "192.168.1.2";
    private boolean isListening,isConnecting;
    boolean onUpdate;

    String  x_str;
    int x;
    boolean isRunning = false;
    public  client(){
        this.isRunning = true;
    }
    public void  run(){
        try {
            System.out.println("Runned1");
            connection = new Socket(InetAddress.getByName(port),1234);
            connection.setTcpNoDelay(true);
            inputStream = new ObjectInputStream(connection.getInputStream());
            outputStream = new ObjectOutputStream(connection.getOutputStream());
            while (isRunning)
            {
                System.out.println("Runned");


                /*x_str =(String) inputStream.readObject();
                x = Integer.parseInt(x_str);
                robot.mouseMove(x,100);*/
            }
        } catch (IOException e) {
            Log.i(null,"bad");
            e.printStackTrace();
           // try {
          //      Thread.sleep(100);
            //} catch (InterruptedException e1) {
             //   e1.printStackTrace();
           // }
            // isRunning = false;
        }
    }
    private   boolean  setUpConnection(){
        while (isConnecting){
            try {
                //Todo connection =
                connection.setTcpNoDelay(true);
                outputStream = new ObjectOutputStream(connection.getOutputStream());
                outputStream.flush();
                inputStream = new ObjectInputStream(connection.getInputStream());
                isConnecting = false;
            }
            catch (IOException e){
                System.out.println("trying");
            }
        }
        return true;
    }

   /* public void sendData(float[] s){
        try {
            System.out.println( this.isRunning);
            outputStream.flush();
            outputStream.writeObject(s);
            outputStream.flush();

        } catch (java.lang.Exception e) {Log.i(null,"Плохо");
           e.printStackTrace();}
    }*/

    public void close() {
        try {
            outputStream.close();
            inputStream.close();
            connection.close();

        } catch (Exception e) {e.printStackTrace();}
    }
}
