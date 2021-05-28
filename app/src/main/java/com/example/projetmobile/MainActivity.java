package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://ikebyt7.github.io/";

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("appli", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Coureur> coureurList = getDataFromCache();

        if (coureurList != null) {
            showList(coureurList);
        } else {
            makeApiCall();
        }


    }

    private List<Coureur> getDataFromCache() {

        String jsonCoureur = sharedPreferences.getString(Constants.KEY_COUREUR_LIST, null);

        if (jsonCoureur == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Coureur>>() {
            }.getType();
            return gson.fromJson(jsonCoureur, listType);
        }
    }

    public void showList(List<Coureur> coureurList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(coureurList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CoureurApi coureurApi = retrofit.create(CoureurApi.class);

        Call<RestCoureurResponse> call = coureurApi.getCoureurResponse();
        call.enqueue(new Callback<RestCoureurResponse>() {
            @Override
            public void onResponse(Call<RestCoureurResponse> call, Response<RestCoureurResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Coureur> coureurList = response.body().getResults();
                    saveList(coureurList);
                    showList(coureurList);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestCoureurResponse> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Coureur> coureurList) {
        String jsonString = gson.toJson(coureurList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_COUREUR_LIST, jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "Liste saved", Toast.LENGTH_SHORT).show();
    }

    ;


    public void showLoader() {

    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}