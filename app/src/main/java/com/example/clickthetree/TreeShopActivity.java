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

public class TreeShopActivity extends AppCompatActivity {
    private SharedPreferences money;
    private TextView money_bar;
    private TextView default_tree_text;
    private TextView green_tree_text;
    private TextView blue_tree_text;
    private TextView yellow_tree_text;
    private TextView orange_tree_text;
    private TextView red_tree_text;
    private TextView cyan_tree_text;
    private TextView purple_tree_text;
    private ImageView back_button;
    private ImageView blue_tree_image;
    private ImageView green_tree_image;
    private ImageView yellow_tree_image;
    private ImageView red_tree_image;
    private ImageView cyan_tree_image;
    private ImageView orange_tree_image;
    private ImageView purple_tree_image;
    private Animation scale_down;
    private MediaPlayer click;
    private MediaPlayer buying_item;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(buying_item!=null) {
            buying_item.stop();
            buying_item.release();
            buying_item=null;
        }

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
        setContentView(R.layout.tree_shop_activity);

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


        money_bar=findViewById(R.id.money_bar_shop);
        default_tree_text=findViewById(R.id.frame_text1);
        blue_tree_text=findViewById(R.id.frame_text2);
        cyan_tree_text=findViewById(R.id.frame_text3);
        purple_tree_text=findViewById(R.id.frame_text4);
        green_tree_text=findViewById(R.id.frame_text5);
        orange_tree_text=findViewById(R.id.frame_text6);
        red_tree_text=findViewById(R.id.frame_text7);
        yellow_tree_text=findViewById(R.id.frame_text8);
        back_button=findViewById(R.id.back_treeshop_button);
        blue_tree_image=findViewById(R.id.blue_tree_image);
        green_tree_image=findViewById(R.id.green_tree_image);
        yellow_tree_image=findViewById(R.id.yellow_tree_image);
        red_tree_image=findViewById(R.id.red_tree_image);
        purple_tree_image=findViewById(R.id.purple_tree_image);
        cyan_tree_image=findViewById(R.id.cyan_tree_image);
        orange_tree_image=findViewById(R.id.orange_tree_image);
        scale_down= AnimationUtils.loadAnimation(this,R.anim.scale_down);

        back_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                click.start();

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    back_button.startAnimation(scale_down);
                }
                Intent intent = new Intent(TreeShopActivity.this, MainShopsActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

        // 1 is unsold, 2 is sold but owned, 3 is selected

         money = getSharedPreferences("tree",MODE_PRIVATE);
        money_bar.setText(""+money.getInt("money",0));
        money.getInt("default",3);
        money.getInt("blue",1);
        money.getInt("green",1);
        money.getInt("cyan",1);
        money.getInt("red",1);
        money.getInt("yellow",1);
        money.getInt("orange",1);
        money.getInt("purple",1);


        // Default tree text shop

        if(money.getInt("default",3)==2){
            default_tree_text.setText("Owned");
        }
        else if(money.getInt("default",3)==3){
            default_tree_text.setText("Selected");
        }

        // Blue tree text shop

        if(money.getInt("blue",1)==1){
            blue_tree_text.setText("3,000");
        }
        else if(money.getInt("blue",1)==2){
            blue_tree_text.setText("Owned");
            blue_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            blue_tree_image.setImageDrawable(getDrawable(R.drawable.blue_tree_frame));
        }
        else if(money.getInt("blue",1)==3){
            blue_tree_text.setText("Selected");
            blue_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            blue_tree_image.setImageDrawable(getDrawable(R.drawable.blue_tree_frame));
        }

        // Cyan tree text shop

        if(money.getInt("cyan",1)==1){
            cyan_tree_text.setText("16,000");
        }
        else if(money.getInt("cyan",1)==2){
            cyan_tree_text.setText("Owned");
            cyan_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            cyan_tree_image.setImageDrawable(getDrawable(R.drawable.cyan_tree_frame));
        }
        else if(money.getInt("cyan",1)==3){
            cyan_tree_text.setText("Selected");
            cyan_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            cyan_tree_image.setImageDrawable(getDrawable(R.drawable.cyan_tree_frame));
        }

        // Purple tree text shop

        if(money.getInt("purple",1)==1){
            purple_tree_text.setText("70,000");
        }
        else if(money.getInt("purple",1)==2){
            purple_tree_text.setText("Owned");
            purple_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            purple_tree_image.setImageDrawable(getDrawable(R.drawable.purple_tree_frame));
        }
        else if(money.getInt("purple",1)==3){
            purple_tree_text.setText("Selected");
            purple_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            purple_tree_image.setImageDrawable(getDrawable(R.drawable.purple_tree_frame));
        }

        // Green tree text shop

        if(money.getInt("green",1)==1){
            green_tree_text.setText("320,000");
        }
        else if(money.getInt("green",1)==2){
            green_tree_text.setText("Owned");
            green_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            green_tree_image.setImageDrawable(getDrawable(R.drawable.green_tree_frame));
        }
        else if(money.getInt("green",1)==3){
            green_tree_text.setText("Selected");
            green_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            green_tree_image.setImageDrawable(getDrawable(R.drawable.green_tree_frame));
        }

        // Orange tree text shop

        if(money.getInt("orange",1)==1){
            orange_tree_text.setText("750,000");
        }
        else if(money.getInt("orange",1)==2){
            orange_tree_text.setText("Owned");
            orange_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            orange_tree_image.setImageDrawable(getDrawable(R.drawable.orange_tree_frame));
        }
        else if(money.getInt("orange",1)==3){
            orange_tree_text.setText("Selected");
            orange_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            orange_tree_image.setImageDrawable(getDrawable(R.drawable.orange_tree_frame));
        }

        // Red tree text shop

        if(money.getInt("red",1)==1){
            red_tree_text.setText("2,110,000");
        }
        else if(money.getInt("red",1)==2){
            red_tree_text.setText("Owned");
            red_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            red_tree_image.setImageDrawable(getDrawable(R.drawable.red_tree_frame));
        }
        else if(money.getInt("red",1)==3){
            red_tree_text.setText("Selected");
            red_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            red_tree_image.setImageDrawable(getDrawable(R.drawable.red_tree_frame));
        }

        // Yellow tree text shop

        if(money.getInt("yellow",1)==1){
            yellow_tree_text.setText("51,610,170");
        }
        else if(money.getInt("yellow",1)==2){
            yellow_tree_text.setText("Owned");
            yellow_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            yellow_tree_image.setImageDrawable(getDrawable(R.drawable.yellow_tree_frame));
        }
        else if(money.getInt("yellow",1)==3){
            yellow_tree_text.setText("Selected");
            yellow_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            yellow_tree_image.setImageDrawable(getDrawable(R.drawable.yellow_tree_frame));
        }


        blue_tree_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();

                if(money.getInt("blue",1)==1) {
                    if (money.getInt("money", 0) < 3000) {
                        Toast.makeText(TreeShopActivity.this, "You dont have enough money to buy this tree", Toast.LENGTH_SHORT).show();
                    } else if (money.getInt("money", 0) >= 3000) {

                        AlertDialog.Builder builder;
                        builder=new AlertDialog.Builder(TreeShopActivity.this);
                        builder.setTitle("Notice!")
                                .setMessage("Are u sure you want to buy this tree?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        buying_item.start();

                                        Toast.makeText(TreeShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                        edit.putInt("money", money.getInt("money", 0) - 3000);
                                        edit.apply();
                                        edit.putInt("blue", 3);
                                        edit.apply();
                                        edit.putInt("default", 2);
                                        edit.apply();
                                        blue_tree_text.setText("Selected");
                                        blue_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                        blue_tree_image.setImageDrawable(getDrawable(R.drawable.blue_tree_frame));
                                        default_tree_text.setText("Owned");
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
                else if (money.getInt("blue",1)==3){
                    Toast.makeText(TreeShopActivity.this, "You already selected this tree", Toast.LENGTH_SHORT).show();
                }
            }
        });

        red_tree_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();

                if(money.getInt("red",1)==1&&money.getInt("orange",1)==3) {
                    if (money.getInt("money", 0) < 2110000) {
                        Toast.makeText(TreeShopActivity.this, "You dont have enough money to buy this tree", Toast.LENGTH_SHORT).show();
                    } else if (money.getInt("money", 0) >= 2110000) {
                        AlertDialog.Builder builder;
                        builder=new AlertDialog.Builder(TreeShopActivity.this);
                        builder.setTitle("Notice!")
                                .setMessage("Are u sure you want to buy this tree?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        buying_item.start();

                                        Toast.makeText(TreeShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                        edit.putInt("money", money.getInt("money", 0) - 2110000);
                                        edit.apply();
                                        edit.putInt("red", 3);
                                        edit.apply();
                                        edit.putInt("orange",2);
                                        edit.apply();
                                        red_tree_text.setText("Selected");
                                        red_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                        red_tree_image.setImageDrawable(getDrawable(R.drawable.red_tree_frame));
                                        orange_tree_text.setText("Owned");
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
                else if(money.getInt("red",1)==3){
                    Toast.makeText(TreeShopActivity.this, "You already selected this tree", Toast.LENGTH_SHORT).show();
                }
                else if(money.getInt("red",1)==1){
                    Toast.makeText(TreeShopActivity.this, "You need to buy the previous tree first in order to buy this tree!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        purple_tree_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();

                if(money.getInt("purple",1)==1&&money.getInt("cyan",1)==3) {
                    if (money.getInt("money", 0) < 70000) {
                        Toast.makeText(TreeShopActivity.this, "You dont have enough money to buy this tree", Toast.LENGTH_SHORT).show();
                    } else if (money.getInt("money", 0) >= 70000) {
                        AlertDialog.Builder builder;
                        builder=new AlertDialog.Builder(TreeShopActivity.this);
                        builder.setTitle("Notice!")
                                .setMessage("Are u sure you want to buy this tree?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        buying_item.start();

                                        Toast.makeText(TreeShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                        edit.putInt("money", money.getInt("money", 0) - 70000);
                                        edit.apply();
                                        edit.putInt("purple", 3);
                                        edit.apply();
                                        edit.putInt("cyan",2);
                                        edit.apply();
                                        purple_tree_text.setText("Selected");
                                        purple_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                        purple_tree_image.setImageDrawable(getDrawable(R.drawable.purple_tree_frame));
                                        cyan_tree_text.setText("Owned");
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
                else if(money.getInt("purple",1)==3){
                    Toast.makeText(TreeShopActivity.this, "You already selected this tree", Toast.LENGTH_SHORT).show();
                }
                else if(money.getInt("purple",1)==1){
                    Toast.makeText(TreeShopActivity.this, "You need to buy the previous tree first in order to buy this tree!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        green_tree_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();

                if(money.getInt("green",1)==1&&money.getInt("purple",1)==3) {
                    if (money.getInt("money", 0) < 320000) {
                        Toast.makeText(TreeShopActivity.this, "You dont have enough money to buy this tree", Toast.LENGTH_SHORT).show();
                    } else if (money.getInt("money", 0) >= 320000) {
                        AlertDialog.Builder builder;
                        builder=new AlertDialog.Builder(TreeShopActivity.this);
                        builder.setTitle("Notice!")
                                .setMessage("Are u sure you want to buy this tree?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        buying_item.start();

                                        Toast.makeText(TreeShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                        edit.putInt("money", money.getInt("money", 0) - 320000);
                                        edit.apply();
                                        edit.putInt("green", 3);
                                        edit.apply();
                                        edit.putInt("purple",2);
                                        edit.apply();
                                        green_tree_text.setText("Selected");
                                        green_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                        green_tree_image.setImageDrawable(getDrawable(R.drawable.green_tree_frame));
                                        purple_tree_text.setText("Owned");
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
                else if(money.getInt("green",1)==3){
                    Toast.makeText(TreeShopActivity.this, "You already selected this tree", Toast.LENGTH_SHORT).show();
                }
                else if(money.getInt("green",1)==1){
                    Toast.makeText(TreeShopActivity.this, "You need to buy the previous tree first in order to buy this tree!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        cyan_tree_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();

                if(money.getInt("cyan",1)==1&&money.getInt("blue",1)==3) {
                    if (money.getInt("money", 0) < 16000) {
                        Toast.makeText(TreeShopActivity.this, "You dont have enough money to buy this tree", Toast.LENGTH_SHORT).show();
                    } else if (money.getInt("money", 0) >= 16000) {
                        AlertDialog.Builder builder;
                        builder=new AlertDialog.Builder(TreeShopActivity.this);
                        builder.setTitle("Notice!")
                                .setMessage("Are u sure you want to buy this tree?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        buying_item.start();

                                        Toast.makeText(TreeShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                        edit.putInt("money", money.getInt("money", 0) - 16000);
                                        edit.apply();
                                        edit.putInt("cyan", 3);
                                        edit.apply();
                                        edit.putInt("blue",2);
                                        edit.apply();
                                        cyan_tree_text.setText("Selected");
                                        cyan_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                        cyan_tree_image.setImageDrawable(getDrawable(R.drawable.cyan_tree_frame));
                                        blue_tree_text.setText("Owned");
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
                else if(money.getInt("cyan",1)==3){
                    Toast.makeText(TreeShopActivity.this, "You already selected this tree", Toast.LENGTH_SHORT).show();
                }
                else if(money.getInt("cyan",1)==1){
                    Toast.makeText(TreeShopActivity.this, "You need to buy the previous tree first in order to buy this tree!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        orange_tree_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();

                if(money.getInt("orange",1)==1&&money.getInt("green",1)==3) {
                    if (money.getInt("money", 0) < 750000) {
                        Toast.makeText(TreeShopActivity.this, "You dont have enough money to buy this tree", Toast.LENGTH_SHORT).show();
                    } else if (money.getInt("money", 0) >= 750000) {
                        AlertDialog.Builder builder;
                        builder=new AlertDialog.Builder(TreeShopActivity.this);
                        builder.setTitle("Notice!")
                                .setMessage("Are u sure you want to buy this tree?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        buying_item.start();

                                        Toast.makeText(TreeShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                        edit.putInt("money", money.getInt("money", 0) - 750000);
                                        edit.apply();
                                        edit.putInt("orange", 3);
                                        edit.apply();
                                        edit.putInt("green",2);
                                        edit.apply();
                                        orange_tree_text.setText("Selected");
                                        orange_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                        orange_tree_image.setImageDrawable(getDrawable(R.drawable.orange_tree_frame));
                                        green_tree_text.setText("Owned");
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
                else if(money.getInt("orange",1)==3){
                    Toast.makeText(TreeShopActivity.this, "You already selected this tree", Toast.LENGTH_SHORT).show();
                }
                else if(money.getInt("orange",1)==1){
                    Toast.makeText(TreeShopActivity.this, "You need to buy the previous tree first in order to buy this tree!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        yellow_tree_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();

                if(money.getInt("yellow",1)==1&&money.getInt("red",1)==3) {
                    if (money.getInt("money", 0) < 51610170) {
                        Toast.makeText(TreeShopActivity.this, "You dont have enough money to buy this tree", Toast.LENGTH_SHORT).show();
                    } else if (money.getInt("money", 0) >= 51610170) {
                        AlertDialog.Builder builder;
                        builder=new AlertDialog.Builder(TreeShopActivity.this);
                        builder.setTitle("Notice!")
                                .setMessage("Are u sure you want to buy this tree?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        buying_item.start();

                                        Toast.makeText(TreeShopActivity.this, "Sold!", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor edit = getSharedPreferences("tree", MODE_PRIVATE).edit();
                                        edit.putInt("money", money.getInt("money", 0) - 51610170);
                                        edit.apply();
                                        edit.putInt("yellow", 3);
                                        edit.apply();
                                        edit.putInt("red",2);
                                        edit.apply();
                                        yellow_tree_text.setText("Selected");
                                        yellow_tree_text.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                                        yellow_tree_image.setImageDrawable(getDrawable(R.drawable.yellow_tree_frame));
                                        red_tree_text.setText("Owned");
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
                else if(money.getInt("yellow",1)==3){
                    Toast.makeText(TreeShopActivity.this, "You already selected this tree", Toast.LENGTH_SHORT).show();
                }
                else if(money.getInt("yellow",1)==1){
                    Toast.makeText(TreeShopActivity.this, "You need to buy the previous tree first in order to buy this tree!", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

}
