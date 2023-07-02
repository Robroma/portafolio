package com.example.androidfirebase.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidfirebase.R;
import com.example.androidfirebase.controller.Controller;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements ViewActivity{
    private CardView logOut;
    private CardView perfil;
    private CardView ahorcado;
    private CardView paraulogic;
    private CardView estadisticas;
    private CardView otros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        callControllerWithThisActivityonParameter();
    }

    @Override
    public void callControllerWithThisActivityonParameter() {
        Controller.getInstance().homeActivity(this);
    }

    @Override
    public void createAllItemsGlobalWithGetters() {
        this.logOut = findViewById(R.id.logOut);
        this.perfil = findViewById(R.id.perfil);
        this.ahorcado = findViewById(R.id.ahorcado);
        this.paraulogic = findViewById(R.id.paraulogic);
        this.estadisticas = findViewById(R.id.estadisticas);
    }

    public CardView getLogOut() {
        return logOut;
    }

    public CardView getPerfil() {
        return perfil;
    }

    public CardView getAhorcado() {
        return ahorcado;
    }

    public CardView getParaulogic() {
        return paraulogic;
    }

    public CardView getEstadisticas() {
        return estadisticas;
    }
}