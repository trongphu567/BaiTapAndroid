package com.example.myapp002;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class BT3_b4 extends Activity {
    private EditText textResult;
    private String operator = "";
    public Integer lastvalue = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt3_b4);

        textResult = (EditText) findViewById(R.id.textResult);
        View.OnClickListener btnListener;

        Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bplus, bminus, bmult, bdiv, bresult, breset;

        b1 = (Button) findViewById(R.id.bt1);
        b2 = (Button) findViewById(R.id.bt2);
        b3 = (Button) findViewById(R.id.bt3);
        b4 = (Button) findViewById(R.id.bt4);
        b5 = (Button) findViewById(R.id.bt5);
        b6 = (Button) findViewById(R.id.bt6);
        b7 = (Button) findViewById(R.id.bt7);
        b8 = (Button) findViewById(R.id.bt8);
        b9 = (Button) findViewById(R.id.bt9);
        b0 = (Button) findViewById(R.id.bt0);
        bplus = (Button) findViewById(R.id.btplus);
        bminus = (Button) findViewById(R.id.btminus);
        bmult = (Button) findViewById(R.id.btmultiply);
        bdiv = (Button) findViewById(R.id.btdivide);
        bresult = (Button) findViewById(R.id.btresult);
        breset = (Button) findViewById(R.id.btreset);

        btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button bt = (Button) view;

                switch (bt.getId()) {
                    case R.id.bt1:
                        setValue(textResult, "1");
                        break;
                    case R.id.bt2:
                        setValue(textResult, "2");
                        break;
                    case R.id.bt3:
                        setValue(textResult, "3");
                        break;
                    case R.id.bt4:
                        setValue(textResult, "4");
                        break;
                    case R.id.bt5:
                        setValue(textResult, "5");
                        break;
                    case R.id.bt6:
                        setValue(textResult, "6");
                        break;
                    case R.id.bt7:
                        setValue(textResult, "7");
                        break;
                    case R.id.bt8:
                        setValue(textResult, "8");
                        break;
                    case R.id.bt9:
                        setValue(textResult, "9");
                        break;
                    case R.id.bt0:
                        setValue(textResult, "0");
                        break;
                    case R.id.btplus:
                        operator = "plus";
                        lastvalue = Integer.parseInt(textResult.getText().toString());
                        resetValue();
                        break;
                    case R.id.btminus:
                        operator = "minus";
                        lastvalue = Integer.parseInt(textResult.getText().toString());
                        resetValue();
                        break;
                    case R.id.btmultiply:
                        operator = "multiply";
                        lastvalue = Integer.parseInt(textResult.getText().toString());
                        resetValue();
                        break;
                    case R.id.btdivide:
                        operator = "divide";
                        lastvalue = Integer.parseInt(textResult.getText().toString());
                        resetValue();
                        break;
                    case R.id.btresult:
                        process(lastvalue, Integer.parseInt(textResult.getText().toString()));
                        break;
                    case R.id.btreset:
                        resetValue();
                        break;
                }
            }
        };
        b0.setOnClickListener(btnListener);
        b1.setOnClickListener(btnListener);
        b2.setOnClickListener(btnListener);
        b3.setOnClickListener(btnListener);
        b4.setOnClickListener(btnListener);
        b5.setOnClickListener(btnListener);
        b6.setOnClickListener(btnListener);
        b7.setOnClickListener(btnListener);
        b8.setOnClickListener(btnListener);
        b9.setOnClickListener(btnListener);
        bplus.setOnClickListener(btnListener);
        bminus.setOnClickListener(btnListener);
        bmult.setOnClickListener(btnListener);
        bdiv.setOnClickListener(btnListener);
        bresult.setOnClickListener(btnListener);
        breset.setOnClickListener(btnListener);
    }

    public void setValue(EditText a, String b) {
        String last = a.getText().toString();
        if (!last.equals("0")) {
            last += b;
            b = last;
        }
        a.setText(b);
    }
    public void resetValue() {
        textResult.setText("0");
    }

    public Integer process(Integer a, Integer b) {
        Integer presult = 0;
        if (operator == "plus") {
            presult = a + b;
            textResult.setText(presult.toString());
        }
        if (operator == "minus") {
            presult = a - b;
            textResult.setText(presult.toString());
        }
        if (operator == "multiply") {
            presult = a * b;
            textResult.setText(presult.toString());
        }
        if (operator == "divide") {
            presult = a / b;
            textResult.setText(presult.toString());
        }
        return presult;
    }
}
