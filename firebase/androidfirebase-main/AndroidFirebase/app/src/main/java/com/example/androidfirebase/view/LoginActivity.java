package com.example.androidfirebase.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidfirebase.R;
import com.example.androidfirebase.controller.Controller;
import com.example.androidfirebase.dao.FireStoreDB;
import com.example.androidfirebase.utils.Constants;
import com.example.androidfirebase.utils.Provider;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity implements ViewActivity {
    private Button loginButton;
    private Button googleButton;
    private Button registerButton;
    private TextInputEditText email;
    private TextInputEditText psswd;
    private FirebaseAuth mAuth;
    private FireStoreDB firestore;
    private static final int RC_SIGN_IN = 1;
    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firestore = new FireStoreDB();
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        callControllerWithThisActivityonParameter();
    }

    @Override
    public void callControllerWithThisActivityonParameter() {
        Controller.getInstance().loginActivity(this);
    }

    @Override
    public void createAllItemsGlobalWithGetters() {
        this.email = findViewById(R.id.email);
        this.psswd = findViewById(R.id.password);
        this.loginButton = findViewById(R.id.loginButton);
        this.googleButton = findViewById(R.id.googleButton);
        this.registerButton = findViewById(R.id.registerButton);
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public TextInputEditText getEmail() {
        return email;
    }

    public TextInputEditText getPsswd() {
        return psswd;
    }

    public Button getGoogleButton() {
        return googleButton;
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void signIn() {
        startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            irHome();
                            FirebaseUser user = mAuth.getCurrentUser();
                            firestore.addUser(Provider.GOOGLE);
                            updateUI(user);
                        } else {
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        user = mAuth.getCurrentUser();
        if (user != null) {
            irHome();
        }
    }

    private void irHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}