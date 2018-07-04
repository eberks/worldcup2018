package com.example.egebe.worldcup2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnGroupItemClickListener {

    private WorldCupResponse mWorldCupResponse;
    private KnockoutResponse mKnockoutResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomAdapter.OnGroupItemClickListener tempListener = this;

        Button kButton = findViewById(R.id.knockout_button);


        kButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KnockoutActivity.class);
                intent.putExtra("extra_response", mWorldCupResponse);
                intent.putExtra("extra_knockout", mKnockoutResponse);
                startActivity(intent);
            }
        });


        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<WorldCupResponse> request = NetworkManager.getInstance().getApiClient().getInfo();
        request.enqueue(new Callback<WorldCupResponse>() {
            @Override
            public void onResponse(Call<WorldCupResponse> call, Response<WorldCupResponse> response) {

                mWorldCupResponse = response.body();
                WorldCupData.getInstance().setWorldCupResponse(response.body());
                List<Group> groupList = new ArrayList<>();

                groupList.add(response.body().getGroups().getA());
                groupList.add(response.body().getGroups().getB());
                groupList.add(response.body().getGroups().getC());
                groupList.add(response.body().getGroups().getD());
                groupList.add(response.body().getGroups().getE());
                groupList.add(response.body().getGroups().getF());
                groupList.add(response.body().getGroups().getG());
                groupList.add(response.body().getGroups().getH());

                recyclerView.setAdapter(new CustomAdapter(MainActivity.this, groupList, tempListener));

            }

            @Override
            public void onFailure(Call<WorldCupResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onGroupItemClicked(Group group) {
        Intent intent = new Intent(MainActivity.this, GroupActivity.class);
        intent.putExtra("extra_group", group);
        intent.putExtra("extra_response", mWorldCupResponse);
        startActivity(intent);
    }
}
