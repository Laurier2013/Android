package com.example.min.finalproject;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
// Anupam Chugh, [online]Feb 25, 2016, www.journaldev.com/10324/android-snackbar-example-tutorial, [Accessed ] Mar 26, 2017


public class LampActivity extends AppCompatActivity implements ColorPickerDialog.OnColorChangedListener{
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);

        ctx = this;
        Button lampButton = (Button) findViewById(R.id.livinglampbutton);
        lampButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setResult(0);
                finish();
            }
        });

        Button colorlampbutton = (Button) findViewById(R.id.colorlampbutton);
        colorlampbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {// choose color dialog
                ColorPickerDialog color = new ColorPickerDialog(ctx,( ColorPickerDialog.OnColorChangedListener)ctx, "picker", Color.BLACK,Color.WHITE);
                color.show();
            }
        });

        Switch lampSwitch = (Switch) findViewById(R.id.lampSwitch);
        lampSwitch.setSelected(true);
        lampSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CharSequence text;
                int duration;

                if(isChecked){
                    text = "Lamp is On";
                    duration = Toast.LENGTH_SHORT;
                }else{
                    text = "Lamp is Off";
                    duration = Toast.LENGTH_SHORT;
                }

                Toast toast = Toast.makeText(LampActivity.this, text, duration);
                toast.show();
            }
        });

        final SeekBar lampDim = (SeekBar) findViewById(R.id.lampDimmable);
        lampDim.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update(lampDim.getRootView());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


            public void update(View v){
                Snackbar snackbar = Snackbar
                        .make(v, "Lamp is tuned", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                Snackbar snackbar1 = Snackbar.make(view, "Message is stored", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                snackbar.show();
            }
        });
    }

    public void onStart(){

        super.onStart();
        Log.i("LampActivity", "onStart");
    }

    public void onResume(){

        super.onResume();
        Log.i("LampActivity", "onResume");
    }

    public void onPause(){

        super.onPause();
        Log.i("LampActivity", "onPause");
    }

    public void onStop(){

        super.onStop();
        Log.i("LampActivity", "onStop");
    }

    public void onDestroy(){

        super.onDestroy();
        Log.i("LampActivity", "onDestroy");
    }

    @Override
    public void colorChanged(String key, int color) {
        // TODO Auto-generated method stub
        TextView textView = (TextView)findViewById(R.id.colorlamptext);
        textView.setTextColor(color);
    }
}
