package com.example.tarea1calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static boolean Operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupUI(){
        Operator = false;

        Button mButton1 = (Button) findViewById(R.id.Button1);
        onClickEvent(mButton1,"1");

        Button mButton2 = (Button) findViewById(R.id.Button2);
        onClickEvent(mButton2,"2");

        Button mButton3 = (Button) findViewById(R.id.Button3);
        onClickEvent(mButton3,"3");

        Button mButton4 = (Button) findViewById(R.id.Button4);
        onClickEvent(mButton4,"4");

        Button mButton5 = (Button) findViewById(R.id.Button5);
        onClickEvent(mButton5,"5");

        Button mButton6 = (Button) findViewById(R.id.Button6);
        onClickEvent(mButton6,"6");

        Button mButton7 = (Button) findViewById(R.id.Button7);
        onClickEvent(mButton7,"7");

        Button mButton8 = (Button) findViewById(R.id.Button8);
        onClickEvent(mButton8,"8");

        Button mButton9 = (Button) findViewById(R.id.Button9);
        onClickEvent(mButton9,"9");

        Button mButton0 = (Button) findViewById(R.id.Button0);
        onClickEvent(mButton0,"0");

        Button mButtonSuma = (Button) findViewById(R.id.ButtonSuma);
        onClickEventOperator(mButtonSuma,"+");

        Button mButtonResta = (Button) findViewById(R.id.ButtonResta);
        onClickEventOperator(mButtonResta,"-");

        Button mButtonMulti = (Button) findViewById(R.id.ButtonMulti);
        onClickEventOperator(mButtonMulti,"x");

        Button mButtonResult = (Button) findViewById(R.id.ButtonResultado);
        onClickEventResultado(mButtonResult);

        Button mButtonAC = (Button) findViewById(R.id.buttonAC);
        mButtonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mCalculatorText = (EditText) findViewById(R.id.calculatorText);
                mCalculatorText.setText("");
            }
        });

    }

    private void onClickEvent(Button pButton, final String pValue){
        pButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mCalculatorText = (EditText) findViewById(R.id.calculatorText);
                mCalculatorText.setText(mCalculatorText.getText().toString()+ pValue);
                Operator = false;
            }
        });
    }

    private void onClickEventOperator(Button pButton, final String pValue){
        pButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mCalculatorText = (EditText) findViewById(R.id.calculatorText);

                if(Operator == true || mCalculatorText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Operación inválida", Toast.LENGTH_SHORT).show();
                }
                else {
                    mCalculatorText.setText(mCalculatorText.getText().toString() + pValue);
                    Operator = true;
                }
            }
        });
    }

    private void onClickEventResultado(Button pButton){
        pButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Operator == true){
                    Toast.makeText(getApplicationContext(), "Operación inválida", Toast.LENGTH_SHORT).show();
                }
                else {
                    EditText mCalculatorText = (EditText) findViewById(R.id.calculatorText);
                    String operacion = mCalculatorText.getText().toString();

                    String cadena = "";
                    String operador = "";
                    Integer valor = 0;
                    Integer resultado = 0;

                    for(char ch: operacion.toCharArray()){
                        if (Character.isDigit(ch)){
                            cadena += String.valueOf(ch);
                        }
                        else {
                            if (valor != 0) {
                                switch (operador) {
                                    case "+":
                                        resultado = valor + Integer.parseInt(cadena);
                                        break;
                                    case "-":
                                        resultado = valor - Integer.parseInt(cadena);
                                        break;
                                    case "x":
                                        resultado = valor * Integer.parseInt(cadena);
                                        break;
                                }
                                valor = resultado;
                                operador = String.valueOf(ch);
                                cadena = "";
                            } else {
                                valor = Integer.parseInt(cadena);
                                cadena = "";
                                operador = String.valueOf(ch);
                            }
                        }
                    }
                    //Ultima operacion no se ejecuta dentro del FOR
                    switch (operador) {
                        case "+":
                            resultado = valor + Integer.parseInt(cadena);
                            break;
                        case "-":
                            resultado = valor - Integer.parseInt(cadena);
                            break;
                        case "x":
                            resultado = valor * Integer.parseInt(cadena);
                            break;
                    }
                    //No existio operacion
                    if(resultado == 0){
                        Toast.makeText(getApplicationContext(), "Operación inexistente", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                        intent.putExtra("RESULT_VALUE", resultado.toString());
                        startActivity(intent);
                    }
                }
            }
        });
    }
}

