package com.example.myapp002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class BT4_b1 extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt4_b1);

        Button btn = (Button) findViewById(R.id.btn);
        EditText inputName = (EditText) findViewById(R.id.inputName);
        EditText inputEmailAddress = (EditText) findViewById(R.id.inputEmailAddress);
        EditText inputProject = (EditText) findViewById(R.id.inputProject);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGetContactInfo = new Intent(getApplicationContext(), BT4_b1_viewcontactinfo.class);

                Bundle bundle = new Bundle();
                bundle.putString("name", inputName.getText().toString());
                bundle.putString("email", inputEmailAddress.getText().toString());
                bundle.putString("project", inputProject.getText().toString());
                iGetContactInfo.putExtras(bundle);
                startActivity(iGetContactInfo);
            }
        });
    }
}
