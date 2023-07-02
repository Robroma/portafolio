package com.example.androidfirebase.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidfirebase.R;

public class SplashActivity extends AppCompatActivity {
    private final int DURACION_SPLASH_ROTATION = 3500;
    private final int DURACION_SPLASH_APARICIO = 2000;

    private ImageView imageView;
    ObjectAnimator objectAnimator;
    ObjectAnimator obAnTranslationX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setTranslationX(-2000f);
        obAnTranslationX = ObjectAnimator.ofFloat(imageView, "translationX",0f);
        obAnTranslationX.setDuration(DURACION_SPLASH_APARICIO);

        objectAnimator = ObjectAnimator.ofFloat(imageView, "Rotation", 0f,720f);
        objectAnimator.setDuration(DURACION_SPLASH_ROTATION);
        objectAnimator.setStartDelay(DURACION_SPLASH_APARICIO);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator,obAnTranslationX);
        animatorSet.start();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH_ROTATION+DURACION_SPLASH_APARICIO);
    }
}