package br.com.grupouninter.ap.jogodamemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_splash_screen);

        Handler handle = new Handler ();
        handle.postDelayed (new Runnable () {
            @Override
            public void run() {
                chamaMenu();


            }
        }, 5000);
    }

    public void chamaMenu(){
        Intent objIntent = new Intent (SplashScreenActivity.this, MainActivity.class);
        startActivity(objIntent);
        finish ();


    }



}
