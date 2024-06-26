package com.example.myapp002;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class BT3_b2 extends Activity {
    int Count = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt3_b2);

        CheckBox checkColor = (CheckBox) findViewById(R.id.checkColor);
        CheckBox checkBold = (CheckBox) findViewById(R.id.checkBold);

        Button myBtn = (Button) findViewById(R.id.myBtn);
        EditText myText = (EditText) findViewById(R.id.myText);
        myText.setSingleLine();
        myText.setInputType(InputType.TYPE_NULL);

        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkColor.isChecked())
                    myText.setTextColor(Color.BLUE);
                else
                    myText.setTextColor(Color.BLACK);

                if (checkBold.isChecked())
                    myText.setTypeface(Typeface.DEFAULT_BOLD);
                else
                    myText.setTypeface(Typeface.DEFAULT);

                myText.setText("You've clicked " + ++Count + " times.");
            }
        });
    }
}
