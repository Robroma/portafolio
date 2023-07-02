package com.example.androidfirebase.controller;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.androidfirebase.R;
import com.example.androidfirebase.dao.FireStoreDB;
import com.example.androidfirebase.utils.Constants;
import com.example.androidfirebase.utils.Provider;
import com.example.androidfirebase.view.HomeActivity;
import com.example.androidfirebase.view.LoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginController implements ControllerInterface {

    private FireStoreDB fireStore;
    private LoginActivity loginActivity;

    public LoginController(LoginActivity loginActivity, FireStoreDB fireStore) {
        this.fireStore = fireStore;
        this.loginActivity = loginActivity;
    }

    @Override
    public void createActivityButtons() {
        this.loginActivity.getGoogleButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginActivity.signIn();
            }
        });

        this.loginActivity.getLoginButton().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = loginActivity.getEmail().getText().toString().trim();
                String password = loginActivity.getPsswd().getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {

                    fireStore.getFAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                switchActivity(loginActivity, new HomeActivity());
                            } else {
                                showAlert(loginActivity, "Email o contraseña incorrectos");
                            }
                        }
                    });
                } else {
                    showAlert(loginActivity, "Error en el login");
                }
            }
        });

        this.loginActivity.getRegisterButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = loginActivity.getEmail().getText().toString().trim();
                String password = loginActivity.getPsswd().getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {

                    fireStore.getFAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                fireStore.addUser(Provider.LOGIN);
                                switchActivity(loginActivity, new HomeActivity());
                            } else {
                                showAlert(loginActivity, "Error en el registro");
                            }

                        }
                    });
                } else {
                    showAlert(loginActivity, "Error en el registro");
                }
            }
        });


    }
}
