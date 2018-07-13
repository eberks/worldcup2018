package com.application.eberks.worldcup2018.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static final NetworkManager ourInstance = new NetworkManager();

    public static NetworkManager getInstance() {
        return ourInstance;
    }

    private Retrofit retrofit;
    private ApiClient apiClient;

    private NetworkManager() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.build();

        apiClient = retrofit.create(ApiClient.class);
    }

    public ApiClient getApiClient() {
        return apiClient;
    }
}
