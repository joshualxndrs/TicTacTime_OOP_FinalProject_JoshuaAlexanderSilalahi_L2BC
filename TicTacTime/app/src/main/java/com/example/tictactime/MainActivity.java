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

// MAIN ACTIVITY//

public class MainActivity extends AppCompatActivity {
    // Using Arrays and List to insert box and combinations
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
            // Calling the name layout and grid layout from the activity_main.xml using findViewById Method
            playerOneName = findViewById(R.id.playerOneName);
            playerTwoName = findViewById(R.id.playerTwoName);

            playerOneLayout = findViewById(R.id.playerOneLayout);
            playerTwoLayout = findViewById(R.id.playerTwoLayout);

            // Calling the assets from the activity_main.xml using findViewById Method
            box1 = findViewById(R.id.box1);
            box2 = findViewById(R.id.box2);
            box3 = findViewById(R.id.box3);
            box4 = findViewById(R.id.box4);
            box5 = findViewById(R.id.box5);
            box6 = findViewById(R.id.box6);
            box7 = findViewById(R.id.box7);
            box8 = findViewById(R.id.box8);
            box9 = findViewById(R.id.box9);

            // Using combinationsList to determine all the possible combinations of 3 grids
            combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{2, 4, 6});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{0, 4, 8});

        // Getting Players' name and replacing "PLAYER ONE" and "PLAYER Two" Text with name inputs
        final String getPlayerOneName = getIntent().getStringExtra("playerOne");
        final String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);

        // BUTTONS

        // grid 1 - on click = input image in box 1
        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(0)){
                    performAction((ImageView)v, 0 );
                }

            }
        });

        // grid 2 - on click = input image in box 2
        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(1)){
                    performAction((ImageView)v, 1 );
                }

            }
        });

        // grid 3 - on click = input image in box 3
        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(2)){
                    performAction((ImageView)v, 2 );
                }

            }
        });

        // grid 4 - on click = input image in box 4
        box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(3)){
                    performAction((ImageView)v, 3 );
                }

            }
        });

        // grid 5 - on click = input image in box 5
        box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(4)){
                    performAction((ImageView)v, 4 );
                }

            }
        });

        // grid 6 - on click = input image in box 6
        box6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(5)){
                    performAction((ImageView)v, 5 );
                }

            }
        });

        // grid 7 - on click = input image in box 7
        box7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(6)){
                    performAction((ImageView)v, 6 );
                }

            }
        });
        // grid 8 - on click = input image in box 8
        box8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(7)){
                    performAction((ImageView)v, 7 );
                }

            }
        });

        // grid 9 - on click = input image in box 9
        box9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBoxSelectable(8)){
                    performAction((ImageView)v, 8 );
                }

            }
        });

    }

    // performAction - Setting up player turns and game outcome conditionals
    private void performAction(ImageView imageView, int selectedBoxPosition){

        boxPositions[selectedBoxPosition] = playerTurn;

        // Check if Player Turn = 1, set image to red cross
        if(playerTurn == 1){

            imageView.setImageResource(R.drawable.cross);

            // Win Message for Player 1 victory, using .getText().toString() method to insert playerOneName into the Champions' Message
            if(checkPlayerWin()){

                WinMessage winMessage = new WinMessage(MainActivity.this, playerOneName.getText().toString() + " is THE CHAMPION!",MainActivity.this);
                winMessage.setCancelable(false);
                winMessage.show();
            }

            // If the outcome of the match is a draw, proceed to display DRAW message
            else if(totalSelectedBoxes == 9){
                WinMessage winMessage = new WinMessage(MainActivity.this, "MATCH IS A DRAW!",MainActivity.this);
                winMessage.setCancelable(false);
                winMessage.show();
            }

            // Switching turns to PLAYER TWO
            else{
                changePlayerTurn(2);

                totalSelectedBoxes++;
            }
        }
        else{

            // Set image to green circle
            imageView.setImageResource(R.drawable.circle);

            // Win Message for Player 2 victory, using .getText().toString() method to insert playerTwoName into the Champions' Message
            if(checkPlayerWin()){

                WinMessage winMessage = new WinMessage(MainActivity.this, playerTwoName.getText().toString() + " is THE CHAMPION!",MainActivity.this);
                winMessage.setCancelable(false);
                winMessage.show();

            }

            // If the outcome of the match is a draw, proceed to display DRAW message
            else if(selectedBoxPosition == 9){
                WinMessage winMessage = new WinMessage(MainActivity.this, "MATCH IS A DRAW!",MainActivity.this);
                winMessage.setCancelable(false);
                winMessage.show();
            }

            // Switching turns back to PLAYER ONE
            else{

                changePlayerTurn(1);

                totalSelectedBoxes++;
            }
        }
    }

    // changePlayerTurn - highlights the current player's border
    // makes turns easily identified
    private void changePlayerTurn(int currentPlayerTurn){

        playerTurn = currentPlayerTurn;

        // Highlights PLAYER ONE's name border
        // PLAYER TWO's border remain without highlight
        if(playerTurn == 1){
            playerOneLayout.setBackgroundResource(R.drawable.round_dark_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_dark_blue_black);
        }
        // Highlights PLAYER TWO's name border
        // PLAYER ONE's border remain without highlight
        else{
            playerTwoLayout.setBackgroundResource(R.drawable.round_dark_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_dark_blue_black);
        }
    }

    // checkPlayerWin - checking combinations and sets, whether it matches the criteria to win the match
    private boolean checkPlayerWin(){

        boolean response = false;

        // Getting player's combination and checking which combinations matches the winning criteria
        for(int i=0; i<combinationsList.size(); i++){

            // Player's input are injected into a List to be checked
            final int [] combination = combinationsList.get(i);

            if(boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn && boxPositions[combination[2]] == playerTurn){
                response = true;
            }
        }

        return response;
    }

    // Setting up empty Boxes
    // Boxes that are clicked cannot be selectable anymore
    private Boolean isBoxSelectable(int boxPosition){

        boolean response = false;

        if(boxPositions[boxPosition] == 0){
            response = true;
        }

        return response;
    }

    // restartMatch - resets all boxPositions to 0
    // all boxes are set into transparent_bg which makes all the grid empty
    // List also returns back to 0
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

    // Calls out the media player from the BackgroundSoundService class to play in-game music.
    public void PlayBackgroundSound(View view) {
        Intent intent = new Intent(MainActivity.this, BackgroundSoundService.class);
        startService(intent);
    }
}