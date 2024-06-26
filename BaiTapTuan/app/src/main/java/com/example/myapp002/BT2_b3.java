package com.example.myapp002;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.util.Date;

public class BT2_b3 extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt2_b3);

        final Button btn = (Button) findViewById(R.id.xemThoiGian);
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Date t = new Date();
                String message = "Thoi gian hien hanh " + t.toString();
                dialog.setMessage(message);
                dialog.show();
            }
        });
    }
}
