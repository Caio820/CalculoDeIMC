package com.example.calculodeimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    private TextView textView, textViewMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        textView = findViewById(R.id.textViewResultado);
        textViewMensagem = findViewById(R.id.textViewMensagem);

        Double imc = calculaIMC();
        String sIMC = getStringIMC(imc);

        textView.setText(sIMC);
        textViewMensagem.setText(avaliaIMC(imc));

        if(imc > 30){
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
    }

    private Double calculaIMC() {
        Intent intent = getIntent();
        Double altura = intent.getExtras().getDouble("Altura");
        Double peso = intent.getExtras().getDouble("Peso");

        Double imc = peso / (altura * altura);

        //String imcS = String.format("%.2f", imc);

        return imc;
    }

    private String getStringIMC(Double imc) {
        String sIMC = imc.toString();
        int i = sIMC.indexOf('.');
        sIMC = sIMC.substring(0,i+2);
        return sIMC;
    }

    private String avaliaIMC(Double imc){

        if (imc < 18.5){
            return "Abaixo do peso";
        }else if (imc > 18.5 && imc < 24.9){
            return "Peso Normal";
        }else if (imc > 25 && imc < 29.9){
            return "Sobrepeso";
        }else if (imc > 30 && imc < 34.9){
            return "Obesidade grau 1";
        }else if (imc > 35 && imc < 39.9){
            return "Obesidade grau 2";
        }else {
            return "Obesidade grau 3";
        }
    }
}
