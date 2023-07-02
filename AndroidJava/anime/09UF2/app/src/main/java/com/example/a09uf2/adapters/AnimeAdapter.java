package com.example.a09uf2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a09uf2.AnimeDetailActivity;
import com.example.a09uf2.R;
import com.example.a09uf2.model.Anime;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {
    private List<Anime> animes;
    private Context context;
    private String email;

    public AnimeAdapter(List<Anime> animes, String email,Context context) {
        this.animes = animes;
        this.email = email;
        this.context = context;
    }

    @NonNull
    @Override
    public AnimeAdapter.AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_recycler, null, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeAdapter.AnimeViewHolder holder, int position) {
        Picasso.get().load("https://joanseculi.com/" + animes.get(position).getImage()).fit().centerCrop().into(holder.image);
        holder.name.setText(animes.get(position).getName());
        holder.description.setText(animes.get(position).getDescription());
        holder.year.setText(animes.get(position).getYear());
        holder.genre.setText(animes.get(position).getDemography());
        if (animes.get(position).getFavorite() != null) {
            holder.fav.setImageResource(R.drawable.ic_favorite_orange);
        }
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animes.get(position).getFavorite() != null) {
                    RequestQueue requestQueue;
                    requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                    String url = "https://www.joanseculi.com/edt69/deletefavorite.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                        holder.fav.setImageResource(R.drawable.ic_favorite_white);

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
                            params.put("email", email);
                            params.put("anime", animes.get(position).getName());

                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);

                } else {
                    RequestQueue requestQueue;
                    requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                    String url = "https://joanseculi.com/edt69/insertfavorite.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("favorite inserted")) {
                                        holder.fav.setImageResource(R.drawable.ic_favorite_orange);
                                    } else {
                                        System.out.println("error");
                                    }
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
                            params.put("email", email);
                            params.put("anime", animes.get(position).getName());

                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    public class AnimeViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView description;
        TextView year;
        TextView genre;
        ImageView fav;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageRecycler);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            year = itemView.findViewById(R.id.year);
            genre = itemView.findViewById(R.id.genre);
            fav = itemView.findViewById(R.id.favIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context = v.getContext();
                    Intent intent = new Intent(context, AnimeDetailActivity.class);
                    intent.putExtra("id", animes.get(getAdapterPosition()).getId());
                    intent.putExtra("name", animes.get(getAdapterPosition()).getName());
                    intent.putExtra("description", animes.get(getAdapterPosition()).getDescription());
                    intent.putExtra("type", animes.get(getAdapterPosition()).getType());
                    intent.putExtra("year", animes.get(getAdapterPosition()).getYear());
                    intent.putExtra("image", animes.get(getAdapterPosition()).getImage());
                    intent.putExtra("image2", animes.get(getAdapterPosition()).getImage2());
                    intent.putExtra("originalname", animes.get(getAdapterPosition()).getOriginalname());
                    intent.putExtra("rating", animes.get(getAdapterPosition()).getRating());
                    intent.putExtra("demography", animes.get(getAdapterPosition()).getDemography());
                    intent.putExtra("genre", animes.get(getAdapterPosition()).getGenre());
                    intent.putExtra("favorite", animes.get(getAdapterPosition()).getFavorite());
                    context.startActivity(intent);
                }
            });

        }
    }
}
