package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    private static final String BASE_URL = "https://ikebyt7.github.io/";

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showList();
        makeApiCall();

    }



        private void makeApiCall(){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            CoureurApi coureurApi = retrofit.create(CoureurApi.class);

            Call<RestCoureurResponse> call = coureurApi.getCoureurResponse();
            call.enqueue(new Callback<RestCoureurResponse>() {
                @Override
                public void onResponse(Call<RestCoureurResponse> call, Response<RestCoureurResponse> response) {
                    if(response.isSuccessful() && response.body() != null){
                        List<Coureur> coureurList = response.body().getResults();
                        Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();
                    } else {
                        showError();
                    }
                }

                @Override
                public void onFailure(Call<RestCoureurResponse> call, Throwable t) {
                        showError();
                }
            });
        };


    public void showList() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }

        // define an adapter
        mAdapter = new ListAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }
    
    public void showLoader() {

    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}