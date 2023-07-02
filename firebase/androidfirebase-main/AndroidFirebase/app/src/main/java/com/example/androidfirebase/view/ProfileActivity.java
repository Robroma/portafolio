package com.example.androidfirebase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidfirebase.R;
import com.example.androidfirebase.controller.Controller;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileActivity extends AppCompatActivity implements ViewActivity {

    private TextView email;
    private TextView provider;

    private TextInputEditText name;
    private TextInputEditText surname;
    private TextInputEditText telephone;

    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        callControllerWithThisActivityonParameter();
    }

    @Override
    public void callControllerWithThisActivityonParameter() {
        Controller.getInstance().profileActivity(this);
    }

    @Override
    public void createAllItemsGlobalWithGetters() {
        email = findViewById(R.id.email);
        provider = findViewById(R.id.provider);
        name = findViewById(R.id.nombre);
        surname = findViewById(R.id.apellido);
        telephone = findViewById(R.id.telefono);
        save = findViewById(R.id.guardarBtn);
    }

    public TextView getEmail() {
        return email;
    }

    public TextView getProvider() {
        return provider;
    }

    public TextInputEditText getName() {
        return name;
    }

    public TextInputEditText getSurname() {
        return surname;
    }

    public TextInputEditText getTelephone() {
        return telephone;
    }

    public Button getSave() {
        return save;
    }
}
