package com.example.eggtimerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    CountDownTimer countDownTimer ;
    boolean counterIsActive = false;
    Button gobutton;
    MediaPlayer mediaPlayer ;



    public void updatetime(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft - (minutes*60);
        String secondstring = Integer.toString(seconds);
        if(seconds<=9){
            secondstring = "0"+secondstring;
        }
        textView.setText(Integer.toString(minutes) + ":" + secondstring);
    }

    public void resetTime (){
        textView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        gobutton.setText("Go");
        counterIsActive = false;

    }

    public void buttonClick (View view) {
        if (counterIsActive) {
          resetTime();
            mediaPlayer.stop();

        } else {
            counterIsActive = true;
            seekBar.setEnabled(false);
            gobutton.setText("STOP");


            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updatetime((int) l / 1000);

                }

                @Override
                public void onFinish() {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.muscle);
                    mediaPlayer.start();
                    resetTime();

                }
            }.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        gobutton = findViewById(R.id.button);


        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updatetime(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
