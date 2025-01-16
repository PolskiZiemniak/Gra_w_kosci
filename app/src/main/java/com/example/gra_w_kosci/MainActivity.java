package com.example.gra_w_kosci;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button throwDice, reset;
    private ImageView dice1, dice2, dice3, dice4, dice5;
    private TextView thisRoll, allRolls, rollCount;
    private int[] diceImages = new int[]{R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};

    private Resources res;

    int thisRollCounter = 0;
    int allRollsCounter = 0;
    int numberOfRolls = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        throwDice = findViewById(R.id.throwDice);
        reset = findViewById(R.id.resetDice);
        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        dice3 = findViewById(R.id.dice3);
        dice4 = findViewById(R.id.dice4);
        dice5 = findViewById(R.id.dice5);
        thisRoll = findViewById(R.id.thisRoll);
        allRolls = findViewById(R.id.allRolls);
        rollCount = findViewById(R.id.rollCount);

        res = getResources();

        resetGame();

        throwDice.setOnClickListener(view -> {
            thisRollCounter = 0;
            int[] roll = RollDice();
            updateScore(thisRollCounter);
            updateRollCount();
            displayDiceResult(roll);
        });
        reset.setOnClickListener(view -> resetGame());
    }
    int[] RollDice(){
        Random rand = new Random();
        int[] roll = new int[5];
        for(int i = 0; i < 5; i++){
            int a = rand.nextInt(6)+1;
            roll[i] = a;
            thisRollCounter += a;
        }
        return roll;
    }
    void resetGame(){
        thisRollCounter = 0;
        allRollsCounter = 0;
        numberOfRolls = -1;
        dice1.setImageResource(R.drawable.emptydice);
        dice2.setImageResource(R.drawable.emptydice);
        dice3.setImageResource(R.drawable.emptydice);
        dice4.setImageResource(R.drawable.emptydice);
        dice5.setImageResource(R.drawable.emptydice);
        updateScore(0);
        updateRollCount();
    }
    void updateScore(int newScore){
        allRollsCounter += newScore;
        String thisRollText = res.getString(R.string.thisRoll, String.valueOf(thisRollCounter));
        String allRollsText = res.getString(R.string.allRolls, String.valueOf(allRollsCounter));
        thisRoll.setText(thisRollText);
        allRolls.setText(allRollsText);
    }
    void updateRollCount(){
        numberOfRolls++;
        String rollCountText = res.getString(R.string.rollcount, String.valueOf(numberOfRolls));
        rollCount.setText(rollCountText);
    }
    void displayDiceResult(int[] diceResult){
        dice1.setImageResource(diceImages[diceResult[0]]);
        dice2.setImageResource(diceImages[diceResult[1]]);
        dice3.setImageResource(diceImages[diceResult[2]]);
        dice4.setImageResource(diceImages[diceResult[3]]);
        dice5.setImageResource(diceImages[diceResult[4]]);
    }
}