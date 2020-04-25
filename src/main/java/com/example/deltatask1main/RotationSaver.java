package com.example.deltatask1main;

import android.graphics.Color;

import androidx.lifecycle.ViewModel;

public class RotationSaver extends ViewModel {
    private boolean optionMode=false, optionsClickable =true;
    private int option1=0,option2=0,option3=0,correctOptionID=0;
    private String proceedButText="Enter a number to proceed";
    private int backgroundColor=Color.WHITE;
    private int fontColor=Color.BLACK;
    private int score=0;
    private String highScoreText="HIGH SCORE: ";
    private String scoreText="SCORE: ";
    private boolean timerMode=false;
    private int timeRemaining;
    private boolean firstTime=true;
    private int[] optionColors= {Color.LTGRAY,Color.LTGRAY,Color.LTGRAY};
    private boolean scoreCountdown=false;
    private int scoreCountRemaining=3000;
    boolean getOptionMode(){
        return optionMode;
    }
    void setOptionMode(boolean opt){
        optionMode=opt;
    }
    boolean getOptionsClickable(){return optionsClickable;}
    void setOptionsClickable(boolean clickable){optionsClickable=clickable;}
    int getOption(int id){
        switch(id){
            case 1:
                return option1;
            case 2:
                return option2;
            default:
                return option3;
        }
    }
    void setOption(int id, int val){
        switch(id){
            case 1:
                option1=val;break;
            case 2:
                option2=val;break;
            default:
                option3=val;break;
        }
    }
    int getCorrectOptionID(){
        return correctOptionID;
    }
    void setCorrectOptionID(int id){
        correctOptionID=id;
    }
    String getProceedButText(){
        return proceedButText;
    }
    void setProceedButText(String text){
        proceedButText=text;
    }
    int getBackgroundColor(){return backgroundColor;}
    void setBackgroundColor(int color){backgroundColor=color;}
    int getFontColor(){return fontColor;}
    void setFontColor(int color){fontColor=color;}
    int getScore(){return score;}
    void setScore(int _score){score=_score;}
    String getHighScoreText(){return highScoreText;}
    void setHighScoreText(String score){highScoreText=score;}
    String getScoreText(){return scoreText;}
    void setScoreText(String score){scoreText=score;}
    boolean getTimerMode(){return timerMode;}
    void setTimerMode(boolean _timerMode){timerMode=_timerMode;}
    int getTimeRemaining(){return timeRemaining;}
    void setTimeRemaining(int time){timeRemaining=time;}
    boolean getFirstTime(){return firstTime;}
    void setFirstTime(boolean first){firstTime=first;}
    int getOptionColors(int id){return optionColors[id];}
    void setOptionColors(int id, int val){optionColors[id]=val;}
    boolean getScoreCountDown(){return scoreCountdown;}
    void setScoreCountDown(boolean countdown){scoreCountdown=true;}
    int getScoreCountRemaining(){return scoreCountRemaining;}
    void setScoreCountRemaining(int scoreTime){scoreCountRemaining=scoreTime;}

}
