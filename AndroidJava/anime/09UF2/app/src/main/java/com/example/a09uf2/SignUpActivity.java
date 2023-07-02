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

public class SignUpActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText phone;
    private Button btnSgnUp;
    private TextView errorRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        errorRegister = findViewById(R.id.errorRegister);
        errorRegister.setText(" ");
        name = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword2);
        phone = findViewById(R.id.editTextPhone);
        btnSgnUp = findViewById(R.id.signUpBtnRegister);

        Context context = this;
        btnSgnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                String url = "https://joanseculi.com/edt69/createuser2.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (response.equals("User created")) {
                                    Intent intent = new Intent(context, LoginActivity.class);
                                    intent.putExtra("email",email.getText().toString());
                                    intent.putExtra("password",password.getText().toString());
                                    context.startActivity(intent);
                                }else errorRegister.setText(response);
                                System.out.println(response);
                                // Manejar la respuesta exitosa aquí
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error aquí
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("name",name.getText().toString());
                        params.put("email",email.getText().toString());
                        params.put("password",password.getText().toString());
                        params.put("phone",phone.getText().toString());


                        return params;
                    }
                };
                requestQueue.add(stringRequest);

            }

        });


    }
}