package com.example.bargav.wallpaper;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Spinner songs;
    MediaPlayer mp;
    String[] songcheck = {"21 guns", "Happy", "Counting Stars", "Pompeii", "Turn down for what", "Story of my life", "thinking out loud", "Sweater weather"};
    int clicked=0,re=0,currentimageindex;
    int[] images ={R.drawable.brain,R.drawable.cat,R.drawable.cons,R.drawable.hello,R.drawable.ihave,R.drawable.me,R.drawable.shit};
    String thesong;
    TextView times;
    TextView check;
    int counter=3,c=0,slidecounter=0,k=0,l=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songs= (Spinner)findViewById(R.id.songs);
        final TextView check=(TextView)findViewById(R.id.textView2);
        Switch mySwitch;
        ArrayAdapter<CharSequence>
                adapter = ArrayAdapter.createFromResource(this, R.array.songs, android.R.layout.select_dialog_singlechoice);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        songs.setAdapter(adapter);
        setContentView(R.layout.activity_main);

    }
    public void play(View view)
    {
        clicked++;
        Spinner song=(Spinner)findViewById(R.id.songs);
        thesong = song.getSelectedItem().toString();
        Intent player = new Intent(MainActivity.this, music_service.class);
        player.putExtra("song name", thesong);
        player.putExtra("restart",re);
        player.putExtra("clicked", clicked);
        startService(player);

    }
    public void restart(View view)
    {
        re=1;
        Intent res= new Intent(MainActivity.this,music_service.class);
        res.putExtra("restart",re);
        startService(res);
        re=0;

    }
    public void SlideShow( View view) {
            Intent intent = new Intent(this, slides.class);
            startActivity(intent);



    }
    public void sensor(View view)
    {
        Intent i= new Intent(this,sensor.class);
        startActivity(i);
    }
}
