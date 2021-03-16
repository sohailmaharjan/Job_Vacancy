 package com.job.jobvacancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

 public class SplashScreen extends AppCompatActivity {
private ImageView img_splashscreen_main_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        img_splashscreen_main_logo= findViewById(R.id.img_splashscreen_main_logo);
        img_splashscreen_main_logo.setAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.fade_animation));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }


}