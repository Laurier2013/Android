package com.example.min.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        Button btlamp = (Button) findViewById(R.id.buttonLamps);
        btlamp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent lampActivity = new Intent(ListItemsActivity.this, LampActivity.class);
                startActivity(lampActivity);
            }
        });

        Button bttv = (Button) findViewById(R.id.buttonTV);
        bttv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tvActivity = new Intent(ListItemsActivity.this, TVActivity.class);
                startActivity(tvActivity);
            }
        });

        Button btblinds = (Button) findViewById(R.id.buttonBlinds);
        btblinds.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent blindsActivity = new Intent(ListItemsActivity.this, BlindsActivity.class);
                startActivity(blindsActivity);
            }
        });
    }

    public void onStart(){
        super.onStart();
    }

    public void onResume(){
        super.onResume();
    }

    public void onPause(){
        super.onPause();
    }

    public void onStop(){
        super.onStop();
    }

    public void onDestroy(){
        super.onDestroy();
    }

}
