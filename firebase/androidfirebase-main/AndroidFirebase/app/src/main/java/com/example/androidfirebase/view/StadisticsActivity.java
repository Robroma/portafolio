package com.example.androidfirebase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.androidfirebase.R;
import com.example.androidfirebase.controller.Controller;

public class StadisticsActivity extends AppCompatActivity implements ViewActivity{
    private TextView ahorcadoGanadas;
    private TextView paraulogicGanadas;
    private TextView numConexiones;
    private TextView ultimoInicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        callControllerWithThisActivityonParameter();
    }

    @Override
    public void callControllerWithThisActivityonParameter() {
        Controller.getInstance().stadisticsActivity(this);
    }

    @Override
    public void createAllItemsGlobalWithGetters() {
        this.ahorcadoGanadas = findViewById(R.id.ahorcadoGanadas);
        this.paraulogicGanadas = findViewById(R.id.paraulogicGanadas);
        this.numConexiones = findViewById(R.id.numConexiones);
        this.ultimoInicio = findViewById(R.id.UltimoInicio);
    }

    public TextView getAhorcadoGanadas() {
        return ahorcadoGanadas;
    }

    public TextView getParaulogicGanadas() {
        return paraulogicGanadas;
    }

    public TextView getNumConexiones() {
        return numConexiones;
    }

    public TextView getUltimoInicio() {
        return ultimoInicio;
    }
}