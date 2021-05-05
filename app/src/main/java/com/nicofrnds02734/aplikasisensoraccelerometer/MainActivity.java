package com.nicofrnds02734.aplikasisensoraccelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SensorManager sm;
    TextView text1, text2, text3;
    LinearLayout relatif1;
    List list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        relatif1 = findViewById(R.id.relatif1);

        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] nilai = event.values;
                text1.setText("X : " + nilai[0]);
                text2.setText("Y : " + nilai[1]);
                text3.setText("Z : " + nilai[2]);

                if(nilai[1] < 9 ) {
                    relatif1.setBackgroundColor(Color.parseColor("#e97878"));
                    text1.setTextColor(Color.parseColor("#fa1e0e"));
                    text2.setTextColor(Color.parseColor("#fa1e0e"));
                    text3.setTextColor(Color.parseColor("#fa1e0e"));

                }
                if(nilai[1] >= 9) {
                    relatif1.setBackgroundColor(Color.parseColor("#f9f3f3"));
                    text1.setTextColor(Color.parseColor("#025955"));
                    text2.setTextColor(Color.parseColor("#025955"));
                    text3.setTextColor(Color.parseColor("#025955"));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(list.size() > 0){

            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }

    }
}