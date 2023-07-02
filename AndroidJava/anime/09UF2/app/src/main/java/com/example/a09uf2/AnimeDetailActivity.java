package com.example.a09uf2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a09uf2.adapters.AnimeAdapter;
import com.example.a09uf2.adapters.AnimeVideosAdapter;
import com.example.a09uf2.model.Anime;
import com.example.a09uf2.model.AnimeVideos;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimeDetailActivity extends AppCompatActivity {
    private int id;
    private String name;
    private String description;
    private String type;
    private String year;
    private String image;
    private String image2;
    private String originalname;
    private String rating;
    private String demography;
    private String genre;
    private String favorite;

    private TextView nameDetail;
    private TextView descriptionDetail;
    private TextView typeDetail;
    private TextView yearDetail;
    private ImageView imageDetail;
    private ImageView image2Detail;
    private TextView originalnameDetail;
    private TextView ratingDetail;
    private TextView demographyDetail;
    private TextView genreDetail;
    private ImageView favoriteDetail;

    private List<AnimeVideos> animeVideos;
    private AnimeVideosAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_detail);

        nameDetail = findViewById(R.id.nameDetail);
        descriptionDetail = findViewById(R.id.synopsisDetail);
        typeDetail = findViewById(R.id.typeDetail);
        yearDetail = findViewById(R.id.yearDetail);
        imageDetail = findViewById(R.id.imageDetail);
        image2Detail = findViewById(R.id.image2Detail);
        originalnameDetail = findViewById(R.id.originalnameDetail);
        ratingDetail = findViewById(R.id.ratingDetail);
        demographyDetail = findViewById(R.id.demographyDetail);
        genreDetail = findViewById(R.id.genreDetial);
        favoriteDetail = findViewById(R.id.favIconDetail);
        recyclerView = findViewById(R.id.videos);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = 0;
                name = null;
                description = null;
                type = null;
                year = null;
                image = null;
                image2 = null;
                originalname = null;
                rating = null;
                demography = null;
                genre = null;
                favorite = null;
            } else {
                id = extras.getInt("id");
                name = extras.getString("name");
                description = extras.getString("description");
                type = extras.getString("type");
                year = extras.getString("year");
                image = extras.getString("image");
                image2 = extras.getString("image2");
                originalname = extras.getString("originalname");
                rating = extras.getString("rating");
                demography = extras.getString("demography");
                genre = extras.getString("genre");
                favorite = extras.getString("favorite");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("id");
            name = (String) savedInstanceState.getSerializable("name");
            description = (String) savedInstanceState.getSerializable("description");
            type = (String) savedInstanceState.getSerializable("type");
            year = (String) savedInstanceState.getSerializable("year");
            image = (String) savedInstanceState.getSerializable("image");
            image2 = (String) savedInstanceState.getSerializable("image2");
            originalname = (String) savedInstanceState.getSerializable("originalname");
            rating = (String) savedInstanceState.getSerializable("rating");
            demography = (String) savedInstanceState.getSerializable("demography");
            genre = (String) savedInstanceState.getSerializable("genre");
            favorite = (String) savedInstanceState.getSerializable("favorite");
        }

        nameDetail.setText(name);
        descriptionDetail.setText(description);
        typeDetail.setText(type);
        yearDetail.setText(year);
        originalnameDetail.setText(originalname);
        ratingDetail.setText(rating);
        demographyDetail.setText(demography);
        genreDetail.setText(genre);

        Picasso.get().load("https://joanseculi.com/"+image).fit().centerCrop().into(imageDetail);
        Picasso.get().load("https://joanseculi.com/"+image2).fit().centerCrop().into(image2Detail);

        if (favorite != null){
            favoriteDetail.setImageResource(R.drawable.ic_favorite_orange);
        }
        //https://joanseculi.com/edt69/animevideos.php?idanime=2

        String url = "https://joanseculi.com/edt69/animevideos.php?idanime="+id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray resultsJSOON = response.getJSONArray("animevideos");
                    animeVideos = Arrays.asList(new GsonBuilder().create().fromJson(resultsJSOON.toString(), AnimeVideos[].class));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new AnimeVideosAdapter(animeVideos);
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