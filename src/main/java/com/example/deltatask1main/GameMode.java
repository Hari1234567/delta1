package com.example.deltatask1main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class GameMode extends AppCompatActivity {
     boolean optionMode=false;
     boolean firstTime=true;
     public static int score,highscore;
    TextView userInputField;
    public  RotationSaver rotationSaver;
     FragmentTransaction fragmentTransaction;
     FragmentManager fragmentManager;
     OptionsPane optionsPane;
     View view;
     public TextView highScore;
     TextView currentScore;
     ProgressBar timerBar;
     CountDownTimer countDownTimer;
     Vibrator vibrator;
     CountDownTimer scoreBoardTimer;
     public static final String SHARED_PREFS="sharedPrefs";
     public static final String highScoreString="HIGH SCORE";
     public static final String highScoreStringHack="HIGH SCORE HACK";

    @Override
    protected void onStart() {
        super.onStart();
        if(rotationSaver.getTimerMode() && MainActivity.mode==3){
           countDownTimer=new CountDownTimer(rotationSaver.getTimeRemaining(),1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerBar.setProgress((int)millisUntilFinished);
                    rotationSaver.setTimeRemaining((int)millisUntilFinished);
                    //timerBar.getProgressDrawable().setColorFilter(gbr(255-(int)millisUntilFinished*255/10000,(int)(millisUntilFinished)*255/10000,0), android.graphics.PorterDuff.Mode.SRC_IN);

                }

                @Override
                public void onFinish() {
                    score=rotationSaver.getScore();
                    optionsPane.timeUp();
                    highscore=getHighScore();
                    rotationSaver.setTimerMode(false);
                    scoreBoard();
                }
            }.start();
        }
        if(MainActivity.mode==3&&rotationSaver.getScoreCountDown()){
            rotationSaver.setScoreCountDown(true);

            scoreBoardTimer=new CountDownTimer(rotationSaver.getScoreCountRemaining(),1) {

                @Override
                public void onTick(long millisUntilFinished) {
                    rotationSaver.setScoreCountRemaining((int)millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    Intent i = new Intent(GameMode.this, ScoreBoard.class);
                    startActivity(i);
                    GameMode.this.finish();
                    rotationSaver.setFirstTime(false);
                    rotationSaver.setScoreCountDown(false);
                }
            }.start();

        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        rotationSaver= new ViewModelProvider(GameMode.this).get(RotationSaver.class);
        setContentView(R.layout.activity_normal_mode);
        super.onCreate(savedInstanceState);
        timerBar=(ProgressBar)findViewById(R.id.timerBar);
        highScore=(TextView)findViewById(R.id.highScore);
        currentScore=(TextView)findViewById(R.id.currentScore);
        currentScore.setText(rotationSaver.getScoreText()+Integer.toString(rotationSaver.getScore()));
        highScore.setText(rotationSaver.getHighScoreText()+Integer.toString(getHighScore()));
        highScore.setTextColor(rotationSaver.getFontColor());
        currentScore.setTextColor(rotationSaver.getFontColor());
        //getResources().getColor(R.color.colorAccent)="#000000";
        if(MainActivity.mode==1){
                  highScore.setVisibility(View.INVISIBLE);
                  currentScore.setVisibility(View.INVISIBLE);
        }
        if(MainActivity.mode!=3){
            timerBar.setVisibility(View.INVISIBLE);
        }

        userInputField = (TextView) findViewById(R.id.userNumber);
        userInputField.setEnabled(!rotationSaver.getOptionMode());
        userInputField.setTextColor(rotationSaver.getFontColor());
        final Button but = (Button) findViewById(R.id.options);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
       setBackground(rotationSaver.getBackgroundColor());

        optionsPane = new OptionsPane();
        final Button proceedBut=(Button)findViewById(R.id.proceedButton);

        findViewById(R.id.optionPlaceHolder).setVisibility(View.INVISIBLE);
        fragmentTransaction.replace(R.id.optionPlaceHolder, optionsPane, "LaunchOption").commit();

        proceedBut.setText(rotationSaver.getProceedButText());
        if(rotationSaver.getOptionMode()) {
            findViewById(R.id.optionPlaceHolder).setVisibility(View.VISIBLE);
            but.setVisibility(View.INVISIBLE);
            proceedBut.setClickable(false);
            rotationSaver.setOptionsClickable(true);
        }


        proceedBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                but.setVisibility(View.VISIBLE);
                highScore.setText(rotationSaver.getHighScoreText()+Integer.toString(getHighScore()));
                userInputField.setText("");
                optionsPane.reset();
                findViewById(R.id.optionPlaceHolder).setBackgroundColor(Color.WHITE);
                rotationSaver.setBackgroundColor(Color.WHITE);

                findViewById(R.id.optionPlaceHolder).setVisibility(View.INVISIBLE);
                rotationSaver.setOptionMode(false);
                userInputField.setEnabled(true);
                proceedBut.setText(R.string.enter_a_number_to_proceed);
                rotationSaver.setProceedButText("Enter a number to proceed");
                proceedBut.setClickable(false);
                rotationSaver.setOptionsClickable(false);

            }
        });
        proceedBut.setClickable((!rotationSaver.getOptionsClickable())||(!rotationSaver.getScoreCountDown()));


            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
               try{
                    if (isValidNum("" + userInputField.getText())) {

                        rotationSaver.setOptionMode(true);
                        userInputField.setEnabled(false);
                        findViewById(R.id.optionPlaceHolder).setVisibility(View.VISIBLE);
                        TextView userInputField = (TextView) findViewById(R.id.userNumber);

                        int userNumber = 20;
                        try {
                            userNumber = Integer.parseInt("" + userInputField.getText());
                        } catch (NullPointerException n) {
                            userNumber = 200;
                        }
                        ArrayList<Integer> factors = new ArrayList<Integer>();

                        for (int i = 2; i <= userNumber / 2; i++) {
                            if (userNumber % i == 0) {
                                factors.add(i);
                            }
                        }
                        if (factors.size() == 0) {
                            factors.add(1);
                        }

                        optionsPane.optionManager(factors, userNumber);
                        ArrayList<Integer> options = new ArrayList<Integer>();

                        rotationSaver.setOption(1,optionsPane.getOptions(0));
                        rotationSaver.setOption(2,optionsPane.getOptions(1));
                        rotationSaver.setOption(3,optionsPane.getOptions(2));

                        but.setVisibility(View.INVISIBLE);
                        proceedBut.setText(R.string.FactorFindPromt);
                        if(MainActivity.mode==3) {
                            rotationSaver.setTimerMode(true);
                            rotationSaver.setTimeRemaining( 10000);
                            countDownTimer = new CountDownTimer(10000, 1) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    timerBar.setProgress((int) millisUntilFinished);
                                    rotationSaver.setTimeRemaining ((int) millisUntilFinished);
                                    //timerBar.getProgressDrawable().setColorFilter(gbr(255-(int)millisUntilFinished*255/10000,(int)(millisUntilFinished)*255/10000,0), android.graphics.PorterDuff.Mode.SRC_IN);
                                }

                                @Override
                                public void onFinish() {
                                    score=rotationSaver.getScore();
                                    optionsPane.timeUp();
                                    highscore=getHighScore();
                                    rotationSaver.setTimerMode(false);
                                    scoreBoard();



                                }
                            };
                            countDownTimer.start();
                        }
                        rotationSaver.setProceedButText("FIND THE FACTOR!");
                    }else{
                        Toast.makeText(getApplicationContext(),"Enter a valid number above 10!",Toast.LENGTH_LONG).show();
                    }}catch(NumberFormatException n){
                   Toast.makeText(getApplicationContext(),"Do not enter game breaking numbers!!",Toast.LENGTH_LONG).show();
               }


                }


            });

        Button menuButton=(Button)findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                GameMode.this.finish();

            }
        });



    }


    public void setBackground(int color){
        findViewById(R.id.gameLayout).setBackgroundColor(color);
        if(color==getResources().getColor(R.color.darkGreen)||color==getResources().getColor(R.color.darkRed)){
            rotationSaver.setFontColor(Color.YELLOW);
            userInputField.setTextColor(Color.YELLOW);
            if(MainActivity.mode!=1) {
                highScore.setTextColor(Color.YELLOW);
                currentScore.setTextColor(Color.YELLOW);
            }
        }else{
            rotationSaver.setFontColor(Color.BLACK);
            userInputField.setTextColor(Color.BLACK);
            if(MainActivity.mode!=1)
                highScore.setTextColor(Color.BLACK);
                currentScore.setTextColor(Color.BLACK);
        }
    }
    public void saveData(int score){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(MainActivity.mode==2)
        editor.putInt(highScoreString,score);
        else if(MainActivity.mode==3){
            editor.putInt(highScoreStringHack,score);
        }
        editor.commit();
    }
    public int getHighScore(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        if(MainActivity.mode==2) {
            return sharedPreferences.getInt(highScoreString, 0);
        }else{
            return sharedPreferences.getInt(highScoreStringHack, 0);
        }
        }

    public void setHighScore(int score){
        highScore.setText(rotationSaver.getHighScoreText()+Integer.toString(score));
        saveData(score);
    }
    boolean isValidNum(String num){

        if(num==""||Integer.parseInt(num)<10)
            return false;
        return true;
    }
    public void stopCountdown(){
        countDownTimer.cancel();
        rotationSaver.setTimeRemaining(10000);
        timerBar.setProgress(0);
        rotationSaver.setTimerMode(false);
    }
    public void vibrate(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(MainActivity.mode==3&& rotationSaver.getTimerMode()) {
            countDownTimer.cancel();

        }
        if(MainActivity.mode==3 && rotationSaver.getScoreCountDown()){
            scoreBoardTimer.cancel();
        }

    }

    public void scoreBoard(){
        if(MainActivity.mode==3&&rotationSaver.getFirstTime()) {
            rotationSaver.setScoreCountDown(true);

            scoreBoardTimer=new CountDownTimer(2000,1) {

                @Override
                public void onTick(long millisUntilFinished) {
                     rotationSaver.setScoreCountRemaining((int)millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    Intent i = new Intent(GameMode.this, ScoreBoard.class);
                    startActivity(i);
                    GameMode.this.finish();
                    rotationSaver.setFirstTime(false);
                    rotationSaver.setScoreCountDown(false);
                }
            }.start();

            }
    }



}