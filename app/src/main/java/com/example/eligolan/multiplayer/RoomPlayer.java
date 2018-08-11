package com.example.eligolan.multiplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RoomPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_player);
        String nRoom = getIntent().getStringExtra("roomName");
        TextView messageBox;
        messageBox = findViewById(R.id.nameRoom);
        messageBox.setText(nRoom);
    }

    public void play(View view) {
    }

    public void pause(View view) {
    }

    public void stop(View view) {
    }
}
