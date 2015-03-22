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
    Activity_Main main;
    public static ObjectInputStream getInputStream() {
        return inputStream;
    }
    LooperThread looperThread;
    public static ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public String port = "192.168.1.2";
    private boolean isListening,isConnecting;
    boolean onUpdate;
    String  x_str;
    int x;
    private boolean isRunning = false;
    public void setIsRunning(boolean i){
        this.isRunning = i;
    }
    public void  run(){

            if(isRunning) {
                isConnecting = true;
              setUpConnection();
                try {

                    listen();

                    } catch (IOException e) {
                        System.out.println("2131");
                        e.printStackTrace();
                    }catch (NullPointerException e){
                    e.printStackTrace();
                }
                }
            }
       // catch (IOException e) {
           // Log.i(null,"bad");
            //e.printStackTrace();
           // try {
          //      Thread.sleep(100);
            //} catch (InterruptedException e1) {
             //   e1.printStackTrace();
           // }
            // isRunning = false;
       // }

    private   void  setUpConnection(){
        while (isConnecting){
            try {
                System.out.println("Runned1");
                connection = new Socket(InetAddress.getByName(port),1234);
                connection.setTcpNoDelay(true);
                outputStream = new ObjectOutputStream(connection.getOutputStream());
                outputStream.flush();
                inputStream = new ObjectInputStream(connection.getInputStream());
                isConnecting = false;
                isListening = true;
            }
            catch (IOException e){
               Log.i(null,"trying");
                e.printStackTrace();
                if(java.net.UnknownHostException.class == e.getClass()){
                    port="192.168.1.4";
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }
    private void listen() throws IOException {
        looperThread = new LooperThread();
        looperThread.run();
    }
    public void doInBackground(float[] f){
        Log.i(null, "doInBack: " + f[0] + " | " +f[1] + " | " + f[2]);
        looperThread.addInStack(f);
    }
    public boolean isListening() {
        return isListening;
    }

    public boolean isRunning() {
        return isRunning;
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
    public void reRun(){
        isRunning=true;
        if(isRunning) {
            isConnecting = true;
            setUpConnection();
            try {

                listen();

            } catch (IOException e) {
                System.out.println("2131");
                e.printStackTrace();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }

    }
}
