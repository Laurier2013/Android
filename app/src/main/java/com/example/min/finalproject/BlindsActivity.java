package com.example.min.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BlindsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blinds);

        Button blindsButton = (Button) findViewById(R.id.buttonblinds);
        blindsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setResult(0);
                finish();
            }
        });

    }

    public void onStart(){
        super.onStart();
        Log.i("BlindsActivity", "onStart");
    }

    public void onResume(){

        super.onResume();
        Log.i("BlindsActivity", "onResume");
    }

    public void onPause(){

        super.onPause();
        Log.i("BlindsActivity", "onPause");
    }

    public void onStop()
    {
        super.onStop();
        Log.i("BlindsActivity", "onStop");
    }

    public void onDestroy(){

        super.onDestroy();
        Log.i("BlindsActivity", "ondestroy");
    }

}

