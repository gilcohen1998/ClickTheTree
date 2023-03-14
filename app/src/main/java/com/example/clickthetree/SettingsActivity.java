package com.example.clickthetree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

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

      //  overridePendingTransition(0, 0);
     //   this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
          this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        click= new MediaPlayer();
        click= MediaPlayer.create(getApplicationContext(),R.raw.clicking_sound);

    }
}