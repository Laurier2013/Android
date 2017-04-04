package com.example.min.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LivingRoomActivity extends AppCompatActivity {
    private Context ctx;
    private ListView livingroomlist;
    private String[] livingroom = {"Lamp On/Off", "Lamp Dimmable", "Lamp Colorful", "TV", "Window Blinds"};
    protected Boolean isTablet;
    private int myLamp1Counter,myLamp2Counter, myLamp2Progress, myLamp3Progress, myLamp3Counter, myLamp3Color, myTVChannel, myTVCounter, myBlindsCounter, myBlindsHeight;
    private String strLamp1Status;
    LivingFragmentActivity livingroomfrag;
    Bundle bundle;
    protected static LivingRoomDatabaseHelper livingDataHelper;
    protected SQLiteDatabase db;
    protected Cursor results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);
        bundle  = new Bundle();

        livingDataHelper = new LivingRoomDatabaseHelper(this);
        db = livingDataHelper.getWritableDatabase();
        results = db.query(false, livingDataHelper.TABLE_NAME, new String[]
                { livingDataHelper.LVINGITEM_ID, livingDataHelper.LIVINGITEM_NAME, livingDataHelper.LIVINGITEM_STATUS}, livingDataHelper.LVINGITEM_ID + " not null",
                null, null, null, null, null);
        while( ! results.isAfterLast() ){
            String str1 = results.getString(results.getColumnIndex(livingDataHelper.LIVINGITEM_NAME));
            String str2 = results.getString(results.getColumnIndex(livingDataHelper.LIVINGITEM_STATUS));
            results.moveToNext();
        }

        ctx = this;
        isTablet = (findViewById(R.id.livingroomfragmentHolder)!=null); // boolean variable to tell if it's a tablet
        livingroomlist = (ListView)findViewById(R.id.livinglists);
        livingroomlist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
              if(!isTablet) {//phone
                    switch (position) {
                        case 0: //lamp1
                            Intent lamp1Intent = new Intent(LivingRoomActivity.this, LivingRoomMessageDetails.class);
                            lamp1Intent.putExtra("Lamp1Status",strLamp1Status);
                            lamp1Intent.putExtra("Lamp1Counter", myLamp1Counter);
                            lamp1Intent.putExtra("ItemID",0);
                            lamp1Intent.putExtra("IsTablet",0);
                            startActivityForResult(lamp1Intent, 5); //go to view fragment details
                            break;
                        case 1: //lamp2
                            Intent lamp2intent = new Intent(LivingRoomActivity.this, LivingRoomMessageDetails.class);
                            lamp2intent.putExtra("Lamp2Progress", myLamp2Progress);
                            lamp2intent.putExtra("Lamp2Counter", myLamp2Counter);
                            lamp2intent.putExtra("ItemID",1);
                            lamp2intent.putExtra("IsTablet",0);
                            startActivityForResult(lamp2intent, 10);
                            break;
                        case 2: //lamp3
                            Intent lamp3intent= new Intent(LivingRoomActivity.this, LivingRoomMessageDetails.class);
                            lamp3intent.putExtra("Lamp3Progress", myLamp3Progress);
                            lamp3intent.putExtra("Lamp3Counter", myLamp3Counter);
                            lamp3intent.putExtra("Lamp3Color", myLamp3Color);
                            lamp3intent.putExtra("ItemID",2);
                            lamp3intent.putExtra("IsTablet",0);
                            startActivityForResult(lamp3intent, 15);
                            break;
                        case 3: // tv
                            Intent tvActivity = new Intent(LivingRoomActivity.this, LivingRoomMessageDetails.class);
                            tvActivity.putExtra("TVChannel", myTVChannel);
                            tvActivity.putExtra("TVCounter", myTVCounter);
                            tvActivity.putExtra("ItemID",3);
                            tvActivity.putExtra("IsTablet",0);
                            startActivityForResult(tvActivity, 20);
                            break;
                        case 4: // blinds
                            Intent blindsActivity = new Intent(LivingRoomActivity.this, LivingRoomMessageDetails.class);
                            blindsActivity.putExtra("BlindsHeight", myBlindsHeight);
                            blindsActivity.putExtra("BlindsCounter", myBlindsCounter);
                            blindsActivity.putExtra("ItemID",4);
                            blindsActivity.putExtra("IsTablet",0);
                            startActivityForResult(blindsActivity, 25);
                            break;
                    }
                }else{// tablet
                    //livingroomfrag = new LivingFragmentActivity(LivingRoomActivity.this);
                    //    livingroomfrag.setArguments(bundle);
                    switch (position) {
                        case 0: //lamp1
                            Lamp1Activity lamp1intent = new Lamp1Activity(LivingRoomActivity.this);
                            Bundle bundle = new Bundle();
                            bundle.putString("Lamp1Status",strLamp1Status);
                            bundle.putInt("Lamp1Counter", myLamp1Counter);
                            bundle.putInt("ItemID",0);
                            bundle.putInt("IsTablet",1);
                            lamp1intent.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction().replace(R.id.livingroomfragmentHolder, lamp1intent).commit();
                            break;
                        case 1://lamp2

                            Lamp2Activity lamp2intent = new Lamp2Activity(LivingRoomActivity.this);
                            bundle = new Bundle();
                            bundle.putInt("Lamp2Progress", myLamp2Progress);
                            bundle.putInt("Lamp2Counter", myLamp2Counter);
                            bundle.putInt("ItemID",1);
                            bundle.putInt("IsTablet",1);
                            lamp2intent.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction().replace(R.id.livingroomfragmentHolder, lamp2intent).commit();
                            break;

                        case 2://lamp3

                            Lamp3Activity lamp3intent = new Lamp3Activity(LivingRoomActivity.this);
                            bundle = new Bundle();
                            bundle.putInt("Lamp3Progress", myLamp3Progress);
                            bundle.putInt("Lamp3Counter", myLamp3Counter);
                            bundle.putInt("Lamp3Color", myLamp3Color);
                            bundle.putInt("ItemID",2);
                            bundle.putInt("IsTablet",1);
                            lamp3intent.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction().replace(R.id.livingroomfragmentHolder, lamp3intent).commit();
                            break;
                        case 3://tv
                            TVActivity tvintent = new TVActivity(LivingRoomActivity.this);
                            bundle = new Bundle();
                            bundle.putInt("TVChannel", myTVChannel);
                            bundle.putInt("TVCounter", myTVCounter);
                            bundle.putInt("ItemID",3);
                            bundle.putInt("IsTablet",1);
                            tvintent.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction().replace(R.id.livingroomfragmentHolder, tvintent).commit();
                            break;
                        case 4: // blinds
                            BlindsActivity blindsintent = new BlindsActivity(LivingRoomActivity.this);
                            bundle = new Bundle();
                            bundle.putInt("BlindsHeight", myBlindsHeight);
                            bundle.putInt("BlindsCounter", myBlindsCounter);
                            bundle.putInt("ItemID",4);
                            bundle.putInt("IsTablet",1);
                            blindsintent.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction().replace(R.id.livingroomfragmentHolder, blindsintent).commit();
                            break;

                    }
                }
            }
        });

        //Open a file for storing shared preferences:
        final SharedPreferences prefs = getSharedPreferences("livingroomFile", Context.MODE_PRIVATE);
        //Read the number of times run in the file:
        strLamp1Status = prefs.getString("Lamp1Status", "Off");
        myLamp2Progress = prefs.getInt("Lamp2Progress", 0);
        myLamp3Progress = prefs.getInt("Lamp3Progress", 0);
        myLamp3Color = prefs.getInt("Lamp3Color", 0);
        myTVChannel = prefs.getInt("TVChannel", 0);
        myBlindsHeight = prefs.getInt("BlindsHeight", 0);

        livingroom[0] = "Lamp1 is " + strLamp1Status;
        livingroom[1] = "Lamp2 is " + myLamp2Progress;
        livingroom[2] = "Lamp3 is " + myLamp3Progress + " and color is " + myLamp3Color;
        livingroom[3] = "TV is tuned to channel " + myTVChannel;
        livingroom[4] = "Blinds is tuned to  " + myBlindsHeight + "  cm high";

        myLamp1Counter = prefs.getInt("Lamp1Counter", 0);
        myLamp2Counter = prefs.getInt("Lamp2Counter", 0);
        myLamp3Counter = prefs.getInt("Lamp3Counter", 0);
        myTVCounter = prefs.getInt("TVCounter", 0);
        myBlindsCounter = prefs.getInt("BlindsCounter", 0);

        livingroomlist.setAdapter(new ArrayAdapter<>(this, R.layout.living_row_layout, livingroom ));
        Log.i("LivingRoomActivity", "OnCreate");

        Button btdialog = (Button) findViewById(R.id.livingroomcustombt);
        btdialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder customBuild = new AlertDialog.Builder(ctx);
                LayoutInflater inflater = getLayoutInflater();
                final View v = inflater.inflate(R.layout.activity_living_custom_dialog, null);
                customBuild.setView(inflater.inflate(R.layout.activity_living_custom_dialog, null))
                        .setPositiveButton("YES", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("Yes", "Yes");
                                EditText inputMessage = (EditText) v.findViewById(R.id.livingcustomtext);
                                //Log.i("Text is " + inputMessage.getText().toString());

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int id){
                                Log.i("No", "No");
                            }
                        });
                customBuild.setView(v);
                customBuild.create().show();
            }
        });

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
        /*
        //Open a file for storing shared preferences:
        final SharedPreferences prefs = getSharedPreferences("livingroomFile", Context.MODE_PRIVATE);
        //Get an editor object for writing to the file:
        SharedPreferences.Editor writer = prefs.edit();

        writer.putString("Lamp1Status", strLamp1Status);

        writer.putInt("LampCounter",myLamp1Counter);
        //Write the file:
        writer.commit();
        */
        Log.i("LivingRoomActivity", "ondestroy");
    }
    //This function gets called when another activity has finished and this activity is resuming:
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(data != null)
        {
            String fromOtherActivity = data.getStringExtra("Status");
            Log.d("LivingRoomActivity", "Back from  other activity: " + fromOtherActivity);

            if(requestCode == 5 && resultCode == 0){
                strLamp1Status = data.getStringExtra("Lamp1Status");
            }else if(requestCode == 10 && resultCode == 0){
                myLamp2Progress = data.getIntExtra("Lamp2Progress",0);
            }else if (requestCode == 15 && resultCode == 0){
                myLamp3Progress = data.getIntExtra("Lamp3Progress", 0);
                myLamp3Color = data.getIntExtra("Lamp3Color",0);
            }else if (requestCode == 20 && resultCode == 0){
                myTVChannel = data.getIntExtra("TVChannel", 0);
            }else if(requestCode == 25 && resultCode == 0) {
                myBlindsHeight = data.getIntExtra("BlindsHeight", 0);
            }
            livingroom[0] = "Lamp1 is " + strLamp1Status;
            livingroom[1] = "Lamp2 is " + myLamp2Progress + " degree";
            livingroom[2] = "Lamp3 is " + myLamp3Progress + " and color is " + myLamp3Color;
            livingroom[3] = "TV is tuned to channel " + myTVChannel;
            livingroom[4] = "Blinds is tuned to  " + myBlindsHeight + "  meters height";

            livingroomlist.setAdapter(new ArrayAdapter<>(this, R.layout.living_row_layout, livingroom ));
        }
    }

    public void synclamp1(String lamp1status) {
        strLamp1Status = lamp1status;
        livingroom[0] = "Lamp1 is " + strLamp1Status;

        livingroomlist.setAdapter(new ArrayAdapter<>(this, R.layout.living_row_layout, livingroom ));
    }


    public void synclamp2(int lamp2progress) {
        myLamp2Progress = lamp2progress;

        livingroom[1] = "Lamp2 is " + myLamp2Progress + " degree";

        livingroomlist.setAdapter(new ArrayAdapter<>(this, R.layout.living_row_layout, livingroom ));
    }

    public void synclamp3(int lamp3progress, int color) {
        myLamp3Progress = lamp3progress;
        myLamp3Color = color;

        livingroom[2] = "Lamp3 is " + myLamp3Progress + " and color is " + myLamp3Color;

        livingroomlist.setAdapter(new ArrayAdapter<>(this, R.layout.living_row_layout, livingroom ));
    }

    public void synctv(int tvChannel) {
        myTVChannel = tvChannel;

        livingroom[3] = "TV is tuned to channel " + myTVChannel;

        livingroomlist.setAdapter(new ArrayAdapter<>(this, R.layout.living_row_layout, livingroom ));
    }

    public void syncblinds(int blindsHeight) {
        myBlindsHeight = blindsHeight;

        //livingroom[0] = "Lamp1 is " + strLamp1Status;
        //livingroom[1] = "Lamp2 is " + myLamp2Progress + " degree";
        //livingroom[2] = "Lamp3 is " + myLamp3Progress + " and color is " + myLamp3Color;
        //livingroom[3] = "TV is tuned to channel " + myTVChannel;
        livingroom[4] = "Blinds is tuned to  " + myBlindsHeight + "  meters height";

        livingroomlist.setAdapter(new ArrayAdapter<>(this, R.layout.living_row_layout, livingroom ));
    }

    public void removeFragmentLamp1(Lamp1Activity  lamp1){
        getSupportFragmentManager().beginTransaction().remove(lamp1).commit();
    }

    public void removeFragmentLamp2(Lamp2Activity  lamp2){
        getSupportFragmentManager().beginTransaction().remove(lamp2).commit();
    }

    public void removeFragmentLamp3(Lamp3Activity  lamp3){
        getSupportFragmentManager().beginTransaction().remove(lamp3).commit();
    }

    public void removeFragmentTV(TVActivity tv){
        getSupportFragmentManager().beginTransaction().remove(tv).commit();
    }

    public void removeFragmentBlinds(BlindsActivity blinds){
        getSupportFragmentManager().beginTransaction().remove(blinds).commit();
    }

}
