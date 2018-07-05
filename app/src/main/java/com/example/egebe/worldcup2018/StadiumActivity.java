package com.example.egebe.worldcup2018;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class StadiumActivity extends Activity {

    private WorldCupResponse worldCupResponse = WorldCupDataSingleton.getInstance().getWorldCupResponse();
    private Stadium tempStadium;
    private ImageView imgStadium;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stadium_layout);

        Intent intent = getIntent();

        Match match = (Match) intent.getSerializableExtra("extra_match");


        for (int i = 0; i < worldCupResponse.getStadiums().size(); i++) {

            if (match.getStadium() == worldCupResponse.getStadiums().get(i).getId()) {
                tempStadium = worldCupResponse.getStadiums().get(i);

            }
        }

        imgStadium = findViewById(R.id.image_stadium);
        Picasso.with(this).load(tempStadium.getImage()).into(imgStadium);
    }
}
