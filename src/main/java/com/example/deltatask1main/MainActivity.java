package com.example.deltatask1main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    public static int mode=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button normalBut=(Button)findViewById(R.id.normalBut);
        Button hackerBut=(Button)findViewById(R.id.hackerBut);
        Button hackerButpp=(Button)findViewById(R.id.hackerButpp);
        normalBut.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     mode=1;
                     Intent i = new Intent(MainActivity.this, GameMode.class);

                     startActivity(i);

                 }
             });
        hackerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=2;
                Intent i = new Intent(MainActivity.this, GameMode.class);
                startActivity(i);

            }
        });
        hackerButpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=3;
                Intent i = new Intent(MainActivity.this, GameMode.class);

                startActivity(i);


            }
        });



    }
}
