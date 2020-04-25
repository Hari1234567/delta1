package com.example.deltatask1main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OptionsPane#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OptionsPane extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int correctOption;
    ArrayList<Integer> options;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    int op1,op2,op3;
    TextView scoreText;

    public OptionsPane() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OptionsPane.
     */
    // TODO: Rename and change types and number of parameters
    ArrayList<Button> buttons;
    ProgressBar progressBar;
    GameMode gameActivty;
    Button proceedBut;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_options_pane, container, false);
        buttons = new ArrayList<>();
        buttons = new ArrayList<Button>();
        buttons.add((Button) view.findViewById(R.id.option1));
        buttons.add((Button) view.findViewById(R.id.option2));
        buttons.add((Button) view.findViewById(R.id.option3));
        gameActivty=(GameMode)getActivity();
        progressBar=(ProgressBar)getActivity().findViewById(R.id.timerBar);
     view.setBackgroundColor(gameActivty.rotationSaver.getBackgroundColor());
        if(gameActivty.rotationSaver.getOption(1)!=0){
            buttons.get(0).setText(Integer.toString(gameActivty.rotationSaver.getOption(1)));
            buttons.get(1).setText(Integer.toString(gameActivty.rotationSaver.getOption(2)));
            buttons.get(2).setText(Integer.toString(gameActivty.rotationSaver.getOption(3)));
        }
        correctOption=gameActivty.rotationSaver.getCorrectOptionID();
        proceedBut=(Button)getActivity().findViewById(R.id.proceedButton);
         scoreText=(TextView)getActivity().findViewById(R.id.currentScore);
        proceedBut.setClickable(!gameActivty.rotationSaver.getOptionsClickable());
            buttons.get(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.setBackgroundColor(getResources().getColor(R.color.darkGreen));
                    gameActivty.rotationSaver.setBackgroundColor(getResources().getColor(R.color.darkGreen));
                    gameActivty.setBackground(getResources().getColor(R.color.darkGreen));
                        buttons.get(correctOption).setBackgroundColor(Color.GREEN);
                    gameActivty.rotationSaver.setOptionColors(correctOption,Color.GREEN);
                            proceedBut.setText(R.string.CorrectAnswerFeedback);
                            gameActivty.rotationSaver.setProceedButText("CORRECT! Press to continue");
                            gameActivty.rotationSaver.setScore(gameActivty.rotationSaver.getScore()+1);
                          scoreText.setText(gameActivty.rotationSaver.getScoreText()+Integer.toString(gameActivty.rotationSaver.getScore()));
                        if(correctOption!=0){

                            gameActivty.vibrate();

                            view.setBackgroundColor(getResources().getColor(R.color.darkRed));
                            gameActivty.rotationSaver.setBackgroundColor(getResources().getColor(R.color.darkRed));
                            gameActivty.setBackground(getResources().getColor(R.color.darkRed));
                            buttons.get(0).setBackgroundColor(Color.RED);
                            gameActivty.rotationSaver.setOptionColors(0,Color.RED);
                            gameActivty.scoreBoard();
                            proceedBut.setClickable(false);


                            if(MainActivity.mode!=3) {
                                proceedBut.setText(R.string.wrongAnswerFeedback);
                                gameActivty.rotationSaver.setProceedButText("INCORRECT, KEEP TRYING!");
                            }else{
                                proceedBut.setText("INCORRECT, Loading Scoreboard");
                                gameActivty.rotationSaver.setProceedButText("INCORRECT, Loading Scoreboard");
                            }

                            GameMode.score=gameActivty.rotationSaver.getScore();
                            GameMode.highscore=gameActivty.getHighScore();
                            gameActivty.rotationSaver.setScore(0);
                            scoreText.setText(gameActivty.rotationSaver.getScoreText()+Integer.toString(0));


                        }
                    if(gameActivty.rotationSaver.getScore()>gameActivty.getHighScore() && MainActivity.mode!=1){
                        gameActivty.setHighScore(gameActivty.rotationSaver.getScore());



                    }

                     for(int i=0;i<3;i++){
                         buttons.get(i).setClickable(false);

                     }
                     gameActivty.rotationSaver.setOptionsClickable(false);
                    if(MainActivity.mode!=3 || gameActivty.rotationSaver.getBackgroundColor()==getResources().getColor(R.color.darkGreen))
                     proceedBut.setClickable(true);
                    if(MainActivity.mode==3) {
                        gameActivty.rotationSaver.setTimerMode(false);
                        gameActivty.stopCountdown();
                    }

                }
            });
        buttons.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttons.get(correctOption).setBackgroundColor(Color.GREEN);
                gameActivty.rotationSaver.setOptionColors(correctOption,Color.GREEN);
                view.setBackgroundColor(getResources().getColor(R.color.darkGreen));
                gameActivty.rotationSaver.setBackgroundColor(getResources().getColor(R.color.darkGreen));
                gameActivty.setBackground(getResources().getColor(R.color.darkGreen));
                proceedBut.setText(R.string.CorrectAnswerFeedback);
                gameActivty.rotationSaver.setProceedButText("CORRECT! Press to continue");
                gameActivty.rotationSaver.setScore(gameActivty.rotationSaver.getScore()+1);
                scoreText.setText(gameActivty.rotationSaver.getScoreText()+Integer.toString(gameActivty.rotationSaver.getScore()));
                if(correctOption!=1){
                    gameActivty.vibrate();
                    view.setBackgroundColor(getResources().getColor(R.color.darkRed));
                    gameActivty.rotationSaver.setBackgroundColor(getResources().getColor(R.color.darkRed));
                    gameActivty.setBackground(getResources().getColor(R.color.darkRed));
                    buttons.get(1).setBackgroundColor(Color.RED);
                    gameActivty.rotationSaver.setOptionColors(1,Color.RED);
                    gameActivty.scoreBoard();
                    proceedBut.setClickable(false);

                    if(MainActivity.mode!=3) {
                        proceedBut.setText(R.string.wrongAnswerFeedback);
                        gameActivty.rotationSaver.setProceedButText("INCORRECT, KEEP TRYING!");
                    }else{
                        proceedBut.setText("INCORRECT, Loading Scoreboard");
                        gameActivty.rotationSaver.setProceedButText("INCORRECT, Loading Scoreboard");
                    }

                    GameMode.score=gameActivty.rotationSaver.getScore();
                    GameMode.highscore=gameActivty.getHighScore();
                    gameActivty.rotationSaver.setScore(0);
                    scoreText.setText(gameActivty.rotationSaver.getScoreText()+Integer.toString(0));


                }
                if(gameActivty.rotationSaver.getScore()>gameActivty.getHighScore()&&MainActivity.mode!=1){
                    gameActivty.setHighScore(gameActivty.rotationSaver.getScore());


                }

                for(int i=0;i<3;i++){
                    buttons.get(i).setClickable(false);
                }
                if(MainActivity.mode!=3 || gameActivty.rotationSaver.getBackgroundColor()==getResources().getColor(R.color.darkGreen))
                proceedBut.setClickable(true);
                gameActivty.rotationSaver.setOptionsClickable(false);
                if(MainActivity.mode==3) {
                    gameActivty.rotationSaver.setTimerMode(false);
                    gameActivty.stopCountdown();

                }

            }
        });
        buttons.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttons.get(correctOption).setBackgroundColor(Color.GREEN);
                gameActivty.rotationSaver.setOptionColors(correctOption,Color.GREEN);
                proceedBut.setText(R.string.CorrectAnswerFeedback);
                gameActivty.rotationSaver.setProceedButText("CORRECT! Press to continue");
                gameActivty.rotationSaver.setScore(gameActivty.rotationSaver.getScore()+1);
                scoreText.setText(gameActivty.rotationSaver.getScoreText()+Integer.toString(gameActivty.rotationSaver.getScore()));
                view.setBackgroundColor(getResources().getColor(R.color.darkGreen));
                gameActivty.rotationSaver.setBackgroundColor(getResources().getColor(R.color.darkGreen));
                gameActivty.setBackground(getResources().getColor(R.color.darkGreen));
                if(correctOption!=2){
                    gameActivty.vibrate();
                    buttons.get(2).setBackgroundColor(Color.RED);
                    gameActivty.rotationSaver.setOptionColors(2,Color.RED);
                    gameActivty.scoreBoard();
                    proceedBut.setClickable(false);




                    if(MainActivity.mode!=3) {
                        proceedBut.setText(R.string.wrongAnswerFeedback);
                        gameActivty.rotationSaver.setProceedButText("INCORRECT, KEEP TRYING!");
                    }else{
                        proceedBut.setText("INCORRECT, Loading Scoreboard");
                        gameActivty.rotationSaver.setProceedButText("INCORRECT, Loading Scoreboard");
                    }
                    view.setBackgroundColor(getResources().getColor(R.color.darkRed));
                    gameActivty.rotationSaver.setBackgroundColor(getResources().getColor(R.color.darkRed));
                    gameActivty.setBackground(getResources().getColor(R.color.darkRed));

                    GameMode.score=gameActivty.rotationSaver.getScore();
                    GameMode.highscore=gameActivty.getHighScore();
                    gameActivty.rotationSaver.setScore(0);
                    scoreText.setText(gameActivty.rotationSaver.getScoreText()+Integer.toString(0));


                }
                if(gameActivty.rotationSaver.getScore()>gameActivty.getHighScore()&&MainActivity.mode!=1){
                    gameActivty.setHighScore(gameActivty.rotationSaver.getScore());



                }
                if(MainActivity.mode==3) {
                    gameActivty.rotationSaver.setTimerMode(false);
                    gameActivty.stopCountdown();
                }
                for(int i=0;i<3;i++){
                    buttons.get(i).setClickable(false);
                }
                if(MainActivity.mode!=3 || gameActivty.rotationSaver.getBackgroundColor()==getResources().getColor(R.color.darkGreen))
                proceedBut.setClickable(true);
                gameActivty.rotationSaver.setOptionsClickable(false);
            }
        });

        for(int i=0;i<3;i++){
            buttons.get(i).setBackgroundColor(gameActivty.rotationSaver.getOptionColors(i));

            buttons.get(i).setClickable(gameActivty.rotationSaver.getOptionsClickable());

        }
        view.setBackgroundColor(gameActivty.rotationSaver.getBackgroundColor());
        if(gameActivty.rotationSaver.getOptionColors(correctOption)==Color.GREEN){
            for(int i=0;i<3;i++){
                buttons.get(i).setClickable(false);
            }
            proceedBut.setClickable(true);
        }

        return view;
    }


    public void optionManager(ArrayList<Integer> factors,int userNumber) {
        Random rand=new Random();
        int correctIndex=rand.nextInt(factors.size());
        correctOption=rand.nextInt(3);
        gameActivty.rotationSaver.setCorrectOptionID(correctOption);
        int max=factors.get(factors.size()-1);
        options=new ArrayList<Integer>();

        for(int i=0;i<3;i++){
            int num=0;
            boolean scan=true;
            while(scan){
                num=1+ rand.nextInt(userNumber);
                if(userNumber%num!=0){
                    boolean flag=false;
                    for(int j=0;j<i;j++){
                        if(num==options.get(j)){
                            flag=true;
                            break;
                        }
                    }
                    scan=flag;
                }
            }
            options.add(num);

            buttons.get(i).setText(Integer.toString(num));
            buttons.get(i).setClickable(true);
        }
        buttons.get(correctOption).setText(Integer.toString(factors.get(correctIndex)));


    }

    public void reset(){
        view.setBackgroundColor(Color.WHITE);
        gameActivty.setBackground(Color.WHITE);
        gameActivty.rotationSaver.setBackgroundColor(Color.WHITE);
        for(int i=0;i<3;i++){
            buttons.get(i).setBackgroundColor(Color.LTGRAY);
            gameActivty.rotationSaver.setOptionColors(i,Color.LTGRAY);
        }


    }
   public int getOptions(int id){
        return Integer.parseInt(""+buttons.get(id).getText());
   }


   public void timeUp(){
        gameActivty.vibrate();
        GameMode.score+=1;
        if(gameActivty.rotationSaver.getScore()>gameActivty.getHighScore()&&MainActivity.mode!=1){

            gameActivty.setHighScore(gameActivty.rotationSaver.getScore());

            Toast.makeText(gameActivty.getApplicationContext(),"New High Score!",Toast.LENGTH_LONG).show();


       }


       gameActivty.rotationSaver.setScore(0);
       scoreText.setText(gameActivty.rotationSaver.getScoreText()+Integer.toString(0));
        for(int i=0;i<3;i++){
            buttons.get(i).setClickable(false);
        }

        proceedBut.setClickable(false);

       proceedBut.setText("TIME UP! Loading Scoreboard!");
       gameActivty.rotationSaver.setProceedButText("TIME UP! Loading Scoreboard");
       buttons.get(correctOption).setBackgroundColor(Color.GREEN);
       gameActivty.rotationSaver.setOptionColors(correctOption,Color.GREEN);




   }


}
