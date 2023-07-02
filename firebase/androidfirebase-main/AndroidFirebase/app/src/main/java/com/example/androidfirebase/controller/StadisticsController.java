package com.example.androidfirebase.controller;

import com.example.androidfirebase.dao.FireStoreDB;
import com.example.androidfirebase.model.UserStats;
import com.example.androidfirebase.view.StadisticsActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class StadisticsController implements ControllerInterface{

    public StadisticsActivity stadisticsActivity;
    private FireStoreDB fireStore;
    private UserStats user;

    public StadisticsController(StadisticsActivity stadisticsActivity,FireStoreDB fireStore) {
        this.stadisticsActivity = stadisticsActivity;
        this.fireStore = fireStore;
        user = new UserStats();
    }

    @Override
    public void createActivityButtons() {
        fireStore.getUserStats().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(UserStats.class);
                if (documentSnapshot.exists()){
                    System.out.println(user.getUltimoInicio());
                    stadisticsActivity.getAhorcadoGanadas().setText("Partidas Ganadas Ahorcado:\n "+user.getAhorcadoGanadas());
                    stadisticsActivity.getParaulogicGanadas().setText("Partidas Ganadas Paraulogic: \n"+user.getParaulogicGanadas());
                    stadisticsActivity.getNumConexiones().setText("Numero de inicios de sesión: \n"+user.getNumConexiones());
                    stadisticsActivity.getUltimoInicio().setText("Última sesión: \n"+user.getUltimoInicio().toString());
                }else alertToast(stadisticsActivity,"Error al trobar el teu usuari");
            }
        });

    }
}
