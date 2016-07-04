package com.example.bargav.wallpaper;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class music_service extends Service{
    MediaPlayer mp;
    String[] songcheck = {"21 guns", "Happy", "Counting Stars", "Pompeii", "Turn down for what", "Story of my life", "Sweater weather"};
    int num=0,clicked=0;
    String t;
    int same=0,restart=0,paused;
    int[] audio={ R.raw.guns, R.raw.happy, R.raw.counting_stars, R.raw.pompeii, R.raw.turn_down_for_what, R.raw.story_of_my_life, R.raw.sweater_weather};
    ArrayList<Integer> playlist;
    @Override
    public IBinder onBind(Intent argo0) {
        return null;
    }

    private static final String LOGCAT = null;
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(LOGCAT, "Service Started!");
        ArrayList<Integer> playlist;
        playlist=new ArrayList<>();
        playlist.add(R.raw.guns);
        playlist.add(R.raw.happy);
        playlist.add(R.raw.counting_stars);
        playlist.add(R.raw.pompeii);
        playlist.add(R.raw.turn_down_for_what);
        playlist.add(R.raw.story_of_my_life);
        playlist.add(R.raw.sweater_weather);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        AudioManager songplaying;
        songplaying= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        t=intent.getExtras().getString("song name");
        restart=intent.getExtras().getInt("restart");
        clicked=intent.getExtras().getInt("clicked");

        for (int i = 0; i < 7; i++) {
            if (songcheck[i].equals(t)) {
                num = i;

            }
        }
       if(restart==0)
       {
           if(clicked%2==0)//even
           {
               mp.pause();
               paused=mp.getCurrentPosition();


           }
           if(clicked%2!=0)//odd
           {
               if (clicked==1)
               {
                   mp = MediaPlayer.create(this, audio[num]);
                   mp.start();
               }
               if(same==num)//same song
               {
                   mp.seekTo(paused);
                   mp.start();
               }
               else//diff song
               {
                   if(!mp.isPlaying())
                   {
                       mp = MediaPlayer.create(this, audio[num]);
                       mp.start();
                   }
                   else
                   {
                       mp.stop();
                       mp = MediaPlayer.create(this, audio[num]);
                       mp.start();

                   }
               }


           }
       }
        if(restart==1)
        {
             if(songplaying.isMusicActive()==true)
            {
                Log.d(LOGCAT, "restart=1 ");
                //mp =MediaPlayer.create(this,audio[num]);
                mp.pause();
                mp.seekTo(0);
                mp.start();
            }
            else
            {

                //mp =MediaPlayer.create(this,audio[num]);
                mp.seekTo(0);
                mp.start();
            }

        }
        Log.d(LOGCAT, "onStart started!");
        same=num;
        return 1;
    }


    @Override
    public void onDestroy()
    {
        AudioManager songplaying;
        songplaying= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if(songplaying.isMusicActive()==true)
          //  mp.stop();

        Log.d(LOGCAT, "onStop started!");
    }



}
