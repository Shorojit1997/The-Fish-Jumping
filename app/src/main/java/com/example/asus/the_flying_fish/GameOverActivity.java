package com.example.asus.the_flying_fish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
TextView text3,text2,txt ;
ImageButton button,home;

protected int final_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        txt= findViewById(R.id.text1);
        text2= findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        home =findViewById(R.id.home);
        Intent intent = getIntent();
        String result = intent.getStringExtra("MY_KEY");
        int n = Integer.valueOf(result);

        button= (ImageButton) findViewById(R.id.button);
        int score = prefs.getInt("key", 0);
        if(n>score) {
            text2.setText("High Score : " + (String.valueOf(n)));
            text3.setText("Score : "+result);
            editor.putInt("key",n);
        }
        else {
            text2.setText("High Score : " + (String.valueOf(score)));
            text3.setText("Score : " + result);
        }
        editor.commit();
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent newIntent = new Intent(GameOverActivity.this,MainActivity.class);
               startActivity(newIntent);
               finish();
           }
       });
       home.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent newIntent = new Intent(GameOverActivity.this,MainActivity.class);
               startActivity(newIntent);
               finish();
           }
       });
        blinkTextView();
    }
    private void blinkTextView() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 800;
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(button.getVisibility() == View.VISIBLE) {
                            button.setVisibility(View.INVISIBLE);
                        }
                        else {
                            button.setVisibility(View.VISIBLE);
                        }
                        blinkTextView();
                    }
                });
            }
        }).start();
    }
}
