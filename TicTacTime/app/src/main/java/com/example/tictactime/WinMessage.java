package com.example.tictactime;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

// WIN MESSAGE POP UP - Shows a dialog message after the end of each game
public class WinMessage extends Dialog {

    private final String message;
    private final MainActivity mainActivity;

    public WinMessage(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.win_message_layout);

        final TextView messageTxt = findViewById(R.id.messageTxt);
        final Button startAgainBtn = findViewById(R.id.startAgainBtn);

        messageTxt.setText(message);

        // Restart Button - starts the game from the beginning upon click
        startAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainActivity.restartMatch();
                dismiss();
            }
        });
    }
}
