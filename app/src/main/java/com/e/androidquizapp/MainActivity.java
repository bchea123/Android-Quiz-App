package com.e.androidquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button resultsButton;
    private TextView statementText;
    private int score = 0;

    private int currentStatementIndex = 0;

    private Statements[] statementBank = new Statements[]{

            new Statements(R.string.open_source_statement, true),
            new Statements(R.string.version_name_statement, false),
            new Statements(R.string.profit_statement, true),
            new Statements(R.string.logo_statement, true),
            new Statements(R.string.launched_statement, false),
            new Statements(R.string.available_statement, false),
            new Statements(R.string.users_statement, true),
            new Statements(R.string.idea_statement, false)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Statements statement = new Statements(R.string.open_source_statement, true);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        resultsButton = findViewById(R.id.results_button);
        statementText = findViewById(R.id.statement_text);

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        resultsButton.setOnClickListener(this);

        nextButton.setEnabled(false);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.true_button:
                checkAnswer(true);
                falseButton.setEnabled(false);
                trueButton.setEnabled(false);
                nextButton.setEnabled(true);
                break;
            case R.id.false_button:
                checkAnswer(false);
                falseButton.setEnabled(false);
                trueButton.setEnabled(false);
                nextButton.setEnabled(true);
                break;
            case R.id.next_button:
                if(currentStatementIndex < statementBank.length - 1) {
                    currentStatementIndex++;
                    statementText.setText(statementBank[currentStatementIndex].getAnswerResId());
                    falseButton.setEnabled(true);
                    trueButton.setEnabled(true);
                    nextButton.setEnabled(false);
                }
                else {
                    statementText.setText("Quiz Completed!");
                    falseButton.setEnabled(false);
                    trueButton.setEnabled(false);
                    nextButton.setEnabled(false);
                    resultsButton.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.results_button:
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
                break;
        }
    }

    private void checkAnswer(boolean userChoice){
        boolean correctChoice = statementBank[currentStatementIndex].isAnswerTrue();
        int toastMessageId;

        if (userChoice == correctChoice){
            toastMessageId = R.string.correct_answer;
            score += 1;
        }
        else {
            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
    }
}
