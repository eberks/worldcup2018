package com.example.egebe.worldcup2018.grouplist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.egebe.worldcup2018.Group;
import com.example.egebe.worldcup2018.R;
import com.example.egebe.worldcup2018.WorldCupResponse;
import com.example.egebe.worldcup2018.groupdetail.GroupActivity;
import com.example.egebe.worldcup2018.knockout.KnockoutActivity;
import com.example.egebe.worldcup2018.models.WorldCupDataSingleton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GroupAdapter.OnGroupItemClickListener, MainMvpView {

    private WorldCupResponse mWorldCupResponse;
    private MainPresenter mPresenter;
    private RecyclerView recyclerView;

    final GroupAdapter.OnGroupItemClickListener tempListener = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);
        mPresenter.getWorldCupData();

        Button kButton = findViewById(R.id.knockout_button);

        kButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, KnockoutActivity.class);
            intent.putExtra("extra_response", mWorldCupResponse);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onGroupItemClicked(Group group) {
        Intent intent = new Intent(MainActivity.this, GroupActivity.class);
        intent.putExtra("extra_group", group);
        intent.putExtra("extra_response", mWorldCupResponse);
        startActivity(intent);
    }

    @Override
    public void onGetWorldCupDataResponse(WorldCupResponse response) {

        WorldCupDataSingleton.getInstance().setWorldCupResponse(response);
        List<Group> groupList = new ArrayList<>();

        groupList.add(response.getGroups().getA());
        groupList.add(response.getGroups().getB());
        groupList.add(response.getGroups().getC());
        groupList.add(response.getGroups().getD());
        groupList.add(response.getGroups().getE());
        groupList.add(response.getGroups().getF());
        groupList.add(response.getGroups().getG());
        groupList.add(response.getGroups().getH());

        recyclerView.setAdapter(new GroupAdapter(MainActivity.this, groupList, tempListener));
    }
}
