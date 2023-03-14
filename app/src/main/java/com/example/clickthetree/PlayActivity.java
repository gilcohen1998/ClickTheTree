package com.example.clickthetree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PlayActivity extends AppCompatActivity {

    private ImageView the_tree;
    private Animation tree_anim;
    private LottieAnimationView money_drop;
    private int cash;
    private TextView money_bar;
    private Animation scale_down;
    private ImageView back_button;
    private ImageView play_background;
    private int MoneyOnClick=0;
    private MediaPlayer click;
    private MediaPlayer clicking_tree;
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
        if(music!=null){
            music.stop();
            music.release();
            music=null;
        }

        if(click!=null){
            click.stop();
            click.release();
            click=null;
        }
        if(clicking_tree!=null){
            clicking_tree.stop();
            clicking_tree.release();
            clicking_tree=null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_activity);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


            click = new MediaPlayer();
            click = MediaPlayer.create(getApplicationContext(), R.raw.clicking_sound);

            clicking_tree = new MediaPlayer();
            clicking_tree = MediaPlayer.create(getApplicationContext(), R.raw.clicking_tree);
            clicking_tree.setVolume(30,30);



        scale_down= AnimationUtils.loadAnimation(this,R.anim.scale_down);
        the_tree= findViewById(R.id.the_tree);
        money_bar= findViewById(R.id.money_bar);
        back_button= findViewById(R.id.back_play_button);
        tree_anim=  AnimationUtils.loadAnimation(this,R.anim.tree_anim);
        money_drop = findViewById(R.id.money_drop);
        play_background=findViewById(R.id.play_background);

        money_drop.setVisibility(View.GONE);
        SharedPreferences money = getSharedPreferences("tree",MODE_PRIVATE);
        cash= money.getInt("money",0);
        money_bar.setText(""+cash);

        if(money.getInt("default",3)==3){
            the_tree.setImageDrawable(getDrawable(R.drawable.default_tree));
            money_drop.setAnimation(R.raw.money_drop2);
            MoneyOnClick=1;
        }
        else if(money.getInt("blue",1)==3){
            the_tree.setImageDrawable(getDrawable(R.drawable.blue_tree));
            money_drop.setAnimation(R.raw.blue_money_drop);
            MoneyOnClick=2;
        }
        else if(money.getInt("cyan",1)==3){
            the_tree.setImageDrawable(getDrawable(R.drawable.cyan_tree));
            money_drop.setAnimation(R.raw.cyan_money_drop);
            MoneyOnClick=4;
        }
        else if(money.getInt("purple",1)==3){
            the_tree.setImageDrawable(getDrawable(R.drawable.purple_tree));
            money_drop.setAnimation(R.raw.purple_money_drop);
            MoneyOnClick=10;
        }
        else if(money.getInt("green",1)==3){
            the_tree.setImageDrawable(getDrawable(R.drawable.green_tree));
            money_drop.setAnimation(R.raw.green_money_drop);
            MoneyOnClick=20;
        }
        else if(money.getInt("orange",1)==3){
            the_tree.setImageDrawable(getDrawable(R.drawable.orange_tree));
            money_drop.setAnimation(R.raw.orange_money_drop);
            MoneyOnClick=40;
        }
        else if(money.getInt("red",1)==3){
            the_tree.setImageDrawable(getDrawable(R.drawable.red_tree));
            money_drop.setAnimation(R.raw.red_money_drop);
            MoneyOnClick=70;
        }
        else if(money.getInt("yellow",1)==3){
            the_tree.setImageDrawable(getDrawable(R.drawable.yellow_tree));
            money_drop.setAnimation(R.raw.yellow_money_drop);
            MoneyOnClick=140;
        }

        SharedPreferences background= getSharedPreferences("background",MODE_PRIVATE);

        if(background.getInt("default",3)==3){
            play_background.setImageDrawable(getDrawable(R.drawable.background_default_tree_background));
        }
        else if(background.getInt("desert",3)==3){
            play_background.setImageDrawable(getDrawable(R.drawable.background_desert_background));
        }
        else if(background.getInt("desert2",3)==3){
            play_background.setImageDrawable(getDrawable(R.drawable.background_desert2_background));
        }
        else if(background.getInt("farm_nature",3)==3){
            play_background.setImageDrawable(getDrawable(R.drawable.background_farmnature_background));
        }
        else if(background.getInt("rainbow",3)==3){
            play_background.setImageDrawable(getDrawable(R.drawable.background_rainbow_background));
        }



        back_button.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {


                            click.start();


                        if(event.getAction()==MotionEvent.ACTION_DOWN){
                            back_button.startAnimation(scale_down);
                        }
                        Intent intent = new Intent(PlayActivity.this, HubActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                    }
                });

        the_tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    clicking_tree.start();

                money_drop.setVisibility(View.VISIBLE);
                money_drop.setSpeed(5);
                money_drop.setScaleX(2);
                money_drop.playAnimation();
                cash+=MoneyOnClick;
                SharedPreferences.Editor money = getSharedPreferences("tree",MODE_PRIVATE).edit();
                money.putInt("money",cash);
                money.apply();
                money_bar.setText(""+cash);


                new CountDownTimer(800, 10) {

                    public void onTick(long millisUntilFinished) {
                        money_bar.setTextColor(getColor(R.color.money));
                    }

                    public void onFinish() {
                        money_bar.setTextColor(getColor(android.R.color.black));
                    }

                }.start();

            }
        });

        the_tree.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    the_tree.startAnimation(tree_anim);

                }


                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    money_bar.startAnimation(scale_down);
                }
                return false;

            }



        });





    }
}