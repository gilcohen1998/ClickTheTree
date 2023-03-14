package com.example.clickthetree;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BackgroundShopActivity extends AppCompatActivity {

    private ImageView back_button;
    private Animation scale_down;
    private TextView money_bar;
    private TextView background_text1;
    private TextView background_text2;
    private TextView background_text3;
    private TextView background_text4;
    private TextView background_text5;
    private SharedPreferences background;
    private SharedPreferences money;
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
    protected void onResume() {
        super.onResume();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_shop_activity);

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
        ;

        back_button=findViewById(R.id.back_background_shop_button);
        scale_down= AnimationUtils.loadAnimation(this,R.anim.scale_down);
        money_bar=findViewById(R.id.money_bar_background_shop);
        background_text1= findViewById(R.id.frame_text1);
        background_text2= findViewById(R.id.frame_text2);
        background_text3= findViewById(R.id.frame_text3);
        background_text4= findViewById(R.id.frame_text4);
        background_text5= findViewById(R.id.frame_text5);



        back_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    back_button.startAnimation(scale_down);
                    click.start();
                }
                Intent intent = new Intent(BackgroundShopActivity.this, MainShopsActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

         money = getSharedPreferences("tree",MODE_PRIVATE);
        money_bar.setText(""+money.getInt("money",0));

        // 1 is unsold, 2 is unselected, 3 is selected

        background= getSharedPreferences("background",MODE_PRIVATE);
        background.getInt("default",3);
        background.getInt("desert",1);
        background.getInt("desert2",1);
        background.getInt("rainbow",1);
        background.getInt("farm_nature",1);


        // Default background text shop

        if(background.getInt("default",3)==2){
            background_text1.setText("Unselected");
        }
        else if(background.getInt("default",3)==3){
            background_text1.setText("Selected");
        }


        // Desert background text shop

        if(background.getInt("desert",1)==1){
            background_text2.setText("100,000");
        }
        else if(background.getInt("desert",1)==2){
            background_text2.setText("Unselected");
            background_text2.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }
        else if(background.getInt("desert",1)==3){
            background_text2.setText("Selected");
            background_text2.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }

        // Desert2 background text shop

        if(background.getInt("desert2",1)==1){
            background_text3.setText("308,000");
        }
        else if(background.getInt("desert2",1)==2){
            background_text3.setText("Unselected");
            background_text3.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }
        else if(background.getInt("desert2",1)==3){
            background_text3.setText("Selected");
            background_text3.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }


        // FarmNature background text shop

        if(background.getInt("farm_nature",1)==1){
            background_text4.setText("1,012,000");
        }
        else if(background.getInt("farm_nature",1)==2){
            background_text4.setText("Unselected");
            background_text4.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }
        else if(background.getInt("farm_nature",1)==3){
            background_text4.setText("Selected");
            background_text4.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }

        // Rainbow background text shop

        if(background.getInt("rainbow",1)==1){
            background_text5.setText("31,313,000");
        }
        else if(background.getInt("rainbow",1)==2){
            background_text5.setText("Unselected");
            background_text5.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }
        else if(background.getInt("rainbow",1)==3){
            background_text5.setText("Selected");
            background_text5.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }


        background_text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();

                SharedPreferences.Editor edit = getSharedPreferences("background", MODE_PRIVATE).edit();

                if(background.getInt("default",3)==2){

                    if(background.getInt("desert",1)==3){
                        edit.putInt("desert",2);
                        edit.apply();
                        background_text2.setText("Unselected");
                    }
                    else if(background.getInt("desert2",1)==3){
                        edit.putInt("desert2",2);
                        edit.apply();
                        background_text3.setText("Unselected");
                    }
                    else if(background.getInt("rainbow",1)==3){
                        edit.putInt("rainbow",2);
                        edit.apply();
                        background_text5.setText("Unselected");
                    }
                    else if(background.getInt("farm_nature",1)==3){
                        edit.putInt("farm_nature",2);
                        edit.apply();
                        background_text4.setText("Unselected");
                    }
                    edit.putInt("default", 3);
                    edit.apply();
                    background_text1.setText("Selected");
                    Toast.makeText(BackgroundShopActivity.this, "Selected background", Toast.LENGTH_SHORT).show();
                }
                else if(background.getInt("default",3)==3){
                    Toast.makeText(BackgroundShopActivity.this, "You already selected this background", Toast.LENGTH_SHORT).show();
                }
            }
        });

        background_text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();

                if(background.getInt("desert",1)==1) {
                    if (money.getInt("money", 0) < 100000) {
                        Toast.makeText(BackgroundShopActivity.this, "You dont have enough money to buy this background", Toast.LENGTH_SHORT).show();
                    } else if (money.getInt("money", 0) >= 100000) {
                        AlertDialog.Builder builder;
                        builder=new AlertDialog.Builder(BackgroundShopActivity.this);
                        builder.setTitle("Notice!")
                                .setMessage("Are u sure you want to buy this background?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        buying_item.start();

                                        Toast.makeText(BackgroundShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                        edit.putInt("money", money.getInt("money", 0) - 100000);
                                        edit.apply();
                                        SharedPreferences.Editor editor = getSharedPreferences("background",MODE_PRIVATE).edit();
                                        editor.putInt("desert",2);
                                        editor.apply();
                                        background_text2.setText("Unselected");
                                        background_text2.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                        money_bar.setText(""+money.getInt("money", 0));
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .show();
                    }
                }
                else if(background.getInt("desert",1)==3){
                    Toast.makeText(BackgroundShopActivity.this, "You already selected this background", Toast.LENGTH_SHORT).show();
                }
                else if(background.getInt("desert",1)==2){

                    SharedPreferences.Editor edit = getSharedPreferences("background", MODE_PRIVATE).edit();

                    if(background.getInt("default",3)==3){
                        edit.putInt("default",2);
                        edit.apply();
                        background_text1.setText("Unselected");
                    }
                    else if(background.getInt("desert2",1)==3){
                        edit.putInt("desert2",2);
                        edit.apply();
                        background_text3.setText("Unselected");
                    }
                    else if(background.getInt("rainbow",1)==3){
                        edit.putInt("rainbow",2);
                        edit.apply();
                        background_text5.setText("Unselected");
                    }
                    else if(background.getInt("farm_nature",1)==3){
                        edit.putInt("farm_nature",2);
                        edit.apply();
                        background_text4.setText("Unselected");
                    }
                    edit.putInt("desert", 3);
                    edit.apply();
                    background_text2.setText("Selected");
                    Toast.makeText(BackgroundShopActivity.this, "Selected background", Toast.LENGTH_SHORT).show();
                }

            }
        });


        background_text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();

                if(background.getInt("desert2",1)==1) {
                    if (money.getInt("money", 0) < 308000) {
                        Toast.makeText(BackgroundShopActivity.this, "You dont have enough money to buy this background", Toast.LENGTH_SHORT).show();
                    } else if (money.getInt("money", 0) >= 308000) {
                        AlertDialog.Builder builder;
                        builder=new AlertDialog.Builder(BackgroundShopActivity.this);
                        builder.setTitle("Notice!")
                                .setMessage("Are u sure you want to buy this background?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        buying_item.start();

                                        Toast.makeText(BackgroundShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                        edit.putInt("money", money.getInt("money", 0) - 308000);
                                        edit.apply();
                                        SharedPreferences.Editor editor = getSharedPreferences("background",MODE_PRIVATE).edit();
                                        editor.putInt("desert2",2);
                                        editor.apply();
                                        background_text3.setText("Unselected");
                                        background_text3.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                        money_bar.setText(""+money.getInt("money", 0));
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .show();
                    }
                }
                else if(background.getInt("desert2",1)==3){
                    Toast.makeText(BackgroundShopActivity.this, "You already selected this background", Toast.LENGTH_SHORT).show();
                }
                else if(background.getInt("desert2",1)==2){

                    SharedPreferences.Editor edit = getSharedPreferences("background", MODE_PRIVATE).edit();

                    if(background.getInt("default",3)==3){
                        edit.putInt("default",2);
                        edit.apply();
                        background_text1.setText("Unselected");
                    }
                    else if(background.getInt("desert",1)==3){
                        edit.putInt("desert",2);
                        edit.apply();
                        background_text2.setText("Unselected");
                    }
                    else if(background.getInt("rainbow",1)==3){
                        edit.putInt("rainbow",2);
                        edit.apply();
                        background_text5.setText("Unselected");
                    }
                    else if(background.getInt("farm_nature",1)==3){
                        edit.putInt("farm_nature",2);
                        edit.apply();
                        background_text4.setText("Unselected");
                    }
                    edit.putInt("desert2", 3);
                    edit.apply();
                    background_text3.setText("Selected");
                    Toast.makeText(BackgroundShopActivity.this, "Selected background", Toast.LENGTH_SHORT).show();
                }

            }
        });


        background_text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();

                if(background.getInt("farm_nature",1)==1) {
                    if (money.getInt("money", 0) < 1012000) {
                        Toast.makeText(BackgroundShopActivity.this, "You dont have enough money to buy this background", Toast.LENGTH_SHORT).show();
                    } else if (money.getInt("money", 0) >= 1012000) {
                        AlertDialog.Builder builder;
                        builder=new AlertDialog.Builder(BackgroundShopActivity.this);
                        builder.setTitle("Notice!")
                                .setMessage("Are u sure you want to buy this background?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        buying_item.start();

                                        Toast.makeText(BackgroundShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                        edit.putInt("money", money.getInt("money", 0) - 1012000);
                                        edit.apply();
                                        SharedPreferences.Editor editor = getSharedPreferences("background",MODE_PRIVATE).edit();
                                        editor.putInt("farm_nature",2);
                                        editor.apply();
                                        background_text4.setText("Unselected");
                                        background_text4.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                        money_bar.setText(""+money.getInt("money", 0));
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .show();
                    }
                }
                else if(background.getInt("farm_nature",1)==3){
                    Toast.makeText(BackgroundShopActivity.this, "You already selected this background", Toast.LENGTH_SHORT).show();
                }
                else if(background.getInt("farm_nature",1)==2){

                    SharedPreferences.Editor edit = getSharedPreferences("background", MODE_PRIVATE).edit();

                    if(background.getInt("default",3)==3){
                        edit.putInt("default",2);
                        edit.apply();
                        background_text1.setText("Unselected");
                    }
                    else if(background.getInt("desert2",1)==3){
                        edit.putInt("desert2",2);
                        edit.apply();
                        background_text3.setText("Unselected");
                    }
                    else if(background.getInt("rainbow",1)==3){
                        edit.putInt("rainbow",2);
                        edit.apply();
                        background_text5.setText("Unselected");
                    }
                    else if(background.getInt("desert",1)==3){
                        edit.putInt("desert",2);
                        edit.apply();
                        background_text2.setText("Unselected");
                    }
                    edit.putInt("farm_nature", 3);
                    edit.apply();
                    background_text4.setText("Selected");
                    Toast.makeText(BackgroundShopActivity.this, "Selected background", Toast.LENGTH_SHORT).show();
                }

            }
        });

        background_text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();

                if(background.getInt("rainbow",1)==1) {
                    if (money.getInt("money", 0) < 31313000) {
                        Toast.makeText(BackgroundShopActivity.this, "You dont have enough money to buy this background", Toast.LENGTH_SHORT).show();
                    } else if (money.getInt("money", 0) >= 31313000) {
                        AlertDialog.Builder builder;
                        builder=new AlertDialog.Builder(BackgroundShopActivity.this);
                        builder.setTitle("Notice!")
                                .setMessage("Are u sure you want to buy this background?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        buying_item.start();

                                        Toast.makeText(BackgroundShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                        edit.putInt("money", money.getInt("money", 0) - 31313000);
                                        edit.apply();
                                        SharedPreferences.Editor editor = getSharedPreferences("background",MODE_PRIVATE).edit();
                                        editor.putInt("rainbow",2);
                                        editor.apply();
                                        background_text5.setText("Unselected");
                                        background_text5.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                        money_bar.setText(""+money.getInt("money", 0));
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .show();
                    }
                }
                else if(background.getInt("rainbow",1)==3){
                    Toast.makeText(BackgroundShopActivity.this, "You already selected this background", Toast.LENGTH_SHORT).show();
                }
                else if(background.getInt("rainbow",1)==2){

                    SharedPreferences.Editor edit = getSharedPreferences("background", MODE_PRIVATE).edit();

                    if(background.getInt("default",3)==3){
                        edit.putInt("default",2);
                        edit.apply();
                        background_text1.setText("Unselected");
                    }
                    else if(background.getInt("desert2",1)==3){
                        edit.putInt("desert2",2);
                        edit.apply();
                        background_text3.setText("Unselected");
                    }
                    else if(background.getInt("farm_nature",1)==3){
                        edit.putInt("farm_nature",2);
                        edit.apply();
                        background_text4.setText("Unselected");
                    }
                    else if(background.getInt("desert",1)==3){
                        edit.putInt("desert",2);
                        edit.apply();
                        background_text2.setText("Unselected");
                    }
                    edit.putInt("rainbow", 3);
                    edit.apply();
                    background_text5.setText("Selected");
                    Toast.makeText(BackgroundShopActivity.this, "Selected background", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}