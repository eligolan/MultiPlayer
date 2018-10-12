package com.example.eligolan.multiplayer;


import android.content.Context;
import android.content.Intent;
import android.media.SoundPool;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    SoundPool soundPool;
    int starsSoundID, winID;
    FirebaseAuth mAuth;
    DatabaseReference roomsRef;

    EditText messageBox;
    ListView listView;
    ArrayList<Room> rooms;
    RoomsListAdapter adapter;

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
        roomsRef = database.getReference("rooms");
        messageBox = findViewById(R.id.roomName);

        rooms = new ArrayList<>();
        listView = findViewById(R.id.listView);
        adapter = new RoomsListAdapter(this, rooms);
        listView.setAdapter(adapter);


       roomsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rooms.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Room r = postSnapshot.getValue(Room.class);
                    rooms.add(r);
                }
              try{
                    adapter.notifyDataSetChanged();
                }catch(Exception ex){
                    Toast.makeText(MainActivity.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Room r = adapter.getItem(position);
                //Toast.makeText(MainActivity.this, r.name, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, RoomPlayer.class);
                intent.putExtra("roomName", r.name);
                intent.putExtra("roomManager", r.manager);
                intent.putExtra("managerKey",r.managerKey);
                intent.putExtra("url",r.urlCurrentSong);
                startActivity(intent);
            }
        });


        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    public void addRoom(View view) {
        String myEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String key =  roomsRef.push().getKey();
        Room r = new Room();
        r.name = messageBox.getText().toString();
        r.manager = myEmail;
        r.managerKey = FirebaseAuth.getInstance().getCurrentUser().getUid();
        r.urlCurrentSong = "http://bff.vr2.net/Don't%20Box%20Me%20In.mp3";
        roomsRef.child(key).setValue(r);
        messageBox.setText("");
        closeKeyboard(view);
    }

    public static class Room{
        public String name;
        public String manager;
        public String managerKey;
        public String urlCurrentSong;
        public int currentSeekBar;
        public boolean stop;
        public Room(){}

        public Room(String name,String manager,String url,int seek) {
            this.name = name;
            this.manager = manager;
            this.urlCurrentSong = url;
            this.currentSeekBar = seek;
        }
    }

    private void closeKeyboard(View view){
        view.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    public static class RoomsListAdapter extends ArrayAdapter<Room> {

        private ArrayList<Room> rooms;

        private static class ViewHolder {
            TextView nameTV;
            TextView managerTV;
        }

        public RoomsListAdapter(Context context, ArrayList<Room> rooms) {
            super(context,R.layout.rooms_list_item, rooms);
            this.rooms = rooms;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Room room = getItem(position);
            ViewHolder viewHolder;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.rooms_list_item, parent, false);
                viewHolder.nameTV = convertView.findViewById(R.id.name);
                viewHolder.managerTV = convertView.findViewById(R.id.manager);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.nameTV.setText(room.name);
            viewHolder.managerTV.setText(room.manager);
            return convertView;
        }
    }
}
