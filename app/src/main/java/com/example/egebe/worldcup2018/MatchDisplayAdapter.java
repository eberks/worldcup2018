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

public class MatchDisplayAdapter extends RecyclerView.Adapter<MatchDisplayAdapter.ViewHolder> {

    private LayoutInflater customInflater;
    private Context context;
    private WorldCupResponse wcrData = WorldCupData.getInstance().getWorldCupResponse();
    private ArrayList<Match> listMatch;

    @NonNull
    @Override
    public MatchDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = customInflater.inflate(R.layout.bottom_row, parent, false);
        return new ViewHolder(view);
    }


    MatchDisplayAdapter(Context context, ArrayList<Match> listMatch) {
        this.context = context;
        this.listMatch = listMatch;
        this.customInflater = LayoutInflater.from(this.context);
    }


    @Override
    public void onBindViewHolder(@NonNull MatchDisplayAdapter.ViewHolder holder, int position) {

        Match tempMatch=listMatch.get(position);
        List<Integer> teamIds = new ArrayList<>();
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < listMatch.size(); i++) {

            if (teamIds.contains(listMatch.get(i).getAway_team())) {
                i++;
            } else {
                teamIds.add(listMatch.get(i).getAway_team());
            }
        }
        for (int i = 0; i < listMatch.size(); i++) {

            if (teamIds.contains(listMatch.get(i).getHome_team())) {
                i++;
            } else {
                teamIds.add(listMatch.get(i).getHome_team());
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
        return listMatch.size();
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
