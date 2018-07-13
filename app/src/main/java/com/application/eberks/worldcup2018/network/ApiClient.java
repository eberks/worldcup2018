package com.application.eberks.worldcup2018.network;

import com.application.eberks.worldcup2018.WorldCupResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

    @GET("/lsv/fifa-worldcup-2018/master/data.json")
    Call<WorldCupResponse> getInfo();
}


