package com.example.asus.the_flying_fish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread thread= new Thread()
        {
            @Override
           public void run()
            {
                try {
                    sleep(1500);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    Intent newIntent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(newIntent);
                    finish();
                }
            }
        };
        thread.start();
    }
    @Override
        protected void onPause()
        {
            super.onPause();
            finish();
        }
}
