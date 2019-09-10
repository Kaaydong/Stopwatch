package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private Button activate;
    private Button restart;
    private Chronometer display;
    private boolean setting;

    public static final String TAG = MainActivity.class.getSimpleName();
    // Look up the Log class for android
    // list all the levels of logging and when they're used
    // order them from lowest priority to highest priority
    // Verbose Log.v (lowest priority)
    // Debug Log.d
    // Info Log.i
    // Warning Log.w
    // Error Log.e

    // launch app--> onCreate, onStart, onResume

    public void wireWidgets()
    {
        activate = findViewById(R.id.button_main_activate);
        restart = findViewById(R.id.button_main_reset);
        display = findViewById(R.id.chronometer_main_display);
    }
    public void setListeners()
    {
        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (setting == false) {
                    setting = true;
                    activate.setText("Pause");
                    long time = display.getBase();
                    display.setBase(time);
                    display.start();
                }
                else
                {
                    activate.setText("Start");
                    setting = false;
                    display.stop();
                }
            }
        }
        );
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setting = false;
                display.setBase(SystemClock.elapsedRealtime());
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();
        Log.d(TAG, "onCreate: ");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
