package com.example.algeb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SistemasEcuaciones extends AppCompatActivity {
    private EditText[] elem = new EditText[12];
    private double[][] matriz = new double[3][4];
    private EditText E1x1;
    private EditText E1x2;
    private EditText E1x3;
    private EditText E1x4;
    private EditText E2x1;
    private EditText E2x2;
    private EditText E2x3;
    private EditText E2x4;
    private EditText E3x1;
    private EditText E3x2;
    private EditText E3x3;
    private EditText E3x4;
    private Button limpiar;
    private Button cal;

    private TextView X;
    private TextView Y;
    private TextView Z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistemas_ecuaciones);
        elem[0] = findViewById(R.id.editText1);
        elem[1] = findViewById(R.id.editText2);
        elem[2] = findViewById(R.id.editText3);
        elem[3] = findViewById(R.id.editTextX);
        elem[4] = findViewById(R.id.editText4);
        elem[5] = findViewById(R.id.editText5);
        elem[6] = findViewById(R.id.editText6);
        elem[7] = findViewById(R.id.editTextY);
        elem[8] = findViewById(R.id.editText7);
        elem[9] = findViewById(R.id.editText8);
        elem[10] = findViewById(R.id.editText9);
        elem[11] = findViewById(R.id.editTextZ);
        X = findViewById(R.id.resultX);
        Y = findViewById(R.id.resultY);
        Z = findViewById(R.id.resultZ);
        cal = findViewById(R.id.Calcular);
        limpiar = findViewById(R.id.Limpiar);

        X.setText("0");
        Y.setText("0");
        Z.setText("0");

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiard();
            }
        });
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
            }
        });
    }



    public boolean verifica() {

        for (int i = 0; i < elem.length; i++) {
            if (elem[i].getText().toString().equals("")) {
                return true;
            }
        }
        return false;
    }

    public void calcular() {

        if (verifica()) {
            Toast.makeText(getApplicationContext(), "Digite a Matriz completa.", Toast.LENGTH_LONG).show();
        } else {
            int k = 0;
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {

                    if (i != 0)
                        k = j + (int) Math.pow(2, (i + 1));
                    else
                        k = j;

                    matriz[i][j] = Double.parseDouble(elem[k].getText().toString());
                }
            }

            DecimalFormat df = new DecimalFormat("###.###");
            df.setRoundingMode(RoundingMode.HALF_UP);
            df.setMinimumFractionDigits(3);

            X.setText(df.format(Cramer.valorX(matriz)));
            Y.setText(df.format(Cramer.valorY(matriz)));
            Z.setText(df.format(Cramer.valorZ(matriz)));

        }
    }

    public void limpiard() {

        for (int i = 0; i < elem.length; i++) {
            elem[i].setText("");
        }

        X.setText("0");
        Y.setText("0");
        Z.setText("0");
    }
}
