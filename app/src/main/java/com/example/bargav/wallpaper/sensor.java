package com.example.bargav.wallpaper;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by bargav on 7/4/2016.
 */
public class sensor extends AppCompatActivity implements SensorEventListener {
    ImageView image;
    SensorManager mSensorManager;
    Sensor mSensor;
    public int currentimageindex = 0;
    int[] images ={R.drawable.brain,R.drawable.cat,R.drawable.cons,R.drawable.hello,R.drawable.ihave,R.drawable.me,R.drawable.shit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        image=(ImageView)findViewById(R.id.thing);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {

        super.onPause();
        mSensorManager.unregisterListener(this);

    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    public void onSensorChanged(SensorEvent event) {

        if (event.values[0] == 0) {
        image.setImageResource(images[currentimageindex%images.length]);

        }else
        {
            image.setImageResource(images[currentimageindex%images.length]);
        }
        currentimageindex++;

    }

}
