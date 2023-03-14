package com.example.clickthetree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;



public class HubActivity extends AppCompatActivity {


    private Button play_button,shop_button,about_button;
    private Animation scale_down;
    private MediaPlayer click;
    private MediaPlayer music;
    private SharedPreferences music_disc;



    @Override
    protected void onResume() {
        super.onResume();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        music_disc= getSharedPreferences("music",MODE_PRIVATE);



            music = new MediaPlayer();

            if (music_disc.getInt("default", 3) == 3) {
                music = MediaPlayer.create(getApplicationContext(), R.raw.default_music);

            } else if (music_disc.getInt("blue", 1) == 3) {
                music = MediaPlayer.create(getApplicationContext(), R.raw.blue_music);

            } else if (music_disc.getInt("purple", 1) == 3) {
                music = MediaPlayer.create(getApplicationContext(), R.raw.purple_music);
            } else if (music_disc.getInt("orange", 1) == 3) {
                music = MediaPlayer.create(getApplicationContext(), R.raw.orange_music);
            }

            music.start();
            music.setLooping(true);
        }



    @Override
    protected void onPause() {
        super.onPause();
            if(music!=null) {
                music.pause();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(music!=null) {
            music.stop();
            music.release();
            music=null;
        }

        if(click!=null){
            click.stop();
            click.release();
            click=null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hub_activity);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);



            click= new MediaPlayer();
            click= MediaPlayer.create(getApplicationContext(),R.raw.clicking_sound);



        play_button= findViewById(R.id.play_botton);
        shop_button = findViewById(R.id.shop_button);
        about_button= findViewById(R.id.about_button);
        scale_down= AnimationUtils.loadAnimation(this,R.anim.scale_down);



        play_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                 if(event.getAction()==MotionEvent.ACTION_DOWN){
                    play_button.startAnimation(scale_down);

                        click.start();
                }
                Intent intent = new Intent(HubActivity.this, PlayActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

        shop_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    shop_button.startAnimation(scale_down);


                        click.start();
                    }
                Intent intent = new Intent(HubActivity.this, MainShopsActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

        about_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    about_button.startAnimation(scale_down);

                        click.start();
                    }
                Intent intent = new Intent(HubActivity.this, AboutActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });


    }
}