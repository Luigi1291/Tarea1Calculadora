package com.example.tarea1calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String pResult = getIntent().getExtras().getString("RESULT_VALUE");
        SetupUI(pResult);
    }

    private void SetupUI(String pResultado){
        TextView mTextView = (TextView) findViewById(R.id.calculatorResult);
        mTextView.setText(pResultado);
    }
}
