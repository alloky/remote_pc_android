package com.alloky.myapplication.app;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by admin on 15.03.2015.
 */
public class stackMsg {
    public Handler handler;
    stackMsg(){
        handler = new Handler() {
            @Override
            public void close() {

            }

            @Override
            public void flush() {

            }

            @Override
            public void publish(LogRecord record) {

            }
        };
    }
    void addInStack(float[] mas){
     //TODO addInstack stuff
    }
}
