package com.example.clickthetree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainShopsActivity extends AppCompatActivity {

    private Animation scale_down;
    private TextView tree_shop_button;
    private TextView background_shop_button;
    private TextView music_shop_button;
    private ImageView back_mainshop_button;
    private MediaPlayer click;


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(click!=null){
            click.stop();
            click.release();
            click=null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_shops_activity);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        click= new MediaPlayer();
        click= MediaPlayer.create(getApplicationContext(),R.raw.clicking_sound);


        scale_down= AnimationUtils.loadAnimation(this,R.anim.scale_down);
        tree_shop_button=findViewById(R.id.tree_shop_button);
        background_shop_button=findViewById(R.id.background_shop_button);
        music_shop_button=findViewById(R.id.music_shop_button);
        back_mainshop_button=findViewById(R.id.back_mainshop_button);

        tree_shop_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                click.start();

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    tree_shop_button.startAnimation(scale_down);
                }
                Intent intent = new Intent(MainShopsActivity.this, TreeShopActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

        background_shop_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                click.start();

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    background_shop_button.startAnimation(scale_down);
                }
                Intent intent = new Intent(MainShopsActivity.this, BackgroundShopActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

        music_shop_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                click.start();

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    music_shop_button.startAnimation(scale_down);
                }
                Intent intent = new Intent(MainShopsActivity.this, MusicShopActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

        back_mainshop_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                click.start();

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    back_mainshop_button.startAnimation(scale_down);
                }
                Intent intent = new Intent(MainShopsActivity.this, HubActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

    }
}