package com.alphasoft.snakeanimationball;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {

    int measuredWidth = 0;
    int measuredHeight = 0;
    WindowManager w;

    MediaPlayer mPlayer,musPlayer;
    SharedPreferences pref;
    Editor editor;
    ImageButton playBtn, retryBtn;
    TextView scoreOnBoard, highscoreOnBoard, score;
    ImageView ball, gameOver, scoreBoard, logo,pillar1,
            pillar2,pillar3,pillar4,pillar5,pillar6,
            pillar7,pillar8,pillar9,pillar10,
            pillarTop1,pillarTop2,pillarTop3,
            pillarTop4,pillarTop5,pillarTop6;
    RelativeLayout layout;
    final Handler h = new Handler();
    Boolean gameOverCalled;
    Boolean ballRight;
    Integer scoreValue,highscoreValue;
    Rect ballRect = new Rect();
    Rect pillar1Rect = new Rect();
    Rect pillar2Rect = new Rect();
    Rect pillar3Rect = new Rect();
    Rect pillar4Rect = new Rect();
    Rect pillar5Rect = new Rect();
    Rect pillar6Rect = new Rect();
    Rect pillar7Rect = new Rect();
    Rect pillar8Rect = new Rect();
    Rect pillar9Rect = new Rect();
    Rect pillar10Rect = new Rect();
    Rect pillartop1Rect = new Rect();
    Rect pillartop2Rect = new Rect();
    Rect pillartop3Rect = new Rect();
    Rect pillartop4Rect = new Rect();
    Rect pillartop5Rect = new Rect();
    Rect pillartop6Rect = new Rect();
    TextView pixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
//highscore save
        pref = getApplicationContext().getSharedPreferences("com.alphasoft.snakeanimationball_prefences.xml",0);
//pixels pata karne
            w = getWindowManager();
            Point size = new Point();
            w.getDefaultDisplay().getSize(size);
            measuredWidth = size.x;
            measuredHeight = size.y;


                onCreateNew();
        musPlayer = MediaPlayer.create(GameActivity.this, R.raw.mus);
        mPlayer = MediaPlayer.create(GameActivity.this, R.raw.thug);
        musPlayer.start();
        musPlayer.setLooping(true);

        playBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCreateNew();
                        playBtnClicker();
                        movement();

                    }
                }
        );
        retryBtn.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(getApplicationContext(),"Snake Animation Ball is created by\nShahzain Ali(EP-1349095)\n" +
                                "Wasi Haider(EP-1349121)\nSyed Irtaza Zaidi(EP-1349103)\nAisha Ehsan(EP-1349008)" +
                                "\n\nComputer Graphics Project\nGiven by\nMiss Humera Bashir",Toast.LENGTH_LONG).show();;
                        return true;
                    }
                }
        );
    }

    protected void onCreateNew()
    {
        highscoreValue = pref.getInt("highscoresaved",0);
        layout =(RelativeLayout)findViewById(R.id.layout);
        playBtn = (ImageButton)findViewById(R.id.playBtn);
        retryBtn = (ImageButton)findViewById(R.id.retryBtn);
        scoreOnBoard = (TextView)findViewById(R.id.scoreOnBoard);
        highscoreOnBoard =(TextView)findViewById(R.id.highscoreOnBoard);
        score = (TextView)findViewById(R.id.score);
        ball = (ImageView)findViewById(R.id.ball);
        gameOver = (ImageView)findViewById(R.id.gameOver);
        scoreBoard=(ImageView)findViewById(R.id.scoreBoard);
        logo = (ImageView)findViewById(R.id.logo);
        pillar1 = (ImageView)findViewById(R.id.pillar1);
        pillar2 = (ImageView)findViewById(R.id.pillar2);
        pillar3 = (ImageView)findViewById(R.id.pillar3);
        pillar4 = (ImageView)findViewById(R.id.pillar4);
        pillar5 = (ImageView)findViewById(R.id.pillar5);
        pillar6 = (ImageView)findViewById(R.id.pillar6);
        pillar7 = (ImageView)findViewById(R.id.pillar7);
        pillar8 = (ImageView)findViewById(R.id.pillar8);
        pillar9 = (ImageView)findViewById(R.id.pillar9);
        pillar10 = (ImageView)findViewById(R.id.pillar10);
        pillarTop1 = (ImageView)findViewById(R.id.pillartop1);
        pillarTop2 = (ImageView)findViewById(R.id.pillartop2);
        pillarTop3 = (ImageView)findViewById(R.id.pillartop3);
        pillarTop4 = (ImageView)findViewById(R.id.pillartop4);
        pillarTop5 = (ImageView)findViewById(R.id.pillartop5);
        pillarTop6 = (ImageView)findViewById(R.id.pillartop6);

        gameOverCalled = false;
        ballRight = true;
        scoreValue=0;

        pillar1.setVisibility(View.INVISIBLE);
        pillar2.setVisibility(View.INVISIBLE);
        pillar3.setVisibility(View.INVISIBLE);
        pillar4.setVisibility(View.INVISIBLE);
        pillar5.setVisibility(View.INVISIBLE);
        pillar6.setVisibility(View.INVISIBLE);
        pillar7.setVisibility(View.INVISIBLE);
        pillar8.setVisibility(View.INVISIBLE);
        pillar9.setVisibility(View.INVISIBLE);
        pillar10.setVisibility(View.INVISIBLE);
        pillarTop1.setVisibility(View.INVISIBLE);
        pillarTop2.setVisibility(View.INVISIBLE);
        pillarTop3.setVisibility(View.INVISIBLE);
        pillarTop4.setVisibility(View.INVISIBLE);
        pillarTop5.setVisibility(View.INVISIBLE);
        pillarTop6.setVisibility(View.INVISIBLE);
        ball.setVisibility(View.INVISIBLE);
        gameOver.setVisibility(View.INVISIBLE);
        retryBtn.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        scoreBoard.setVisibility(View.INVISIBLE);
        scoreOnBoard.setVisibility(View.INVISIBLE);
        highscoreOnBoard.setVisibility(View.INVISIBLE);

    }

    protected void playBtnClicker()
    {
        logo.setVisibility(View.INVISIBLE);
        playBtn.setVisibility(View.INVISIBLE);

        float calcY= measuredHeight/1.8f;
        float calcX=measuredWidth/3.5f;

        pillar1.setX(calcX);
        pillar1.setY(calcY);
        ball.setX(calcX+20);
        ball.setY(calcY+50);

//        pillar1.setX(200);
//        pillar1.setY(768);
//        ball.setX(220);
//        ball.setY(820);

        pillar2.setX(pillar1.getX() + 78);
        pillar2.setY(pillar1.getY() - 55);
        pillar3.setX(pillar2.getX() + 78);
        pillar3.setY(pillar2.getY() - 55);
        pillarTop1.setX(pillar2.getX() + 10.5f);
        pillarTop1.setY(pillar2.getY() + 10.5f);
        pillarTop2.setX(pillar1.getX()+10.5f);
        pillarTop2.setY(pillar1.getY()+10.5f);
        pillar4.setX(pillarPlacementX(pillar3.getX()));
        pillar4.setY(pillarPlacementY(pillar3.getY()));
        pillar5.setX(pillarPlacementX(pillar4.getX()));
        pillar5.setY(pillarPlacementY(pillar4.getY()));
        pillar6.setX(pillarPlacementX(pillar5.getX()));
        pillar6.setY(pillarPlacementY(pillar5.getY()));
        pillar7.setX(pillarPlacementX(pillar6.getX()));
        pillar7.setY(pillarPlacementY(pillar6.getY()));
        pillar8.setX(pillarPlacementX(pillar7.getX()));
        pillar8.setY(pillarPlacementY(pillar7.getY()));
        pillar9.setX(pillarPlacementX(pillar8.getX()));
        pillar9.setY(pillarPlacementY(pillar8.getY()));
        pillar10.setX(pillarPlacementX(pillar9.getX()));
        pillar10.setY(pillarPlacementY(pillar9.getY()));



        pillar1.setVisibility(View.VISIBLE);
        pillar2.setVisibility(View.VISIBLE);
        pillar3.setVisibility(View.VISIBLE);
        pillar4.setVisibility(View.VISIBLE);
        pillar5.setVisibility(View.VISIBLE);
        pillar6.setVisibility(View.VISIBLE);
        pillar7.setVisibility(View.VISIBLE);
        pillar8.setVisibility(View.VISIBLE);
        pillar9.setVisibility(View.VISIBLE);
        pillar10.setVisibility(View.VISIBLE);
        pillarTop1.setVisibility(View.VISIBLE);
        pillarTop2.setVisibility(View.VISIBLE);
        pillarTop3.setVisibility(View.VISIBLE);
        pillarTop4.setVisibility(View.VISIBLE);
        pillarTop5.setVisibility(View.VISIBLE);
        pillarTop6.setVisibility(View.VISIBLE);

        ball.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
    }

    public void movement(){
        final int delay = 60;



        h.postDelayed(new Runnable(){
            public void run(){

                layout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {


                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            if (gameOverCalled == false) {
                                scoreValue++;
                                score.setText(String.valueOf(scoreValue));
                                if (ballRight == true) {
                                    ballRight = false;


                                } else {
                                    ballRight = true;
                                }
                            }

                            return true;
                        }
                        return false;
                    }
                });

                if(ballRight==true)
                {
                    ball.setX(ball.getX()+6.7f);
                    ball.setY(ball.getY()-0.5f);
                }
                else{
                    ball.setX(ball.getX()-6.7f);
                    ball.setY(ball.getY()-0.5f);
                }

                pillar1.setY(pillar1.getY()+5);
                pillar2.setY(pillar2.getY()+5);
                pillar3.setY(pillar3.getY()+5);
                pillar4.setY(pillar4.getY()+5);
                pillar5.setY(pillar5.getY()+5);
                pillar6.setY(pillar6.getY()+5);
                pillar7.setY(pillar7.getY()+5);
                pillar8.setY(pillar8.getY()+5);
                pillar9.setY(pillar9.getY()+5);
                pillar10.setY(pillar10.getY()+5);
                ball.setY(ball.getY() + 0.5f);

                ball.getHitRect(ballRect);
                pillar1.getHitRect(pillar1Rect);
                pillar2.getHitRect(pillar2Rect);
                pillar3.getHitRect(pillar3Rect);
                pillar4.getHitRect(pillar4Rect);
                pillar5.getHitRect(pillar5Rect);
                pillar6.getHitRect(pillar6Rect);
                pillar7.getHitRect(pillar7Rect);
                pillar8.getHitRect(pillar8Rect);
                pillar9.getHitRect(pillar9Rect);
                pillar10.getHitRect(pillar10Rect);
                pillarTop1.getHitRect(pillartop1Rect);
                pillarTop2.getHitRect(pillartop2Rect);
                pillarTop3.getHitRect(pillartop3Rect);
                pillarTop4.getHitRect(pillartop4Rect);
                pillarTop5.getHitRect(pillartop5Rect);
                pillarTop6.getHitRect(pillartop6Rect);


                if(checkPillarPosition(pillar1.getY())==true)
                {   sendViewToBack(pillar1);
                    pillar1.setX(pillarPlacementX(pillar10.getX()));
                    pillar1.setY(pillarPlacementY(pillar10.getY()));
                }
                else if(checkPillarPosition(pillar2.getY())==true)
                {   sendViewToBack(pillar2);
                    pillar2.setX(pillarPlacementX(pillar1.getX()));
                    pillar2.setY(pillarPlacementY(pillar1.getY()));
                }
                else if(checkPillarPosition(pillar3.getY())==true)
                {   sendViewToBack(pillar3);
                    pillar3.setX(pillarPlacementX(pillar2.getX()));
                    pillar3.setY(pillarPlacementY(pillar2.getY()));
                }
                else if(checkPillarPosition(pillar4.getY())==true)
                {   sendViewToBack(pillar4);
                    pillar4.setX(pillarPlacementX(pillar3.getX()));
                    pillar4.setY(pillarPlacementY(pillar3.getY()));
                }
                else if(checkPillarPosition(pillar5.getY())==true)
                {   sendViewToBack(pillar5);
                    pillar5.setX(pillarPlacementX(pillar4.getX()));
                    pillar5.setY(pillarPlacementY(pillar4.getY()));
                }
                else if(checkPillarPosition(pillar6.getY())==true)
                {   sendViewToBack(pillar6);
                    pillar6.setX(pillarPlacementX(pillar5.getX()));
                    pillar6.setY(pillarPlacementY(pillar5.getY()));
                }
                else if(checkPillarPosition(pillar7.getY())==true)
                {   sendViewToBack(pillar7);
                    pillar7.setX(pillarPlacementX(pillar6.getX()));
                    pillar7.setY(pillarPlacementY(pillar6.getY()));
                }
                else if(checkPillarPosition(pillar8.getY())==true)
                {   sendViewToBack(pillar8);
                    pillar8.setX(pillarPlacementX(pillar7.getX()));
                    pillar8.setY(pillarPlacementY(pillar7.getY()));
                }
                else if(checkPillarPosition(pillar9.getY())==true)
                {   sendViewToBack(pillar9);
                    pillar9.setX(pillarPlacementX(pillar8.getX()));
                    pillar9.setY(pillarPlacementY(pillar8.getY()));
                }
                else if(checkPillarPosition(pillar10.getY())==true)
                {   sendViewToBack(pillar10);
                    pillar10.setX(pillarPlacementX(pillar9.getX()));
                    pillar10.setY(pillarPlacementY(pillar9.getY()));
                }
                if(Rect.intersects(ballRect,pillartop1Rect)||
                        Rect.intersects(ballRect,pillartop2Rect)||
                        Rect.intersects(ballRect,pillartop3Rect)||
                        Rect.intersects(ballRect,pillartop4Rect)||
                        Rect.intersects(ballRect,pillartop5Rect)||
                        Rect.intersects(ballRect,pillartop6Rect))
                {

                }
                else
                {
                    gameOver();
                }
                if(Rect.intersects(ballRect,pillar1Rect)){
                    pillarTop4.setVisibility(View.VISIBLE);
                    pillarTop5.setVisibility(View.VISIBLE);
                    pillarTop6.setVisibility(View.VISIBLE);
                    pillarTop1.setX(pillar2.getX()+10.5f);
                    pillarTop1.setY(pillar2.getY()+10.5f);
                    pillarTop2.setX(pillar1.getX()+10.5f);
                    pillarTop2.setY(pillar1.getY()+10.5f);
                    pillarTop3.setX(pillar10.getX()+10.5f);
                    pillarTop3.setY(pillar10.getY()+10.5f);
                    pillarTop4.setX(pillar9.getX()+10.5f);
                    pillarTop4.setY(pillar9.getY()+10.5f);
                    pillarTop5.setX(pillar8.getX()+10.5f);
                    pillarTop5.setY(pillar8.getY()+10.5f);
                    pillarTop6.setX(pillar7.getX()+10.5f);
                    pillarTop6.setY(pillar7.getY()+10.5f);
                }
                else if(Rect.intersects(ballRect,pillar2Rect)){
                    pillarTop1.setX(pillar3.getX()+10.5f);
                    pillarTop1.setY(pillar3.getY()+10.5f);
                    pillarTop2.setX(pillar2.getX()+10.5f);
                    pillarTop2.setY(pillar2.getY()+10.5f);
                    pillarTop3.setX(pillar1.getX()+10.5f);
                    pillarTop3.setY(pillar1.getY()+10.5f);
                    pillarTop4.setX(pillar10.getX()+10.5f);
                    pillarTop4.setY(pillar10.getY()+10.5f);
                    pillarTop5.setX(pillar9.getX()+10.5f);
                    pillarTop5.setY(pillar9.getY()+10.5f);
                    pillarTop6.setX(pillar8.getX()+10.5f);
                    pillarTop6.setY(pillar8.getY()+10.5f);

                }
                else if(Rect.intersects(ballRect,pillar3Rect)){
                    pillarTop4.setVisibility(View.INVISIBLE);
                    pillarTop5.setVisibility(View.INVISIBLE);
                    pillarTop6.setVisibility(View.INVISIBLE);
                    pillarTop1.setX(pillar4.getX()+10.5f);
                    pillarTop1.setY(pillar4.getY()+10.5f);
                    pillarTop2.setX(pillar3.getX()+10.5f);
                    pillarTop2.setY(pillar3.getY()+10.5f);
                    pillarTop3.setX(pillar2.getX()+10.5f);
                    pillarTop3.setY(pillar2.getY()+10.5f);
                }
                else if(Rect.intersects(ballRect,pillar4Rect)){
                    pillarTop1.setX(pillar5.getX()+10.5f);
                    pillarTop1.setY(pillar5.getY()+10.5f);
                    pillarTop2.setX(pillar4.getX()+10.5f);
                    pillarTop2.setY(pillar4.getY()+10.5f);
                    pillarTop3.setX(pillar3.getX()+10.5f);
                    pillarTop3.setY(pillar3.getY()+10.5f);
                }
                else if(Rect.intersects(ballRect,pillar5Rect)){
                    pillarTop1.setX(pillar6.getX()+10.5f);
                    pillarTop1.setY(pillar6.getY()+10.5f);
                    pillarTop2.setX(pillar5.getX()+10.5f);
                    pillarTop2.setY(pillar5.getY()+10.5f);
                    pillarTop3.setX(pillar4.getX()+10.5f);
                    pillarTop3.setY(pillar4.getY()+10.5f);
                }
                else if(Rect.intersects(ballRect,pillar6Rect)){
                    pillarTop1.setX(pillar7.getX()+10.5f);
                    pillarTop1.setY(pillar7.getY()+10.5f);
                    pillarTop2.setX(pillar6.getX()+10.5f);
                    pillarTop2.setY(pillar6.getY()+10.5f);
                    pillarTop3.setX(pillar5.getX()+10.5f);
                    pillarTop3.setY(pillar5.getY()+10.5f);
                }
                else if(Rect.intersects(ballRect,pillar7Rect)){
                    pillarTop1.setX(pillar8.getX()+10.5f);
                    pillarTop1.setY(pillar8.getY()+10.5f);
                    pillarTop2.setX(pillar7.getX()+10.5f);
                    pillarTop2.setY(pillar7.getY()+10.5f);
                    pillarTop3.setX(pillar6.getX()+10.5f);
                    pillarTop3.setY(pillar6.getY()+10.5f);

                }
                else if(Rect.intersects(ballRect,pillar8Rect)){
                    pillarTop1.setX(pillar9.getX()+10.5f);
                    pillarTop1.setY(pillar9.getY()+10.5f);
                    pillarTop2.setX(pillar8.getX()+10.5f);
                    pillarTop2.setY(pillar8.getY()+10.5f);
                    pillarTop3.setX(pillar7.getX()+10.5f);
                    pillarTop3.setY(pillar7.getY()+10.5f);
                }
                else if(Rect.intersects(ballRect,pillar9Rect)){
                    pillarTop1.setX(pillar10.getX()+10.5f);
                    pillarTop1.setY(pillar10.getY()+10.5f);
                    pillarTop2.setX(pillar9.getX()+10.5f);
                    pillarTop2.setY(pillar9.getY()+10.5f);
                    pillarTop3.setX(pillar8.getX()+10.5f);
                    pillarTop3.setY(pillar8.getY()+10.5f);
                }
                else if(Rect.intersects(ballRect,pillar10Rect)){
                    pillarTop1.setX(pillar1.getX()+10.5f);
                    pillarTop1.setY(pillar1.getY()+10.5f);
                    pillarTop2.setX(pillar10.getX()+10.5f);
                    pillarTop2.setY(pillar10.getY()+10.5f);
                    pillarTop3.setX(pillar9.getX()+10.5f);
                    pillarTop3.setY(pillar9.getY()+10.5f);
                }


                h.postDelayed(this,delay);
            }
        },delay);
    }
    public void gameOver(){



       gameOverCalled = true;
        h.removeCallbacksAndMessages(null);

        sendViewToBack(pillar1);
        sendViewToBack(pillar2);
        sendViewToBack(pillar3);
        sendViewToBack(pillar4);
        sendViewToBack(pillar5);
        sendViewToBack(pillar6);
        sendViewToBack(pillar7);
        sendViewToBack(pillar8);
        sendViewToBack(pillar9);
        sendViewToBack(pillar10);

        pillar1.setVisibility(View.INVISIBLE);
        pillar2.setVisibility(View.INVISIBLE);
        pillar3.setVisibility(View.INVISIBLE);
        pillar4.setVisibility(View.INVISIBLE);
        pillar5.setVisibility(View.INVISIBLE);
        pillar6.setVisibility(View.INVISIBLE);
        pillar7.setVisibility(View.INVISIBLE);
        pillar8.setVisibility(View.INVISIBLE);
        pillar9.setVisibility(View.INVISIBLE);
        pillar10.setVisibility(View.INVISIBLE);
        pillarTop1.setVisibility(View.INVISIBLE);
        pillarTop2.setVisibility(View.INVISIBLE);
        pillarTop3.setVisibility(View.INVISIBLE);
        pillarTop4.setVisibility(View.INVISIBLE);
        pillarTop5.setVisibility(View.INVISIBLE);
        pillarTop6.setVisibility(View.INVISIBLE);
        ball.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);

        if(scoreValue>highscoreValue){
            highscoreValue=scoreValue;
            editor = pref.edit();
            editor.putInt("highscoresaved", highscoreValue);
            editor.commit();
            if(musPlayer.isPlaying()) {
                musPlayer.stop();
            }

            mPlayer.start();

        }

        scoreOnBoard.setText(String.valueOf(scoreValue));
        highscoreOnBoard.setText(String.valueOf(highscoreValue));
        gameOver.setVisibility(View.VISIBLE);
        retryBtn.setVisibility(View.VISIBLE);
        scoreBoard.setVisibility(View.VISIBLE);
        scoreOnBoard.setVisibility(View.VISIBLE);
        highscoreOnBoard.setVisibility(View.VISIBLE);

        retryBtn.setOnClickListener(new View.OnClickListener()

        {@Override
         public void onClick(View v) {

                onCreateNew();
                playBtnClicker();
                movement();
                if(!musPlayer.isPlaying()) {
                    musPlayer = MediaPlayer.create(GameActivity.this, R.raw.mus);
                    musPlayer.start();
                    musPlayer.setLooping(true);
                    musPlayer.stop();
                }
                if(mPlayer.isPlaying()) {
                    mPlayer.stop();
                }



            }

        });
    }
    public static void sendViewToBack(final View child)
    {
        final ViewGroup parent =(ViewGroup)child.getParent();
        if(null != parent)
        {
            parent.removeView(child);
            parent.addView(child,6);
        }
    }
    public boolean checkPillarPosition(float Y){
        boolean low = false;
        if(Y>1000f)
        {
            low=true;
        }
        return low;

    }

    public float pillarPlacementX(float X){
        float PillarNewX =0f;
        int random = (int)(Math.random()*2+1);

        if(random==1)
        {
            if(X>400){
                PillarNewX =X -79;
            }
            else {
                PillarNewX = X+78;
            }

        }
        else
        {if(X<80){
            PillarNewX =X +78;
        }
        else {
            PillarNewX = X-79;
        }}

        return PillarNewX;
    }
    public float pillarPlacementY(float Y) {
        Y = Y-57;
        return Y;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mPlayer.isPlaying())
            mPlayer.stop();
        if (musPlayer.isPlaying())
            musPlayer.stop();
    }


}
