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

public class RoomPlayer extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    Handler updateHandler = new Handler();
    MediaPlayer mediaPlayer;
    boolean doneLoading;
    int tries = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_player);
        /* set text room */
        String nRoom = getIntent().getStringExtra("roomName");
        TextView messageBox;
        messageBox = findViewById(R.id.nameRoom);
        messageBox.setText(nRoom);
        /* seek bar */
        seekBar = (SeekBar) findViewById(R.id.seekBar);


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
                    String url = "http://bff.vr2.net/jazz/Dave%20Brubeck%20Quartet%20featuring%20Paul%20Desmond%20-%20Buried%20Treasures%20()/07%20%20-%20Take%20Five.mp3";
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
    }

    public void stop(View v) {
        if (!doneLoading) {
            Toast.makeText(this, "still loading the song..", Toast.LENGTH_LONG).show();
            return;
        }
        mediaPlayer.pause();
        mediaPlayer.seekTo(0);
        seekBar.setProgress(0);
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
