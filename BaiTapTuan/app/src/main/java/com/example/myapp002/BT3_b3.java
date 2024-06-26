package com.example.myapp002;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

public class BT3_b3 extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt3_b3);

        RadioButton radioBtnRed = (RadioButton) findViewById(R.id.radioBtnRed);
        radioBtnRed.setBackgroundColor(Color.RED);
        RadioButton radioBtnGreen = (RadioButton) findViewById(R.id.radioBtnGreen);
        radioBtnGreen.setBackgroundColor(Color.GREEN);
        RadioButton radioBtnBlue = (RadioButton) findViewById(R.id.radioBtnBlue);
        radioBtnBlue.setBackgroundColor(Color.BLUE);
        RadioButton radioBtnGray = (RadioButton) findViewById(R.id.radioBtnGray);
        radioBtnGray.setBackgroundColor(Color.GRAY);

        radioBtnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            }
        });

        radioBtnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            }
        });

        radioBtnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            }
        });

        radioBtnGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(Color.GRAY);
            }
        });
    }
}
