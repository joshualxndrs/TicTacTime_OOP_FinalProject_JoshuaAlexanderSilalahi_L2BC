package com.example.tictactime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<int[]> combinationsList = new ArrayList<>();

    private int [] boxPositions = {0,0,0,0,0,0,0,0,0};

    private int playerTurn = 1;

    private int totalSelectedBoxes = 1;

    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView playerOneName, playerTwoName;
    private ImageView box1, box2, box3, box4, box5, box6, box7, box8, box9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            playerOneName = findViewById(R.id.playerOneName);
            playerTwoName = findViewById(R.id.playerTwoName);

            playerOneLayout = findViewById(R.id.playerOneLayout);
            playerTwoLayout = findViewById(R.id.playerTwoLayout);

            box1 = findViewById(R.id.box1);
            box2 = findViewById(R.id.box2);
            box3 = findViewById(R.id.box3);
            box4 = findViewById(R.id.box4);
            box5 = findViewById(R.id.box5);
            box6 = findViewById(R.id.box6);
            box7 = findViewById(R.id.box7);
            box8 = findViewById(R.id.box8);
            box9 = findViewById(R.id.box9);

            combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{2, 4, 6});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{0, 4, 8});

        final String getPlayerOneName = getIntent().getStringExtra("playerOne");
        final String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);


        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(0)){
                    performAction((ImageView)v, 0 );
                }

            }
        });

        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(1)){
                    performAction((ImageView)v, 1 );
                }

            }
        });

        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(2)){
                    performAction((ImageView)v, 2 );
                }

            }
        });

        box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(3)){
                    performAction((ImageView)v, 3 );
                }

            }
        });

        box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(4)){
                    performAction((ImageView)v, 4 );
                }

            }
        });

        box6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(5)){
                    performAction((ImageView)v, 5 );
                }

            }
        });

        box7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(6)){
                    performAction((ImageView)v, 6 );
                }

            }
        });

        box8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(7)){
                    performAction((ImageView)v, 7 );
                }

            }
        });

        box9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(8)){
                    performAction((ImageView)v, 8 );
                }

            }
        });

    }

    private void performAction(ImageView imageView, int selectedBoxPosition){

        boxPositions[selectedBoxPosition] = playerTurn;

        if(playerTurn == 1){

            imageView.setImageResource(R.drawable.cross);

            if(checkPlayerWin()){

                WinMessage winMessage = new WinMessage(MainActivity.this, playerOneName.getText().toString() + " is THE CHAMPION!",MainActivity.this);
                winMessage.setCancelable(false);
                winMessage.show();
            }

            else if(totalSelectedBoxes == 9){
                WinMessage winMessage = new WinMessage(MainActivity.this, "MATCH IS A DRAW!",MainActivity.this);
                winMessage.setCancelable(false);
                winMessage.show();
            }

            else{
                changePlayerTurn(2);

                totalSelectedBoxes++;
            }
        }
        else{

            imageView.setImageResource(R.drawable.circle);

            if(checkPlayerWin()){

                WinMessage winMessage = new WinMessage(MainActivity.this, playerTwoName.getText().toString() + " is THE CHAMPION!",MainActivity.this);
                winMessage.setCancelable(false);
                winMessage.show();

            }

            else if(selectedBoxPosition == 9){
                WinMessage winMessage = new WinMessage(MainActivity.this, "MATCH IS A DRAW!",MainActivity.this);
                winMessage.setCancelable(false);
                winMessage.show();
            }

            else{

                changePlayerTurn(1);

                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn){

        playerTurn = currentPlayerTurn;

        if(playerTurn == 1){
            playerOneLayout.setBackgroundResource(R.drawable.round_dark_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_dark_blue_black);
        }
        else{
            playerTwoLayout.setBackgroundResource(R.drawable.round_dark_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_dark_blue_black);
        }
    }

    private boolean checkPlayerWin(){

        boolean response = false;

        for(int i=0; i<combinationsList.size(); i++){

            final int [] combination = combinationsList.get(i);

            if(boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn && boxPositions[combination[2]] == playerTurn){
                response = true;
            }
        }

        return response;
    }

    private Boolean isBoxSelectable(int boxPosition){

        boolean response = false;

        if(boxPositions[boxPosition] == 0){
            response = true;
        }

        return response;
    }

    public void restartMatch(){

        boxPositions = new int[]{0,0,0,0,0,0,0,0,0};

        playerTurn = 1;

        totalSelectedBoxes = 1;

        box1.setImageResource(R.drawable.transparent_bg);
        box2.setImageResource(R.drawable.transparent_bg);
        box3.setImageResource(R.drawable.transparent_bg);
        box4.setImageResource(R.drawable.transparent_bg);
        box5.setImageResource(R.drawable.transparent_bg);
        box6.setImageResource(R.drawable.transparent_bg);
        box7.setImageResource(R.drawable.transparent_bg);
        box8.setImageResource(R.drawable.transparent_bg);
        box9.setImageResource(R.drawable.transparent_bg);
    }

    public void PlayBackgroundSound(View view) {
        Intent intent = new Intent(MainActivity.this, BackgroundSoundService.class);
        startService(intent);
    }
}