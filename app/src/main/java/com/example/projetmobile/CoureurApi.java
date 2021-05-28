package com.example.projetmobile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoureurApi {
    @GET("coureursapi.json")
    Call<RestCoureurResponse> getCoureurResponse();
}
