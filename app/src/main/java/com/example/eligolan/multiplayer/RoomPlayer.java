package com.example.eligolan.multiplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoomPlayer extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    Handler updateHandler = new Handler();
    MediaPlayer mediaPlayer;
    boolean doneLoading;
    DatabaseReference roomsRef;
    String nRoom;
    int tries = 0;
    String idRoom;
    int currPos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_player);
        /* set text room */
        nRoom = getIntent().getStringExtra("roomName");
        TextView messageBox;
        messageBox = findViewById(R.id.nameRoom);
        messageBox.setText(nRoom);
        /* seek bar */
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        roomsRef = database.getReference("rooms");

        FirebaseDatabase.getInstance().getReference().child("rooms")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            MainActivity.Room room = snapshot.getValue(MainActivity.Room.class);
                            if(room.name.equals(nRoom)){
                                idRoom = snapshot.getKey();
                                currPos = room.currentSeekBar;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    String url = getIntent().getStringExtra("url");
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare();
                    doneLoading = true;
                    seekBar.post(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setMax(mediaPlayer.getDuration());
                            seekBar.setOnSeekBarChangeListener(seekBarListener);
                            findViewById(R.id.progressBar).setVisibility(View.GONE);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    setResult(-1);
                    finish();
                }

            }

        }).start();
    }
    public void play(View v) {
        if (!doneLoading) {
            Toast.makeText(this, "still loading the song..", Toast.LENGTH_LONG).show();
            return;
        }
        FirebaseDatabase.getInstance().getReference().child("rooms")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            MainActivity.Room room = snapshot.getValue(MainActivity.Room.class);
                            if(room.name.equals(nRoom)){
                                currPos = room.currentSeekBar;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
        mediaPlayer.seekTo(currPos);
        mediaPlayer.start();
        updateHandler.postDelayed(updateSeekBar, 100);
    }

    Runnable updateSeekBar = new Runnable() {
        public void run() {
            if (mediaPlayer.isPlaying()) {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                updateHandler.postDelayed(this, 2000);
            }
        }
    };
    public void pause(View v) {
        if (!doneLoading) {
            Toast.makeText(this, "still loading the song..", Toast.LENGTH_LONG).show();
            return;
        }
        mediaPlayer.pause();
        int media_length = mediaPlayer.getCurrentPosition();
        DatabaseReference updateData = FirebaseDatabase.getInstance()
                .getReference("rooms")
                .child(idRoom);
        updateData.child("currentSeekBar").setValue(media_length);
    }

    public void stop(View v) {
        if (!doneLoading) {
            Toast.makeText(this, "still loading the song..", Toast.LENGTH_LONG).show();
            return;
        }
        mediaPlayer.pause();
        mediaPlayer.seekTo(0);
        seekBar.setProgress(0);
        DatabaseReference updateData = FirebaseDatabase.getInstance()
                .getReference("rooms")
                .child(idRoom);
        updateData.child("currentSeekBar").setValue(0);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        updateHandler.removeCallbacks(updateSeekBar);
        updateHandler = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
    }



    SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        int startTouch;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            startTouch = seekBar.getProgress();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (!doneLoading) {
                Toast.makeText(RoomPlayer.this, "still loading the song..", Toast.LENGTH_LONG).show();
                return;
            }
            if (!mediaPlayer.isPlaying()) {
                Toast.makeText(RoomPlayer.this,
                        "you need to press play before you use the seek bar",
                        Toast.LENGTH_LONG).show();
                seekBar.setProgress(startTouch);
                return;
            }
            mediaPlayer.seekTo(seekBar.getProgress());
        }
    };
}
