package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    public static final String TAG= MainActivity.class.getSimpleName();
    public static final String KEY_CHRONOMETER_BASE = "chronometer base";
    public static final String KEY_BUTTON_STATE = "button setting";
    public static final String KEY_BUTTON_FIRST = "button start";
    private Button activate;
    private Button restart;
    private Chronometer display;
    private boolean setting;
    private long pause;
    private long current;
    private long difference;
    private boolean start;

    // Look up the Log class for android
    // list all the levels of logging and when they're used
    // order them from lowest priority to highest priority
    // Verbose Log.v (lowest priority)
    // Debug Log.d
    // Info Log.i
    // Warning Log.w
    // Error Log.e

    // What methods are called when you do various phone actions?
// launched app--> onCreate, onStart, onResume
// rotate --> onPause, onStop, onRestart, onStart, onResume
// hit the square button --> onPause
// click back on the app from the square button --> onResume
// hit the circle button --> onPause
// relaunch the app from the phone navigation (not play button)--> onResume
// hit the back button --> onPause, onStop
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
                    current = SystemClock.elapsedRealtime();
                    difference = current-pause;
                    if (start == false) {
                        start = true;
                        difference = 0;
                    }
                    setting = true;
                    activate.setText(getString(R.string.main_pause));
                    long time = display.getBase();
                    display.setBase(time+difference);
                    display.start();
                }
                else
                {

                    pause = SystemClock.elapsedRealtime();
                    activate.setText(getString(R.string.main_activate));
                    setting = false;
                    display.stop();
                }
            }
        }
        );
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.setBase(SystemClock.elapsedRealtime());
                pause = SystemClock.elapsedRealtime();
                current = SystemClock.elapsedRealtime();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();
        if (savedInstanceState != null)
        {
            setting = savedInstanceState.getBoolean(KEY_BUTTON_STATE);
            display.setBase(savedInstanceState.getLong(KEY_CHRONOMETER_BASE));
            if (savedInstanceState.getBoolean(KEY_BUTTON_STATE)==true)
            {
                activate.setText("pause");

                start = savedInstanceState.getBoolean(KEY_BUTTON_FIRST);
                display.start();
            }

        }

        Log.d(TAG, "onCreate: ");



        // if the savedInstanceState isn't null
        // pull out the value of the base that we saved from the bundle
        // set the chronometer's base to that value
        // start the chronometer


        // next functionality would be to also store in the bundle
        // whether it was running or stopped to decide if you
        // should start it or not
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_CHRONOMETER_BASE, display.getBase());
        outState.putBoolean(KEY_BUTTON_STATE, setting);
        outState.putBoolean(KEY_BUTTON_FIRST, start);
    }
}
