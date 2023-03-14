package com.example.clickthetree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

public class StarterActivity extends AppCompatActivity {
    private MediaPlayer start;




    @Override
    protected void onResume() {
        super.onResume();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (start != null) {
            start.stop();
            start.release();
            start=null;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starter_activity);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        start=new MediaPlayer();
        start=MediaPlayer.create(getApplicationContext(),R.raw.start);


        SharedPreferences money = getSharedPreferences("tree",MODE_PRIVATE);
        money.getInt("money",0);


        Thread td= new Thread(){
            public void run(){
                try {
                    sleep(5000);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                finally{
                    Intent intent = new Intent(StarterActivity.this,HubActivity.class);
                    startActivity(intent);
                    finish();
                }
                }
            }; td.start();

        new CountDownTimer(1600, 20) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                start.start();
            }

        }.start();
        }



    }