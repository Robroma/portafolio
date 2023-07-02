package com.example.a09uf2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a09uf2.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;
    private Button createUser;
    private TextView usuariNoTrobat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String emailRegister;
        String passwordRegister;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                emailRegister = null;
                passwordRegister = null;

            } else {
                emailRegister = extras.getString("email");
                passwordRegister = extras.getString("password");

            }
        } else {
            emailRegister = (String) savedInstanceState.getSerializable("email");
            passwordRegister = (String) savedInstanceState.getSerializable("password");
        }


        usuariNoTrobat = findViewById(R.id.usuariNoTrobat);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.logInBtn);
        createUser = findViewById(R.id.createUserBtn);

        email.setText(emailRegister);
        password.setText(passwordRegister);

        Context context = this;

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://joanseculi.com/edt69/loginuser.php?email=" + email.getText() + "&password=" + password.getText();
                System.out.println(url);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("id") != null)
                                System.out.println(response.getString("id"));
                            else System.out.println("NO sha trobat usuari");
                            User user = new User(Integer.parseInt(response.getString("id")), response.getString("name"), response.getString("email"), response.getString("password"), response.getString("phone"));

                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("user", user);
                            context.startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        usuariNoTrobat.setText("L'usuari no s'ha trobat");
                        System.out.println(error);
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(jsonObjectRequest);

            }
        });

        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SignUpActivity.class);
                context.startActivity(intent);
            }
        });
    }
}