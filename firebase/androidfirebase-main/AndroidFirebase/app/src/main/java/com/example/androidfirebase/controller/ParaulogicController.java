package com.example.androidfirebase.controller;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.androidfirebase.dao.FireStoreDB;
import com.example.androidfirebase.model.Paraulogic;
import com.example.androidfirebase.utils.Constants;
import com.example.androidfirebase.view.ParaulogicActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class ParaulogicController implements ControllerInterface {

    public ParaulogicActivity paraulogicActivity;
    private Paraulogic paraulogic;
    private FireStoreDB fireStore;

    public ParaulogicController(ParaulogicActivity paraulogicActivity,FireStoreDB fireStore ) {
        this.paraulogicActivity = paraulogicActivity;
        this.fireStore = fireStore;
        paraulogic = new Paraulogic();
    }



    @Override
    public void createActivityButtons() {
        startGame();

        this.paraulogicActivity.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = paraulogicActivity.getInput().getText().toString().trim();

                if (comprobarParaula(input)) {
                    if (comprobarParaulaReppetida(input)) {
                        paraulogic.getParaulesCorrectes().add(input);
                        fireStore.addParaulogic(paraulogic);
                        paraulogicActivity.getParulesBones().setText("Paraules correctes: "+ paraulogic.getParaulesCorrectes());
                        paraulogicActivity.getInput().setText("");
                        alertToast(paraulogicActivity,"Correcte");
                        comprobarSiGuanya();
                    }else{
                        paraulogicActivity.getInput().setText("");
                        alertToast(paraulogicActivity,"Ja hi és");
                    }
                } else {
                    alertToast(paraulogicActivity,"Incorrecta");
                }

            }

        });

    }

    private void startGame(){
        fireStore.getParaulogic().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    paraulogic = documentSnapshot.toObject(Paraulogic.class);
                    paraulogicActivity.getImatge().setImageResource(Constants.drawablesParaulogics[paraulogic.getIdParaulogic()]);
                    paraulogicActivity.getParulesBones().setText("Paraules correctes:"+paraulogic.getParaulesCorrectes());
                }else{
                    paraulogic.setIdParaulogic(new Random().nextInt(Constants.paraulogics.length));
                    fireStore.addParaulogic(paraulogic);
                    paraulogicActivity.getImatge().setImageResource(Constants.drawablesParaulogics[paraulogic.getIdParaulogic()]);
                }
            }
        });
    }

    private void comprobarSiGuanya(){
        if(paraulogic.getParaulesCorrectes().size() == Constants.paraulogics[paraulogic.getIdParaulogic()].length){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    alertToast(paraulogicActivity,"Partida guanyada");
                    paraulogic = new Paraulogic();
                    paraulogic.setIdParaulogic(new Random().nextInt(Constants.paraulogics.length));
                    fireStore.addParaulogic(paraulogic);
                    fireStore.updateParaulogicGuanyat();
                    startGame();
                }
            }, 2000);

        }
    }

    private boolean comprobarParaula(String input) {
        for (String str : Constants.paraulogics[paraulogic.getIdParaulogic()]) {
            if (str.equals(input)) {
                return true;
            }
        }
        return false;
    }

    private boolean comprobarParaulaReppetida(String input) {
        for (String str :paraulogic.getParaulesCorrectes() ) {
            if(str.equals(input)){
                return false;
            }
        }
        return true;
    }



}
