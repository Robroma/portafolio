package com.example.androidfirebase.controller;

import android.view.View;

import com.example.androidfirebase.dao.FireStoreDB;
import com.example.androidfirebase.view.HangedManActivity;
import com.example.androidfirebase.view.HomeActivity;
import com.example.androidfirebase.view.LoginActivity;
import com.example.androidfirebase.view.ParaulogicActivity;
import com.example.androidfirebase.view.ProfileActivity;
import com.example.androidfirebase.view.StadisticsActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeController implements ControllerInterface {

    public HomeActivity homeActivity;
    private FireStoreDB fireStore;

    public HomeController(HomeActivity homeActivity, FireStoreDB fireStore) {
        this.homeActivity = homeActivity;
        this.fireStore = fireStore;
    }

    @Override
    public void createActivityButtons() {
        fireStore.updateConections();
        this.homeActivity.getLogOut().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                switchActivity(homeActivity, new LoginActivity());
            }
        });
        this.homeActivity.getPerfil().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(homeActivity, new ProfileActivity());
            }
        });
        this.homeActivity.getAhorcado().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(homeActivity, new HangedManActivity());
            }
        });
        this.homeActivity.getParaulogic().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(homeActivity, new ParaulogicActivity());
            }
        });
        this.homeActivity.getEstadisticas().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(homeActivity, new StadisticsActivity());
            }
        });

    }
}
