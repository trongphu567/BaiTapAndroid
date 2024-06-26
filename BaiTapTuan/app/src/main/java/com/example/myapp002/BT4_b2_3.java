package com.example.myapp002;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

public class BT4_b2_3 extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt4_b2_3);

        Button btnShowMe = (Button) findViewById(R.id.btnShowMe);

        btnShowMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"));
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+84)123456789"));
                startActivity(intent);

            }
        });
    }
}
