package com.example.bargav.wallpaper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class slides extends Activity{
    int[] images ={R.drawable.brain,R.drawable.cat,R.drawable.cons,R.drawable.hello,R.drawable.ihave,R.drawable.me,R.drawable.shit};
    int k=0,slidecounter=0,num=1,p=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide);
        final TextView times = (TextView)findViewById(R.id.counter);
        final ImageView thing = (ImageView) findViewById(R.id.thing);
        final Handler mHandler = new Handler();
        final Runnable slides = new Runnable() {
            public void run() {
                thing.setImageResource(images[slidecounter]);

            }

        };
        final Runnable time =new Runnable() {
            @Override
            public void run() {
                    times.setText(""+p);
                p++;


            }
        } ;


        Thread slideshow = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                    synchronized (this)
                    {
                        while(slidecounter<7)
                        {
                            mHandler.post(slides);
                            for (k=0;k<3;k++) {
                                mHandler.post(time);
                                Thread.sleep(1000);
                            }
                            p=1;
                            slidecounter++;
                        }

                    }

                } catch (InterruptedException e) {
                }

            }
        };
        slideshow.start();
        k++;
        slidecounter++;




    }

}
