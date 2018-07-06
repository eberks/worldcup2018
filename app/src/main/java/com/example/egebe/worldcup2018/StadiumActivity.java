package com.example.egebe.worldcup2018;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StadiumActivity extends AppCompatActivity implements OnMapReadyCallback {

    private WorldCupResponse worldCupResponse = WorldCupDataSingleton.getInstance().getWorldCupResponse();
    private Stadium tempStadium;
    private LatLng latLng;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stadium_layout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Intent intent = getIntent();
        Match match = (Match) intent.getSerializableExtra("extra_match");

        for (int i = 0; i < worldCupResponse.getStadiums().size(); i++) {
            if (match.getStadium() == worldCupResponse.getStadiums().get(i).getId()) {
                tempStadium = worldCupResponse.getStadiums().get(i);
            }
        }

        latLng = new LatLng(tempStadium.getLat(), tempStadium.getLng());

        googleMap.addMarker(new MarkerOptions().position(latLng)
                .title("Match Place : " + tempStadium.getName())
                .snippet("City : " + tempStadium.getCity()));


        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
