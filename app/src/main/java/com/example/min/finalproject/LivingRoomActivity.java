package com.example.min.finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LivingRoomActivity extends AppCompatActivity {
    private Context ctx;
    private ListView livingroomlist;
    private String[] livingroom = {"Lamp   status", "TV", "Window Blinds"};
    protected Boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        ctx = this;
        isTablet = (findViewById(R.id.fragmentHolder)!=null); // boolean variable to tell if it's a tablet
        livingroomlist = (ListView)findViewById(R.id.livinglists);
        livingroomlist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                if(!isTablet) {//phone
                    switch (position) {
                        case 0: //lamp
                            Intent lampActivity = new Intent(ctx, LampActivity.class);
                            lampActivity.putExtra("Status", "Off");
                            startActivityForResult(lampActivity, 10);
                            break;
                        case 1: // tv
                            Intent tvActivity = new Intent(ctx, TVActivity.class);
                            tvActivity.putExtra("TV State", "Off");
                            startActivityForResult(tvActivity, 20);
                            break;
                        case 2: // blinds
                            Intent blindsActivity = new Intent(ctx, BlindsActivity.class);
                            blindsActivity.putExtra("Blinds Status", "Off");
                            startActivityForResult(blindsActivity, 30);
                            break;
                    }
                }else{// tablet
                }
            }
        });

        livingroomlist.setAdapter(new ArrayAdapter<>(this, R.layout.living_row_layout, livingroom ));
        Log.i("LivingRoomActivity", "OnCreate");
    }

    public void onStart(){
        super.onStart();
        Log.i("LivingRoomActivity", "onStart");
    }

    public void onResume(){

        super.onResume();
        Log.i("LivingRoomActivity", "onResume");
    }

    public void onPause(){

        super.onPause();
        Log.i("LivingRoomActivity", "onPause");
    }

    public void onStop()
    {
        super.onStop();
        Log.i("LivingRoomActivity", "onStop");
    }

    public void onDestroy(){

        super.onDestroy();
        Log.i("LivingRoomActivity", "ondestroy");
    }
    //This function gets called when another activity has finished and this activity is resuming:
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(data != null)
        {
            String fromOtherActivity = data.getStringExtra("Status");
            Log.d("LivingRoomActivity", "Back from  other activity: " + fromOtherActivity);
        }
    }
}
