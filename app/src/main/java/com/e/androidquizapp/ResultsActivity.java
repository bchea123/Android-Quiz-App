package com.e.androidquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private int score;
    private TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        scoreText = findViewById(R.id.score_text);
        score = getIntent().getIntExtra("score", 0);
        scoreText.setText(Integer.toString(score) + "/8");


    }
}
