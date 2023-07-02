package com.example.androidfirebase.controller;

import android.view.View;

import com.example.androidfirebase.dao.FireStoreDB;
import com.example.androidfirebase.view.ProfileActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class ProfileController implements ControllerInterface {

    private ProfileActivity profileActivity;
    private FireStoreDB fireStore;

    public ProfileController(ProfileActivity profileActivity, FireStoreDB fireStore) {
        this.profileActivity = profileActivity;
        this.fireStore = fireStore;
    }

    @Override
    public void createActivityButtons() {
        fireStore.getUserProfile(fireStore.getUser().getEmail()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                profileActivity.getEmail().setText(documentSnapshot.getId());
                profileActivity.getName().setText(documentSnapshot.getString("nombre"));
                profileActivity.getSurname().setText(documentSnapshot.getString("apellido"));
                profileActivity.getTelephone().setText(documentSnapshot.getString("telefono"));
                profileActivity.getProvider().setText(documentSnapshot.getString("provider"));
            }
        });

        profileActivity.getSave().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireStore.updateUserProfile(profileActivity.getName().getText().toString(),
                        profileActivity.getSurname().getText().toString(),
                        profileActivity.getTelephone().getText().toString());
                showAlert(profileActivity, "Perfil actualizado.", "Exito!");
            }
        });
    }
}
