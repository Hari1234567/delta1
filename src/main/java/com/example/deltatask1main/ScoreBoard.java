package com.example.deltatask1main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        TextView scoreText=(TextView)findViewById(R.id.score);
        TextView highScoreText=(TextView)findViewById(R.id.highScore);
        if(GameMode.score-1!=GameMode.highscore){
            findViewById(R.id.hghScoreMessage).setVisibility(View.INVISIBLE);
        }

        scoreText.setText("Score: "+Integer.toString(GameMode.score-1));
        highScoreText.setText("High Score: "+Integer.toString(GameMode.highscore));

    }
}
