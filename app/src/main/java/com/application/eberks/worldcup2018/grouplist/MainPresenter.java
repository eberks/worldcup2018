package com.application.eberks.worldcup2018.grouplist;

import android.content.Context;
import android.widget.Toast;

import com.application.eberks.worldcup2018.WorldCupResponse;
import com.application.eberks.worldcup2018.network.NetworkManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainMvpPresenter {

    private MainMvpView mView;

    MainPresenter(MainMvpView mView) {
        this.mView = mView;
    }

    public void getWorldCupData() {
        Call<WorldCupResponse> request = NetworkManager.getInstance().getApiClient().getInfo();
        request.enqueue(new Callback<WorldCupResponse>() {
            @Override
            public void onResponse(Call<WorldCupResponse> call, Response<WorldCupResponse> response) {

                mView.onGetWorldCupDataResponse(response.body());
            }

            @Override
            public void onFailure(Call<WorldCupResponse> call, Throwable t) {
                Toast.makeText((Context) mView, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
