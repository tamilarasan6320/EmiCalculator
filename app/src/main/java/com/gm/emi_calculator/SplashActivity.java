package com.gm.emi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ImageView Logo;
    Handler handler;
    Animation animFadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        Logo = findViewById(R.id.loan);
        animFadeIn = AnimationUtils.loadAnimation(SplashActivity.this,
                R.anim.fade_in);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Logo.startAnimation(myFadeInAnimation);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },3000);

    }
}