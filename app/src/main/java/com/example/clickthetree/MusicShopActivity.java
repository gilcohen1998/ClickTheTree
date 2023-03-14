package com.example.clickthetree;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MusicShopActivity extends AppCompatActivity {
    private TextView music_text1;
    private TextView music_text2;
    private TextView music_text3;
    private TextView music_text4;
    private TextView money_bar;
    private ImageView back_button;
    private Animation scale_down;
    private SharedPreferences money;
    private SharedPreferences music;
    private MediaPlayer click;
    private MediaPlayer buying_item;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(click!=null){
            click.stop();
            click.release();
            click=null;
        }
        if(buying_item!=null){
            buying_item.stop();
            buying_item.release();
            buying_item=null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_shop_activity);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        click= new MediaPlayer();
        click= MediaPlayer.create(getApplicationContext(),R.raw.clicking_sound);

        buying_item=new MediaPlayer();
        buying_item=MediaPlayer.create(getApplicationContext(),R.raw.buying_item);


        music_text1= findViewById(R.id.mframe_text1);
        music_text2=findViewById(R.id.mframe_text2);
        music_text3=findViewById(R.id.mframe_text3);
        music_text4=findViewById(R.id.mframe_text4);
        money_bar=findViewById(R.id.money_bar_musicshop);
        back_button=findViewById(R.id.back_musicshop_button);
        scale_down= AnimationUtils.loadAnimation(this,R.anim.scale_down);

        money = getSharedPreferences("tree",MODE_PRIVATE);
        money_bar.setText(""+money.getInt("money",0));

        back_button.setOnTouchListener((v, event) -> {

            click.start();

            if(event.getAction()==MotionEvent.ACTION_DOWN){
                back_button.startAnimation(scale_down);
            }
            Intent intent = new Intent(MusicShopActivity.this, MainShopsActivity.class);
            startActivity(intent);
            finish();
            return true;
        });

        // 1 is unsold, 2 is unselected, 3 is selected

        music= getSharedPreferences("music",MODE_PRIVATE);
        music.getInt("default",3);
        music.getInt("blue",1);
        music.getInt("purple",1);
        music.getInt("orange",1);

        // Default music text shop

        if(music.getInt("default",3)==2){
            music_text1.setText(R.string.unselected_text);
        }
        else if(music.getInt("default",3)==3){
            music_text1.setText(R.string.selected_text);
        }

        // Blue music text shop

        if(music.getInt("blue",1)==1){
            music_text2.setText(R.string.money1_text);
        }
        else if(music.getInt("blue",1)==2){
            music_text2.setText(R.string.unselected_text);
            music_text2.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }
        else if(music.getInt("blue",1)==3){
            music_text2.setText(R.string.selected_text);
            music_text2.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }

        // Purple music text shop

        if(music.getInt("purple",1)==1){
            music_text3.setText(R.string.money2_text);
        }
        else if(music.getInt("purple",1)==2){
            music_text3.setText(R.string.unselected_text);
            music_text3.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }
        else if(music.getInt("purple",1)==3){
            music_text3.setText(R.string.selected_text);
            music_text3.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }

        // Orange music text shop

        if(music.getInt("orange",1)==1){
            music_text4.setText(R.string.money3_text);
        }
        else if(music.getInt("orange",1)==2){
            music_text4.setText(R.string.unselected_text);
            music_text4.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }
        else if(music.getInt("orange",1)==3){
            music_text4.setText(R.string.selected_text);
            music_text4.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }


        music_text1.setOnClickListener(v -> {

            click.start();

            SharedPreferences.Editor edit = getSharedPreferences("music", MODE_PRIVATE).edit();

            if(music.getInt("default",3)==2){

                if(music.getInt("blue",1)==3){
                    edit.putInt("blue",2);
                    edit.apply();
                    music_text2.setText(R.string.unselected_text);
                }
                else if(music.getInt("purple",1)==3){
                    edit.putInt("purple",2);
                    edit.apply();
                    music_text3.setText(R.string.unselected_text);
                }
                else if(music.getInt("orange",1)==3){
                    edit.putInt("orange",2);
                    edit.apply();
                    music_text4.setText(R.string.unselected_text);
                }

                edit.putInt("default", 3);
                edit.apply();
                music_text1.setText(R.string.selected_text);
                Toast.makeText(MusicShopActivity.this, "Selected music", Toast.LENGTH_SHORT).show();
            }
            else if(music.getInt("default",3)==3){
                Toast.makeText(MusicShopActivity.this, "You already selected this music", Toast.LENGTH_SHORT).show();
            }
        });

        music_text2.setOnClickListener(v -> {

            click.start();

            if(music.getInt("blue",1)==1) {
                if (money.getInt("money", 0) < 210000) {
                    Toast.makeText(MusicShopActivity.this, "You dont have enough money to buy this music", Toast.LENGTH_SHORT).show();
                } else if (money.getInt("money", 0) >= 210000) {
                    AlertDialog.Builder builder;
                    builder=new AlertDialog.Builder(MusicShopActivity.this);
                    builder.setTitle("Notice!")
                            .setMessage("Are u sure you want to buy this music?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", (dialog, which) -> {

                                buying_item.start();

                                Toast.makeText(MusicShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                edit.putInt("money", money.getInt("money", 0) - 210000);
                                edit.apply();
                                SharedPreferences.Editor editor = getSharedPreferences("music",MODE_PRIVATE).edit();
                                editor.putInt("blue",2);
                                editor.apply();
                                music_text2.setText(R.string.unselected_text);
                                music_text2.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                money_bar.setText(""+money.getInt("money", 0));
                            })
                            .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                            .show();
                }
            }
            else if(music.getInt("blue",1)==3){
                Toast.makeText(MusicShopActivity.this, "You already selected this music", Toast.LENGTH_SHORT).show();
            }
            else if(music.getInt("blue",1)==2){

                SharedPreferences.Editor edit = getSharedPreferences("music", MODE_PRIVATE).edit();

                if(music.getInt("default",3)==3){
                    edit.putInt("default",2);
                    edit.apply();
                    music_text1.setText(R.string.unselected_text);
                }
                else if(music.getInt("purple",1)==3){
                    edit.putInt("purple",2);
                    edit.apply();
                    music_text3.setText(R.string.unselected_text);
                }
                else if(music.getInt("orange",1)==3){
                    edit.putInt("orange",2);
                    edit.apply();
                    music_text4.setText(R.string.unselected_text);
                }
                edit.putInt("blue", 3);
                edit.apply();
                music_text2.setText(R.string.selected_text);
                Toast.makeText(MusicShopActivity.this, "Selected music", Toast.LENGTH_SHORT).show();
            }

        });

        music_text3.setOnClickListener(v -> {

            click.start();

            if(music.getInt("purple",1)==1) {
                if (money.getInt("money", 0) < 720000) {
                    Toast.makeText(MusicShopActivity.this, "You dont have enough money to buy this music", Toast.LENGTH_SHORT).show();
                } else if (money.getInt("money", 0) >= 720000) {
                    AlertDialog.Builder builder;
                    builder=new AlertDialog.Builder(MusicShopActivity.this);
                    builder.setTitle("Notice!")
                            .setMessage("Are u sure you want to buy this music?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", (dialog, which) -> {

                                buying_item.start();

                                Toast.makeText(MusicShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                edit.putInt("money", money.getInt("money", 0) - 720000);
                                edit.apply();
                                SharedPreferences.Editor editor = getSharedPreferences("music",MODE_PRIVATE).edit();
                                editor.putInt("purple",2);
                                editor.apply();
                                music_text3.setText(R.string.unselected_text);
                                music_text3.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                money_bar.setText(""+money.getInt("money", 0));
                            })
                            .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                            .show();
                }
            }
            else if(music.getInt("purple",1)==3){
                Toast.makeText(MusicShopActivity.this, "You already selected this music", Toast.LENGTH_SHORT).show();
            }
            else if(music.getInt("purple",1)==2){

                SharedPreferences.Editor edit = getSharedPreferences("music", MODE_PRIVATE).edit();

                if(music.getInt("default",3)==3){
                    edit.putInt("default",2);
                    edit.apply();
                    music_text1.setText(R.string.unselected_text);
                }
                else if(music.getInt("blue",1)==3){
                    edit.putInt("blue",2);
                    edit.apply();
                    music_text2.setText(R.string.unselected_text);
                }
                else if(music.getInt("orange",1)==3){
                    edit.putInt("orange",2);
                    edit.apply();
                    music_text4.setText(R.string.unselected_text);
                }
                edit.putInt("purple", 3);
                edit.apply();
                music_text3.setText(R.string.selected_text);
                Toast.makeText(MusicShopActivity.this, "Selected music", Toast.LENGTH_SHORT).show();
            }

        });

        music_text4.setOnClickListener(v -> {

            click.start();

            if(music.getInt("orange",1)==1) {
                if (money.getInt("money", 0) < 21134710) {
                    Toast.makeText(MusicShopActivity.this, "You dont have enough money to buy this music", Toast.LENGTH_SHORT).show();
                } else if (money.getInt("money", 0) >= 21134710) {
                    AlertDialog.Builder builder;
                    builder=new AlertDialog.Builder(MusicShopActivity.this);
                    builder.setTitle("Notice!")
                            .setMessage("Are u sure you want to buy this music?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", (dialog, which) -> {

                                buying_item.start();

                                Toast.makeText(MusicShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                edit.putInt("money", money.getInt("money", 0) - 21134710);
                                edit.apply();
                                SharedPreferences.Editor editor = getSharedPreferences("music",MODE_PRIVATE).edit();
                                editor.putInt("orange",2);
                                editor.apply();
                                music_text4.setText(R.string.unselected_text);
                                music_text4.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                money_bar.setText(""+money.getInt("money", 0));
                            })
                            .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                            .show();
                }
            }
            else if(music.getInt("orange",1)==3){
                Toast.makeText(MusicShopActivity.this, "You already selected this music", Toast.LENGTH_SHORT).show();
            }
            else if(music.getInt("orange",1)==2){

                SharedPreferences.Editor edit = getSharedPreferences("music", MODE_PRIVATE).edit();

                if(music.getInt("default",3)==3){
                    edit.putInt("default",2);
                    edit.apply();
                    music_text1.setText(R.string.unselected_text);
                }
                else if(music.getInt("purple",1)==3){
                    edit.putInt("purple",2);
                    edit.apply();
                    music_text3.setText(R.string.unselected_text);
                }
                else if(music.getInt("blue",1)==3){
                    edit.putInt("blue",2);
                    edit.apply();
                    music_text2.setText(R.string.unselected_text);
                }
                edit.putInt("orange", 3);
                edit.apply();
                music_text4.setText(R.string.selected_text);
                Toast.makeText(MusicShopActivity.this, "Selected music", Toast.LENGTH_SHORT).show();
            }

        });


    }
}