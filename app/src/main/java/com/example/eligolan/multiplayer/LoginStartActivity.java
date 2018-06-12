package com.example.eligolan.multiplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.*;

public class LoginStartActivity extends AppCompatActivity {
    ConstraintLayout myLayout;
    AnimationDrawable animationDrawable;
    EditText name, password;
    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthLisener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_start);
        myLayout = (ConstraintLayout) findViewById(R.id.myLayout);
        animationDrawable = (AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();

        //FirebaseApp.initializeApp(this);

        try {
            mAuth = FirebaseAuth.getInstance();
            mAuthLisener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (firebaseAuth.getCurrentUser() == null) {

                    }
                }
            };
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }


        // database = FirebaseDatabase.getInstance();

    }

    public void SignIn(View view) {
        if(!checkIfInternetConnected()){
            Toast.makeText(this,"not Internet Connection",Toast.LENGTH_LONG).show();
            return;
        }

        name = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);


        String _name = name.getText().toString();
        if (_name.isEmpty()) {
            name.setError("name can't be empty");
            return;
        }
        String _pass = password.getText().toString();
        if (_pass.isEmpty()) {
            password.setError("phone can't be empty");
            return;
        }
/*            database.getReference("contacts")
                    .child(_name + "_" + _pass)
                    .setValue(new Contact(_name, _pass));*/

        mAuth.signInWithEmailAndPassword(_name, _pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginStartActivity.this, "sign In field", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(LoginStartActivity.this, MainActivity.class);
                    Toast.makeText(getApplicationContext(), "Connection complete!", //* message *//*
                            Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });

    }


    public void Register(View view) {

        if(!checkIfInternetConnected()){
            Toast.makeText(this,"not Internet Connection",Toast.LENGTH_LONG).show();
            return;
        }

        name = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        String _name = name.getText().toString();
        if (_name.isEmpty()) {
            name.setError("email can't be empty");
            return;
        }
        String _pass = password.getText().toString();
        if (_pass.isEmpty()) {
            password.setError("password can't be empty");
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(_name).matches()){
            name.setError("please enter a valid email");
            return;
        }

        if( _pass.length() < 6){
            password.setError("please enter a valid pass");
            return;
        }

        mAuth.createUserWithEmailAndPassword(_name,_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    Toast.makeText(LoginStartActivity.this, "Register field", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginStartActivity.this,"Register success",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static class Contact {
        public String name, password;

        public Contact() {
        }//must have for firebase to create contacts automatically

        public Contact(String name, String pass) {
            this.name = name;
            this.password = pass;
        }

        @Override
        public String toString() {
            return "name='" + name + '\'' +
                    ", password='" + password + '\'';
        }
    }

    private boolean checkIfInternetConnected() {

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }
        else
            return false;
    }

}
