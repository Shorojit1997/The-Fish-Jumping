package com.example.asus.the_flying_fish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ASUS on 12/16/2019.
 */


public class FlyingFishView extends View{
    private Bitmap fish[]=new Bitmap[2];
    private Bitmap h_fish;
    private  int h_fishx,h_fishy,h_fishSpeed=10;
    private  Paint h_fishPing = new Paint();

    private Bitmap ball1;
    private Bitmap ball2;
    private Bitmap ball3;
    private Bitmap ball4;
    private  int fishX=70;
    public  int cnt=0;
    private  int fishY;
    private int fishSpeed;
    private  int canvesWidth,canvasHeight;
    private  int yellowX,yellowY,yellowSpeed=20;
    private  Paint yellowPaint = new Paint();
    private  int greenX,greenY,greenSpeed=20;
    private  Paint greenPaint = new Paint();
    private  int redX,redY,redSpeed=15;
    private  Paint redPaint = new Paint();


    private  int score,lifeCounterofFish;

    private  boolean touch =false;
    private  Bitmap backGroundImage,backim;
    int imx=0,imy=0,speed=5;
    private  Paint back_img = new Paint();

    private Paint scorePaing = new Paint();
    private  Bitmap life[]= new Bitmap[2];
    public FlyingFishView(Context context) {
        super(context);
        fish[0]= BitmapFactory.decodeResource(getResources(),R.mipmap.fish1);
        fish[1]= BitmapFactory.decodeResource(getResources(),R.mipmap.fish2);
        h_fish= BitmapFactory.decodeResource(getResources(),R.mipmap.hungry);
        ball1= BitmapFactory.decodeResource(getResources(),R.mipmap.ball);
        ball2= BitmapFactory.decodeResource(getResources(),R.mipmap.ball1);
        ball3= BitmapFactory.decodeResource(getResources(),R.mipmap.ball2);
        ball4= BitmapFactory.decodeResource(getResources(),R.mipmap.ball3);

        backGroundImage = BitmapFactory.decodeResource(getResources(),R.mipmap.background1);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        scorePaing.setColor(Color.WHITE);
        scorePaing.setTextSize(70);
        scorePaing.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaing.setAntiAlias(true);
        life[0]=BitmapFactory.decodeResource(getResources(),R.mipmap.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.mipmap.heart_grey);
        fishY =550;
        score=0;
        lifeCounterofFish=3;
    }
    @Override
    protected  void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvesWidth=canvas.getWidth();
        canvasHeight=canvas.getHeight();
        int minfishY=fish[0].getHeight()-100;
        int maxfishY=canvasHeight-fish[0].getHeight();
        imx-=(2+score/100);


        int wide=backGroundImage.getWidth()-canvesWidth;

        if(imx>=-(wide+canvesWidth))
           canvas.drawBitmap(backGroundImage,imx,imy,null);
        if((imx)<-(wide))
        {
            canvas.drawBitmap(backGroundImage,imx+wide-1+canvesWidth,imy,null);
        }
        if(imx<-2*wide)
            imx=0;


        fishY=fishY+fishSpeed;
        if(fishY<minfishY)
        {
            fishY=minfishY;

        }
        if(fishY>maxfishY)
        {
            fishY=maxfishY;
        }
        fishSpeed =fishSpeed+2;
        if(touch)
        {
            canvas.drawBitmap(fish[1],fishX,fishY,null);
            touch=false;
        }
        else
        {
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }


        yellowX-=yellowSpeed;
        if(hitBallCheaker(yellowX,yellowY))
        {
            score +=10;
            yellowX=-100;

        }

        if(yellowX<0)
        {
            yellowX=canvesWidth+21;
            yellowY= (int) Math.floor(Math.random()*(maxfishY-minfishY)+minfishY);
        }
        canvas.drawCircle(yellowX,yellowY,25,yellowPaint);

        greenX-=(greenSpeed+(score/30));
        if(hitBallCheaker(greenX,greenY))
        {
            score +=20;
            int rand=(int)Math.random()%80;
            greenX=-rand;

        }

        if(greenX<0)
        {
            greenX=canvesWidth+21;

            greenY= (int) Math.floor(Math.random()*(maxfishY-minfishY)+minfishY);
        }
        int rand=(int)Math.random()%100;
        canvas.drawCircle(greenX,greenY,25,greenPaint);

        redX-=(redSpeed+(score/15));
        if(hitBallCheaker(redX,redY))
        {

            redX=-100;
            lifeCounterofFish--;
            if(lifeCounterofFish==0)
            {   String s = Integer.toString(score);
                Toast.makeText(getContext(),"Game Over",Toast.LENGTH_SHORT).show();
                Intent gameOverIntent = new Intent(getContext(),GameOverActivity.class);
                gameOverIntent.putExtra("MY_KEY",s);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                getContext().startActivity(gameOverIntent);
            }

        }
        h_fishx-=(h_fishSpeed+(score/30));
        if(hitBallCheaker(h_fishx,h_fishy))
        {
            if(lifeCounterofFish<3)
            lifeCounterofFish++;

            h_fishx=-100;
        }

        if(h_fishx<0)
        {
            h_fishx=0;
           h_fishx=canvesWidth+3000;

            h_fishy= (int) Math.floor(Math.random()*(maxfishY-minfishY)+minfishY);
        }

        if(lifeCounterofFish%1==0)
           canvas.drawBitmap(h_fish,h_fishx,h_fishy,null);

        if(redX<0)
        {
            redX=canvesWidth+42;
            redY= (int) Math.floor(Math.random()*(maxfishY-minfishY)+minfishY);
        }
        if(cnt<4){
            canvas.drawBitmap(ball1,redX,redY,null);
            cnt++;
        }
        else if(cnt<=8)
        {
            canvas.drawBitmap(ball2,redX,redY,null);
            cnt++;
        }
        else if(cnt<=12)
        {
            canvas.drawBitmap(ball3,redX,redY,null);
            cnt++;
        }
        else if(cnt<=16)
        {
            canvas.drawBitmap(ball4,redX,redY,null);
            cnt++;
        }
        else
            cnt=0;


      //  canvas.drawCircle(redX,redY,35,redPaint);
        canvas.drawText("Score : "+score,20,60,scorePaing);
        for(int i=0;i<=3;i++)
        {
            int x = (int) (650 + life[0].getWidth() * 1.5 * i);

            int y = 30;
            if (i < lifeCounterofFish)
            {
                canvas.drawBitmap(life[0], x, y, null);
            }
            else
            {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }
    }
    public  boolean hitBallCheaker(int x,int y)
    {
        if(fishX<x && x<(fishX+fish[0].getWidth())&& fishY<y && y<fishY+fish[0].getHeight())
        {
            return  true;
        }
        return  false;
    }
    @Override
    public  boolean onTouchEvent(MotionEvent event)
    {
      if(event.getAction() == MotionEvent.ACTION_DOWN)
       {
        touch = true;
        fishSpeed=-25;
        }
        return true;
    }
}
