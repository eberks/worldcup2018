package com.example.egebe.worldcup2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BottomAdapter extends RecyclerView.Adapter<BottomAdapter.ViewHolder> {

    private Group mData;
    private LayoutInflater customInflater;
    private Context context;
    private WorldCupResponse wcrData;
    private Knockout mKnockout;

    public BottomAdapter(Context context, Group group, WorldCupResponse worldCupResponse) {
        this.context = context;
        this.customInflater = LayoutInflater.from(context);
        this.mData = group;
        this.wcrData = worldCupResponse;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = customInflater.inflate(R.layout.bottom_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Match tempMatch = mData.getMatches().get(position);

        List<Integer> teamIds = new ArrayList<>();
        List<Team> teams = new ArrayList<>();


        for (int i = 0; i < mData.getMatches().size(); i++) {

            if (teamIds.contains(mData.getMatches().get(i).getAway_team())) {
                i++;
            } else {
                teamIds.add(mData.getMatches().get(i).getAway_team());
            }
        }
        for (int i = 0; i < mData.getMatches().size(); i++) {

            if (teamIds.contains(mData.getMatches().get(i).getHome_team())) {
                i++;
            } else {
                teamIds.add(mData.getMatches().get(i).getHome_team());
            }
        }

        for (int i = 0; i < teamIds.size(); i++) {

            for (int j = 0; j < wcrData.getTeams().size(); j++) {

                if (wcrData.getTeams().get(j).getId() == teamIds.get(i)) {
                    teams.add(wcrData.getTeams().get(j));
                }

            }

        }

        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getId() == tempMatch.getHome_team())
                holder.homeTeam.setText(teams.get(i).getName());
        }
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getId() == tempMatch.getAway_team())
                holder.awayTeam.setText(teams.get(i).getName());
        }
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getId() == tempMatch.getHome_team())
                holder.homeScore.setText(tempMatch.getHome_result() + "");
        }
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getId() == tempMatch.getAway_team())
                holder.awayScore.setText(tempMatch.getAway_result() + "");
        }

        holder.matchDate.setText(tempMatch.getMatchday() + "");


    }

    @Override
    public int getItemCount() {
        return mData.getMatches().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView matchDate;
        TextView homeTeam;
        TextView homeScore;
        TextView awayScore;
        TextView awayTeam;

        public ViewHolder(View itemView) {
            super(itemView);
            matchDate = itemView.findViewById(R.id.match_date);
            homeTeam = itemView.findViewById(R.id.home_team);
            homeScore = itemView.findViewById(R.id.home_score);
            awayScore = itemView.findViewById(R.id.away_score);
            awayTeam = itemView.findViewById(R.id.away_team);
        }
    }
}
