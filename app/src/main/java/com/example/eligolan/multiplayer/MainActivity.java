package com.example.eligolan.multiplayer;


import android.media.SoundPool;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    SoundPool soundPool;
    int starsSoundID, winID;
    FirebaseAuth mAuth;
    DatabaseReference usersRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            String userNmae = mAuth.getCurrentUser().getEmail();
            TextView tv = findViewById(R.id.titleM);
            tv.setText("Hello " + userNmae);
        }else{
            Toast.makeText(this, "somthing wrong", Toast.LENGTH_SHORT).show();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("rooms");
/*        if(usersRef == null){
            Toast.makeText(this,"is null",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
        }*/
/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);*/

/*
        String url = "https://s10.onlinevideoconverter.com/download?file=j9i8g6d3a0e4"; // your URL here
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();*/

    }
}
