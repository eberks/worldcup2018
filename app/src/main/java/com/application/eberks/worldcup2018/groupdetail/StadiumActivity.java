package com.application.eberks.worldcup2018.groupdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.application.eberks.worldcup2018.Match;
import com.application.eberks.worldcup2018.R;
import com.application.eberks.worldcup2018.Stadium;
import com.application.eberks.worldcup2018.Team;
import com.application.eberks.worldcup2018.WorldCupResponse;
import com.application.eberks.worldcup2018.models.WorldCupDataSingleton;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class StadiumActivity extends AppCompatActivity implements OnMapReadyCallback {

    private WorldCupResponse worldCupResponse = WorldCupDataSingleton.getInstance().getWorldCupResponse();
    private Stadium tempStadium;
    private LatLng latLng;
    private Intent mIntent;
    private Team awayTeam;
    private Team homeTeam;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stadium_layout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);

        mIntent = getIntent();
        Match match = (Match) mIntent.getSerializableExtra("extra_match");

        for (int i = 0; i < worldCupResponse.getTeams().size(); i++) {
            if (match.getHome_team() == worldCupResponse.getTeams().get(i).getId()) {
                homeTeam = worldCupResponse.getTeams().get(i);
            }
        }

        for (int i = 0; i < worldCupResponse.getTeams().size(); i++) {
            if (match.getAway_team() == worldCupResponse.getTeams().get(i).getId()) {
                awayTeam = worldCupResponse.getTeams().get(i);
            }
        }

        for (int i = 0; i < worldCupResponse.getStadiums().size(); i++) {
            if (match.getStadium() == worldCupResponse.getStadiums().get(i).getId()) {
                tempStadium = worldCupResponse.getStadiums().get(i);
            }
        }

        ImageView homeFlag = findViewById(R.id.home_team_img);
        Picasso.with(this).load(homeTeam.getFlag()).into(homeFlag);
        ImageView awayFlag = findViewById(R.id.away_team_img);
        Picasso.with(this).load(awayTeam.getFlag()).into(awayFlag);

        TextView homeName = findViewById(R.id.home_name_txt);
        homeName.setText(homeTeam.getName());

        TextView homeScore = findViewById(R.id.home_score_txt);

        if (match.getHome_result() == null) {
            homeScore.setText("");
        } else {
            homeScore.setText(String.format("%d", match.getHome_result()));

        }

        TextView awayName = findViewById(R.id.away_name_txt);
        awayName.setText(awayTeam.getName());
        TextView awayScore = findViewById(R.id.away_score_txt);
        if (match.getAway_result() == null) {
            awayScore.setText("");
        } else {
            awayScore.setText(String.format("%d", match.getAway_result()));
        }


        if (match.getAway_penalty() != 0 && match.getHome_penalty() != 0) {

            TextView penaltyResult = findViewById(R.id.penalties_txt);
            penaltyResult.setText("(P) " + match.getHome_penalty() + " - " + match.getAway_penalty() + " (P)");
        }

        ImageView stadiumImage = findViewById(R.id.stadium_img);
        Picasso.with(this).load(tempStadium.getImage()).into(stadiumImage);
        getActionBar().setTitle(homeTeam.getName() + " vs " + awayTeam.getName());

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mIntent = getIntent();
        Match match = (Match) mIntent.getSerializableExtra("extra_match");

        for (int i = 0; i < worldCupResponse.getStadiums().size(); i++) {
            if (match.getStadium() == worldCupResponse.getStadiums().get(i).getId()) {
                tempStadium = worldCupResponse.getStadiums().get(i);
            }
        }

        latLng = new LatLng(tempStadium.getLat(), tempStadium.getLng());

        googleMap.addMarker(new MarkerOptions().position(latLng)
                .title("Match Place : " + tempStadium.getName())
                .snippet("City : " + tempStadium.getCity()));


        CameraUpdate center =
                CameraUpdateFactory.newLatLng(latLng);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);

        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);

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
}
