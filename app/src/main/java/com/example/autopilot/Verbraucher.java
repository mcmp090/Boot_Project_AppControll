package com.example.autopilot;


import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Verbraucher extends AppCompatActivity{
    public Verbraucher(){}
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.verbraucher);


            Thread thread = new Thread(runnable);
            thread.start();




        }
        //setContentView(R.layout.verbraucher);


    Runnable runnable = new Runnable(){
        public void run() {
            while(true) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss", Locale.GERMANY);
                String datum_Zeit = dateFormat.format(new java.util.Date());
                TextView tv = (TextView) findViewById(R.id.textViewTime);
                tv.setText(datum_Zeit);
            }
        }
    };

}
