package com.example.xemnamamlich;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xemNamAL();
            }
        });

    }

    private void xemNamAL() {
        EditText txtNam = findViewById(R.id.txtNam);
        int nam = Integer.parseInt(txtNam.getText().toString());
        AmLich al = new AmLich(nam);
        TextView ketQua = findViewById(R.id.txtKetqua);
        ketQua.setText(al.getNamAL());
    }
}