package com.example.androidfirebase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidfirebase.R;
import com.example.androidfirebase.controller.Controller;
import com.google.android.material.textfield.TextInputEditText;

public class ParaulogicActivity extends AppCompatActivity implements ViewActivity{
    private ImageView imatge;
    private TextInputEditText input;
    private TextView parulesBones;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paraulogic);

        callControllerWithThisActivityonParameter();
    }

    @Override
    public void callControllerWithThisActivityonParameter() {
        Controller.getInstance().paraulogicActivity(this);
    }

    @Override
    public void createAllItemsGlobalWithGetters() {
        this.imatge = findViewById(R.id.imatgeParaulogic);
        this.input = findViewById(R.id.inputParaulogic);
        this.parulesBones = findViewById(R.id.paraulesBones);
        this.button = findViewById(R.id.buttonParaulogic);
    }

    public ImageView getImatge() {
        return imatge;
    }

    public TextInputEditText getInput() {
        return input;
    }

    public TextView getParulesBones() {
        return parulesBones;
    }

    public Button getButton() {
        return button;
    }
}