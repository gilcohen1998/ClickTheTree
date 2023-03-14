package com.example.clickthetree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private MediaPlayer click;
    private ImageView back_button;
    private Animation scale_down;
    private ImageView facebook;
    private ImageView instagram;
    private ImageView gmail;
    private ImageView tasklist;


    @Override
    protected void onResume() {
        super.onResume();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        click= new MediaPlayer();
        click= MediaPlayer.create(getApplicationContext(),R.raw.clicking_sound);
        back_button= findViewById(R.id.back_about_button);
        scale_down= AnimationUtils.loadAnimation(this,R.anim.scale_down);
        gmail=findViewById(R.id.gmail_image);
        facebook=findViewById(R.id.facebook_image);
        instagram=findViewById(R.id.instagram_image);
        tasklist=findViewById(R.id.tasklist);

        back_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                click.start();

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    back_button.startAnimation(scale_down);
                }
                Intent intent = new Intent(AboutActivity.this, HubActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

        gmail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    gmail.startAnimation(scale_down);
                }
                golink("mailto:gilc1998@gmail.com");
                return false;
            }
        });


        instagram.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    instagram.startAnimation(scale_down);
                }
                golink("https://www.instagram.com/gilcohen55");
                return false;
            }
        });

        facebook.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    facebook.startAnimation(scale_down);
                }
                golink("https://www.facebook.com/profile.php?id=100001783674694");
                return false;
            }
        });

        tasklist.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    tasklist.startAnimation(scale_down);
                }
                golink("https://play.google.com/store/apps/details?id=com.bawp.tasklist");
                return false;
            }
        });
    }
    private void golink(String s){
        Uri uri =Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}