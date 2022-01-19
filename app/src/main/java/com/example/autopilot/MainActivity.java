package com.example.autopilot;


import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{


    public int screen = 1;
    public int x = 1;
    public boolean posl = false;
    public boolean tiefm = false;
    public boolean lichv = false;
    public boolean lichh = false;
    public boolean v12 = false;
    public boolean usb = false;
    public static long timeout;
    public static int resu;
    public static int num;
    public static int fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        Thread thread = new Thread(runnable);
        thread.start();

        ImageButton simpleImageButton = (ImageButton)findViewById(R.id.imageButton4);
        //ImageButton AndroidImageButton = (ImageButton) findViewById(R.id.imageView7);

        simpleImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 2;
                screen2();

                //Verbraucher verbraucher = new Verbraucher();
                //x = false;
                //TextView tv = (TextView) findViewById(R.id.textViewTime);
                //tv.setText("Es hat geklappt!");
            }
        });



    }

    public void screen1(){
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(runnable);
        thread.start();
        ImageButton simpleImageButton = (ImageButton)findViewById(R.id.imageButton4);
        //ImageButton AndroidImageButton = (ImageButton) findViewById(R.id.imageView7);

        simpleImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 2;
                screen2();
            }
        });

    }

    public void screen2(){
        setContentView(R.layout.verbraucher);
        ImageView myImage = (ImageView) findViewById(R.id.imageView7);
        myImage.setAlpha(20);
        Thread s2thread = new Thread(runnable2);
        s2thread.start();

        Button Haupt = (Button) findViewById(R.id.button1);
        Haupt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 1;
                screen1();
            }
        });

        Button boot = (Button) findViewById(R.id.buttonboot);
        boot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 3;
                try {
                    screen3();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button verb = (Button) findViewById(R.id.buttonverbraucher);
        verb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 4;
                try {
                    screen4();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void screen3() throws IOException {
        setContentView(R.layout.bootlayout);
        ImageView myImage = (ImageView) findViewById(R.id.imageView7);
        myImage.setAlpha(20);
        Thread s2thread = new Thread(runnable3);
        s2thread.start();

        Button Haupt = (Button) findViewById(R.id.button1);
        Haupt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 2;
                screen2();
            }
        });

        Button pos = (Button) findViewById(R.id.Poslicht);

        if(communication("2.1.1") == 0) {
            pos.setBackgroundColor(getResources().getColor(R.color.aus));
        }else{
            pos.setBackgroundColor(getResources().getColor(R.color.an));
        }
        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                try {
                    if(communication("1.1.1") == 1) {
                        posl = true;
                        pos.setBackgroundColor(getResources().getColor(R.color.an));
                    }else{
                        posl = false;
                        pos.setBackgroundColor(getResources().getColor(R.color.aus));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button tief = (Button) findViewById(R.id.tiefmess);
        if(communication("2.2.1") == 0) {
            tief.setBackgroundColor(getResources().getColor(R.color.aus));
        }else{
            tief.setBackgroundColor(getResources().getColor(R.color.an));
        }
        tief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(communication("1.2.1") == 1) {
                        tiefm = true;
                        tief.setBackgroundColor(getResources().getColor(R.color.an));
                    }else{
                        tiefm = false;
                        tief.setBackgroundColor(getResources().getColor(R.color.aus));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void screen4() throws IOException {
        setContentView(R.layout.verblayout);
        ImageView myImage = (ImageView) findViewById(R.id.imageView7);
        myImage.setAlpha(20);
        Thread s2thread = new Thread(runnable4);
        s2thread.start();

        Button Haupt = (Button) findViewById(R.id.button1);
        Haupt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 2;
                screen2();
            }
        });

        Button liv = (Button) findViewById(R.id.lichtv);
        if(communication("2.3.1") == 0) {
            liv.setBackgroundColor(getResources().getColor(R.color.aus));
        }else{
            liv.setBackgroundColor(getResources().getColor(R.color.an));
        }
        liv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(communication("1.3.1") == 1) {
                        lichv = true;
                        liv.setBackgroundColor(getResources().getColor(R.color.an));
                    }else{
                        lichv = false;
                        liv.setBackgroundColor(getResources().getColor(R.color.aus));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button lih = (Button) findViewById(R.id.lichth);
        if(communication("2.4.1") == 0) {
            lih.setBackgroundColor(getResources().getColor(R.color.aus));
        }else{
            lih.setBackgroundColor(getResources().getColor(R.color.an));
        }
        lih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(communication("1.4.1") == 1) {
                        lichh = true;
                        lih.setBackgroundColor(getResources().getColor(R.color.an));
                    }else{
                        lichh = false;
                        lih.setBackgroundColor(getResources().getColor(R.color.aus));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button volt12 = (Button) findViewById(R.id.v12);
        if(communication("2.5.1") == 0) {
            volt12.setBackgroundColor(getResources().getColor(R.color.aus));
        }else{
            volt12.setBackgroundColor(getResources().getColor(R.color.an));
        }
        volt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(communication("1.5.1") == 1) {
                        v12 = true;
                        volt12.setBackgroundColor(getResources().getColor(R.color.an));
                    }else{
                        v12 = false;
                        volt12.setBackgroundColor(getResources().getColor(R.color.aus));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button USB = (Button) findViewById(R.id.usb);
        if(communication("2.6.1") == 0) {
            USB.setBackgroundColor(getResources().getColor(R.color.aus));
        }else{
            USB.setBackgroundColor(getResources().getColor(R.color.an));
        }
        USB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(communication("1.6.1") == 1) {
                        usb = true;
                        USB.setBackgroundColor(getResources().getColor(R.color.an));
                    }else{
                        usb = false;
                        USB.setBackgroundColor(getResources().getColor(R.color.aus));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }



    Runnable runnable = new Runnable(){
        public void run() {


                    // Stuff that updates the UI
                    while(x == 1) {


                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.GERMANY);
                        String datum_Zeit = dateFormat.format(new java.util.Date());

                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    if(x == 1) {
                                        TextView tv = (TextView) findViewById(R.id.textViewTime);
                                        tv.setText(datum_Zeit);
                                    }
                                }
                            });
                            //writeTime(datum_Zeit);

                    }



        }
    };

    Runnable runnable2 = new Runnable(){
        public void run() {
            while(x == 2) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.GERMANY);
                String datum_Zeit = dateFormat.format(new java.util.Date());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(x == 2) {
                                    TextView tv = (TextView) findViewById(R.id.textViewTime1);
                                    tv.setText(datum_Zeit);
                                }
                            }
                        });


            }
        }
    };

    Runnable runnable3 = new Runnable(){
        public void run() {
            while(x == 3) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.GERMANY);
                String datum_Zeit = dateFormat.format(new java.util.Date());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(x == 3) {
                            TextView tv = (TextView) findViewById(R.id.textViewTime1);
                            tv.setText(datum_Zeit);
                        }
                    }
                });


            }
        }
    };

    Runnable runnable4 = new Runnable(){
        public void run() {
            while(x == 4) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.GERMANY);
                String datum_Zeit = dateFormat.format(new java.util.Date());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(x == 4) {
                            TextView tv = (TextView) findViewById(R.id.textViewTime1);
                            tv.setText(datum_Zeit);
                        }
                    }
                });


            }
        }
    };


    public int communication(String command) throws IOException {

        fin = 0;
        Socket socket = new Socket("192.168.178.115", 23);
        while (!socket.isConnected()) {
            System.out.print(".");
        }
        System.out.print("Is Connected!");
        //Thread.sleep(500);
        socket.getOutputStream().write("t".getBytes());
        socket.getOutputStream().flush();
        //Thread.sleep(500);
        socket.getOutputStream().write(command.getBytes());
        socket.getOutputStream().flush();
        //Thread.sleep(500);
        socket.getOutputStream().write("t".getBytes());
        socket.getOutputStream().flush();
        timeout = System.currentTimeMillis();

        //System.out.print(socket.getInputStream().read());


        while((resu = socket.getInputStream().read()) < 0) {
            if(System.currentTimeMillis() - timeout > 5000) {
                break;
            }
        }

        for(int i=0; i < resu; i++) {
            while((num = socket.getInputStream().read()) < 0) {
                if(System.currentTimeMillis() - timeout > 5000) {
                    break;
                }
            }
            fin = fin + num;
        }


        //Thread.sleep(500);
        socket.close();
        System.out.println(fin);
        System.out.println("Is Disconnected!");
        return fin;
    }

}

