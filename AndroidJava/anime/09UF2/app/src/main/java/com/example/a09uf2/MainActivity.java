package com.example.a09uf2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a09uf2.adapters.AnimeAdapter;
import com.example.a09uf2.model.Anime;
import com.example.a09uf2.model.User;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AnimeAdapter adapter;
    private List<Anime> animes;
    private ImageView account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        System.out.println(user.toString());

        account = findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(v.getContext(), AccountActivity.class);
                intent1.putExtra("user",user);
                v.getContext().startActivity(intent1);
            }
        });

        recyclerView = findViewById(R.id.recyclerAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Context context = this;
        String url = "https://joanseculi.com/edt69/animes3.php?email="+user.getEmail();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Anime> animesFiltrats = new ArrayList<>();
                try {
                    JSONArray resultsJSOON = response.getJSONArray("animes");
                    animes = Arrays.asList(new GsonBuilder().create().fromJson(resultsJSOON.toString(), Anime[].class));

                    for (Anime anime : animes) {
                        if (anime.getActive().equals("true")) {
                            animesFiltrats.add(anime);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new AnimeAdapter(animesFiltrats,user.getEmail(),context);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}