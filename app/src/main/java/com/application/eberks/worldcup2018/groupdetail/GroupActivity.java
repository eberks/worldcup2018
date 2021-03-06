package com.application.eberks.worldcup2018.groupdetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.application.eberks.worldcup2018.Group;
import com.application.eberks.worldcup2018.Match;
import com.application.eberks.worldcup2018.R;
import com.application.eberks.worldcup2018.WorldCupResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GroupActivity extends Activity implements MatchDisplayAdapter.OnMatchItemClickListener {

    private GroupTableAdapter groupTableAdapter;
    private MatchDisplayAdapter bottomAdapter;
    private ImageView backgroundImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.group_activity_layout);

        Intent intent = getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);


        Group group = (Group) intent.getSerializableExtra("extra_group");
        WorldCupResponse worldCupResponse = (WorldCupResponse) intent.getSerializableExtra("extra_response");

        RecyclerView topGroupView = findViewById(R.id.top_view);
        RecyclerView bottomGroupView = findViewById(R.id.bottom_view);

        topGroupView.setLayoutManager(new LinearLayoutManager(this));
        bottomGroupView.setLayoutManager(new LinearLayoutManager(this));


        TextView winnerText = findViewById(R.id.winner_text);

        for (int i = 0; i < group.getTeamsInGroup().size(); i++) {
            if (group.getTeamsInGroup().get(i).getId() == group.getWinner())

                winnerText.setText("The Winner of " + group.getName() + " is " + group.getTeamsInGroup().get(i).getName() + "!");
        }


        groupTableAdapter = new GroupTableAdapter(this, group, worldCupResponse);
        topGroupView.setAdapter(groupTableAdapter);

        final MatchDisplayAdapter.OnMatchItemClickListener tempListener = this;
        bottomAdapter = new MatchDisplayAdapter(this, (ArrayList<Match>) group.getMatches(), tempListener);
        bottomGroupView.setAdapter(bottomAdapter);

        backgroundImage = findViewById(R.id.group_background_image);
        Picasso.with(this).load("https://images.pexels.com/photos/47730/the-ball-stadion-football-the-pitch-47730.jpeg?cs=srgb&dl=ball-field-football-47730.jpg&fm=jpg").into(backgroundImage);

        getActionBar().setTitle(group.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMatchItemClicked(Match match) {
        Intent intent = new Intent(GroupActivity.this, StadiumActivity.class);
        intent.putExtra("extra_match", match);
        startActivity(intent);
    }
}



