package com.example.androidfirebase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidfirebase.R;
import com.example.androidfirebase.controller.Controller;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HangedManActivity extends AppCompatActivity implements ViewActivity {

    private TextView word;
    private TextView answers;
    private ImageView imageTries;
    private TextInputEditText guessTry;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanged_man);

        callControllerWithThisActivityonParameter();
    }

    @Override
    public void callControllerWithThisActivityonParameter() {
        Controller.getInstance().hangedManActivity(this);
    }

    @Override
    public void createAllItemsGlobalWithGetters() {
        word = findViewById(R.id.Word);
        answers = findViewById(R.id.Answers);
        imageTries = findViewById(R.id.imageTries);
        guessTry = findViewById(R.id.guessTry);
        sendBtn = findViewById(R.id.sendBtn);
    }

    public TextView getWord() {
        return word;
    }

    public TextView getAnswers() {
        return answers;
    }

    public ImageView getImageTries() {
        return imageTries;
    }

    public TextInputEditText getGuessTry() {
        return guessTry;
    }

    public Button getSendBtn() {
        return sendBtn;
    }
}