package com.example.myapp002;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class BT2_b4 extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt2_b4);

        final EditText editText = (EditText) findViewById(R.id.editText);
        AlertDialog dialog = new AlertDialog.Builder(BT2_b4.this).create();

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                    String message = editText.getText().toString();
                    dialog.setMessage(message);
                    dialog.show();
                }
                return false;
            }
        });
    }
}
