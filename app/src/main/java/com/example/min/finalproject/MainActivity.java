package com.example.min.finalproject;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity  extends AppCompatActivity implements ColorPickerDialog.OnColorChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ColorPickerDialog color = new ColorPickerDialog(this,this, "picker", Color.BLACK,Color.WHITE);
        //color.show();
    }

    @Override
    public void colorChanged(String key, int color) {
        // TODO Auto-generated method stub
        TextView textView = (TextView)findViewById(R.id.hello);
        textView.setTextColor(color);
    }
}
