package com.example.asus.the_flying_fish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.GetChars;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private  FlyingFishView gameVIew;
    private Handler handler = new Handler();
    private TextView h_txt;
  private ImageButton ply,high,exit,home;

private  final  static  long InterVal = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
     gameVIew=new FlyingFishView(this);
     ply=(ImageButton)findViewById(R.id.ply);
     high=(ImageButton)findViewById(R.id.score);
     exit=(ImageButton)findViewById(R.id.exit);

     final int score = prefs.getInt("key", 0);
     ply.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             setContentView(gameVIew);

             Timer timer= new Timer();
             timer.schedule(new TimerTask() {
                 @Override
                 public void run() {
                     handler.post(new Runnable() {
                         @Override
                         public void run() {
                             gameVIew.invalidate();
                         }
                     });
                 }
             },0,InterVal);
         }
     });
     high.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             setContentView(R.layout.h_score);
             h_txt=(TextView)findViewById(R.id.h_txt);
             home=findViewById(R.id.home);
             h_txt.setText("High Score : "+String.valueOf(score));
             home.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent newIntent = new Intent(MainActivity.this,MainActivity.class);
                     startActivity(newIntent);
                     finish();
                 }
             });
         }
     });
     exit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             onBackPressed();
         }
     });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in=new Intent(this,MainActivity.class);

    }
}
