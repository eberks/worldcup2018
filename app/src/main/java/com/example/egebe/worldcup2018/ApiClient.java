package com.example.egebe.worldcup2018;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

    @GET("/lsv/fifa-worldcup-2018/master/data.json")
    Call<WorldCupResponse> getInfo();
}


