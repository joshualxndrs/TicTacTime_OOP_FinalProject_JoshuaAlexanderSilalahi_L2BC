package com.example.tictactime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// CLASS TO ADD PLAYER NAMES //

public class AddPlayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // connects directly to the add players page
        // Carries instances from the activity_add_players.xml file
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        // Elements from the activity_add_players.xml are assigned to a variable through the findViewByID method
        final EditText playerOne = findViewById(R.id.playerOneName);
        final EditText playerTwo = findViewById(R.id.playerTwoName);
        final Button startGameBtn = findViewById(R.id.startGameBtn);




        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Final Method - Once set cannot be overriden
                // To String function to set user input as the Players' name
                final String getPlayerOneName = playerOne.getText().toString();
                final String getPlayerTwoName = playerTwo.getText().toString();

                // No Input = Input required message
                if(getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()){
                    Toast.makeText(AddPlayers.this, "Player names are required!", Toast.LENGTH_SHORT).show();
                }
                // Name is inputted = Player names added
                else{
                    Intent intent = new Intent(AddPlayers.this, MainActivity.class);
                    intent.putExtra("playerOne", getPlayerOneName);
                    intent.putExtra("playerTwo", getPlayerTwoName);
                    startActivity(intent);
                }
            }
        });

    }
}