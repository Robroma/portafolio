package com.example.a09uf2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a09uf2.model.User;

import java.util.HashMap;
import java.util.Map;

public class AccountActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private TextView email;
    private EditText phone;
    private Button btnUpdate;
    private Button btnDelete;
    private TextView errorProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        name = findViewById(R.id.editProfileName);
        password = findViewById(R.id.editProfilePassword);
        email = findViewById(R.id.textProfileEmail);
        phone = findViewById(R.id.editProfilePhone);
        btnDelete = findViewById(R.id.btnDeleteProfile);
        btnUpdate = findViewById(R.id.btnUpdateProfile);
        errorProfile = findViewById(R.id.errorProfile);

        name.setText(user.getName());
        password.setText(user.getPassword());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());

        Context context = this;
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                String url = "https://www.joanseculi.com/edt69/updateuser.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (response.equals("User updated")) {
                                    Intent intent = new Intent(context, LoginActivity.class);
                                    intent.putExtra("email", email.getText().toString());
                                    intent.putExtra("password", password.getText().toString());
                                    context.startActivity(intent);
                                } else errorProfile.setText(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error aquí
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", name.getText().toString());
                        params.put("email", email.getText().toString());
                        params.put("password", password.getText().toString());
                        params.put("phone", phone.getText().toString());

                        return params;
                    }
                };
                requestQueue.add(stringRequest);


            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                String url = "https://www.joanseculi.com/edt69/deleteuser.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (response.equals("User deleted")) {
                                    Intent intent = new Intent(context, LoginActivity.class);
                                    context.startActivity(intent);
                                } else errorProfile.setText(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error aquí
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email.getText().toString());

                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });


    }
}