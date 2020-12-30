package com.keennhoward.braintrainer;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    int score = 0, num = 0, answer;
    TextView scoreTextView;
    TextView timeLeftTextView;
    TextView questionTextView;
    TextView doneTextView;
    Button choice1;
    Button choice2;
    Button choice3;
    Button choice4;
    Button restartButton;


    public void checkAnswer(View view){
        Button choice = (Button) view;
        doneTextView.setVisibility(View.VISIBLE);
        if(Integer.parseInt(choice.getText().toString())==answer){
            score++;
            doneTextView.setText("Correct");
        }else{
            doneTextView.setText("Wrong");
        }
        num++;
        scoreTextView.setText(score+"/"+num);
        randomQuestion(view);
    }
    public void reset(View view){
        score = 0;
        num = 0;
        scoreTextView.setText(score+"/"+num);
        start(view);
        randomQuestion(view);
    }

    public void start(View view){
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setVisibility(View.INVISIBLE);
        restartButton.setVisibility(View.INVISIBLE);
        doneTextView.setVisibility(View.INVISIBLE);
        setGameVisible();
        setGameEnabled();


        randomQuestion(view);
        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftTextView.setText(millisUntilFinished/1000+"s");
            }
            @Override
            public void onFinish() {
                doneTextView.setText("Done!");
                restartButton.setVisibility(View.VISIBLE);
                doneTextView.setVisibility(View.VISIBLE);
                setGameDisabled();
            }
        }.start();
    }
    public void randomQuestion(View view){
        int a = generateRandomNumber(),b=generateRandomNumber();
        answer = a + b;
        questionTextView.setText(a+" + "+b);
        ArrayList<Integer> choices = randomArray(answer);
        choice1.setText(Integer.toString(choices.get(0)));
        choice2.setText(Integer.toString(choices.get(1)));
        choice3.setText(Integer.toString(choices.get(2)));
        choice4.setText(Integer.toString(choices.get(3)));
    }
    public ArrayList<Integer> randomArray(int answer){
        ArrayList<Integer> a = new ArrayList<>(asList(generateRandomNumber(),generateRandomNumber(),generateRandomNumber(),answer));
        Collections.shuffle(a);
        return a;
    }

    public int generateRandomNumber(){
        final int min = 1;
        final int max = 49;
        final int random = new Random().nextInt((max - min) + 1) + min;
        return random;
    }

    public void setGameVisible(){
        scoreTextView.setVisibility(View.VISIBLE);
        timeLeftTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        choice1.setVisibility(View.VISIBLE);
        choice2.setVisibility(View.VISIBLE);
        choice3.setVisibility(View.VISIBLE);
        choice4.setVisibility(View.VISIBLE);
    }
    public void setGameEnabled(){
        choice1.setEnabled(true);
        choice2.setEnabled(true);
        choice3.setEnabled(true);
        choice4.setEnabled(true);
    }
    public void setGameDisabled(){
        choice1.setEnabled(false);
        choice2.setEnabled(false);
        choice3.setEnabled(false);
        choice4.setEnabled(false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        choice1 = (Button) findViewById(R.id.button1);
        choice2 = findViewById(R.id.button2);
        choice3 = findViewById(R.id.button3);
        choice4 = findViewById(R.id.button4);
        restartButton = findViewById(R.id.restartButton);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        timeLeftTextView = (TextView) findViewById(R.id.timeLeftTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        doneTextView = findViewById(R.id.doneTextView);

    }
}
