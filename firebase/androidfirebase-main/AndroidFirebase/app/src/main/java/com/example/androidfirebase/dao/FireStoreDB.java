package com.example.androidfirebase.dao;

import com.example.androidfirebase.model.HangedMan;
import com.example.androidfirebase.model.Paraulogic;
import com.example.androidfirebase.utils.Provider;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;


public class FireStoreDB {
    private FirebaseFirestore fs = FirebaseFirestore.getInstance();
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();

    public FirebaseAuth getFAuth() {
        return fAuth;
    }

    public FirebaseUser getUser() {
        return fAuth.getCurrentUser();
    }

    public void addUser(Provider provider) {
        HashMap<String, Long> userStats = new HashMap<String, Long>();

        userStats.put("numConexiones", 0L);
        userStats.put("ahorcadoGanadas", 0L);
        userStats.put("paraulogicGanadas", 0L);
        fs.collection("estadisticas").document(getUser().getEmail()).set(userStats);

        HashMap<String, Timestamp> lastConnection = new HashMap<String, Timestamp>();
        lastConnection.put("UltimoInicio", Timestamp.now());
        fs.collection("estadisticas").document(getUser().getEmail())
                .set(lastConnection, SetOptions.merge());

        HashMap<String, String> userInfo = new HashMap<String, String>();
        userInfo.put("nombre", "");
        userInfo.put("provider", provider.toString());
        fs.collection("perfil").document(getUser().getEmail()).set(userInfo);
    }

    public void updateConections() {
        DocumentReference userRef = fs.collection("estadisticas").document(getUser().getEmail());
        userRef.update("numConexiones", FieldValue.increment(1));
        userRef.update("UltimoInicio",Timestamp.now());

    }

    public Task<DocumentSnapshot> getGameHM() {
        return fs.collection("ahorcado").document(getUser().getEmail()).get();
    }

    public void addGameHM(HangedMan hangedMan) {
        fs.collection("ahorcado").document(getUser().getEmail()).set(hangedMan);
    }

    public void updateWinsHM(String email) {
        fs.collection("estadisticas").document(email).update("ahorcadoGanadas", FieldValue.increment(1));
    }
    public void addParaulogic(Paraulogic praulogic){
        fs.collection("Paraulogic").document(getUser().getEmail()).set(praulogic);
    }
    public Task<DocumentSnapshot> getParaulogic(){
        return fs.collection("Paraulogic").document(getUser().getEmail()).get();
    }

    public void updateParaulogicGuanyat() {
        DocumentReference userRef = fs.collection("estadisticas").document(getUser().getEmail());
        userRef.update("paraulogicGanadas", FieldValue.increment(1));
    }

    public Task<DocumentSnapshot> getUserProfile(String email) {
        return fs.collection("perfil").document(email).get();
    }

    public void updateUserProfile (String name,String surname, String telephone){
        DocumentReference userRef = fs.collection("perfil").document(getUser().getEmail());
        userRef.update("nombre", name);
        userRef.update("apellido", surname);
        userRef.update("telefono", telephone);
    }


    public Task<DocumentSnapshot> getUserStats(){
        return fs.collection("estadisticas").document(getUser().getEmail()).get();
    }


}
