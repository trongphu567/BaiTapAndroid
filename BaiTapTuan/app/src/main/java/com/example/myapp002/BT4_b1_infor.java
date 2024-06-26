package com.example.myapp002;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class BT4_b1_infor extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_bt4_contactinfo);

        TextView txtName = (TextView) findViewById(R.id.txtName);
        TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
        TextView txtProject = (TextView) findViewById(R.id.txtProject);

        Button finishBtn = (Button) findViewById(R.id.btn);

        Bundle bundle = getIntent().getExtras();
        txtName.setText(bundle.getString("name"));
        txtEmail.setText(bundle.getString("email"));
        txtProject.setText(bundle.getString("project"));

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
