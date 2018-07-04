package com.example.egebe.worldcup2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    private Group gData;
    private LayoutInflater customInflater;
    private Context context;
    private WorldCupResponse wcrData;

    public TopAdapter(Context context, Group group, WorldCupResponse worldCupResponse) {
        this.context = context;
        this.customInflater = LayoutInflater.from(context);
        this.gData = group;
        this.wcrData = worldCupResponse;
    }

    @NonNull
    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = customInflater.inflate(R.layout.top_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAdapter.ViewHolder holder, int position) {


        List<Integer> teamIds = new ArrayList<>();
        List<Team> teams = new ArrayList<>();


        for (int i = 0; i < gData.getMatches().size(); i++) {

            if (teamIds.contains(gData.getMatches().get(i).getAway_team())) {
                i++;
            } else {
                teamIds.add(gData.getMatches().get(i).getAway_team());
            }
        }
        for (int i = 0; i < gData.getMatches().size(); i++) {

            if (teamIds.contains(gData.getMatches().get(i).getHome_team())) {
                i++;
            } else {
                teamIds.add(gData.getMatches().get(i).getHome_team());
            }
        }

        for (int i = 0; i < teamIds.size(); i++) {

            for (int j = 0; j < wcrData.getTeams().size(); j++) {

                if (wcrData.getTeams().get(j).getId() == teamIds.get(i)) {
                    teams.add(wcrData.getTeams().get(j));
                }

            }

        }

        Team currentTeam = teams.get(position);
        int numOfMatch = 0;
        int numOfWin = 0;
        int numOfDraw = 0;
        int numOfLose = 0;


        for (int i = 0; i < gData.getMatches().size(); i++) {
            if (gData.getMatches().get(i).getAway_team() == currentTeam.getId()) numOfMatch++;
        }
        for (int i = 0; i < gData.getMatches().size(); i++) {
            if (gData.getMatches().get(i).getHome_team() == currentTeam.getId()) numOfMatch++;
        }
        for (int i = 0; i < gData.getMatches().size(); i++) {
            if (gData.getMatches().get(i).getAway_team() == currentTeam.getId() && gData.getMatches().get(i).getAway_result() > gData.getMatches().get(i).getHome_result()) {
                numOfWin++;
            } else if (gData.getMatches().get(i).getAway_team() == currentTeam.getId() && gData.getMatches().get(i).getAway_result() < gData.getMatches().get(i).getHome_result()) {
                numOfLose++;
            } else if (gData.getMatches().get(i).getAway_team() == currentTeam.getId() && gData.getMatches().get(i).getAway_result() == gData.getMatches().get(i).getHome_result()) {
                numOfDraw++;
            }
        }
        for (int i = 0; i < gData.getMatches().size(); i++) {
            if (gData.getMatches().get(i).getHome_team() == currentTeam.getId() && gData.getMatches().get(i).getHome_result() > gData.getMatches().get(i).getAway_result()) {
                numOfWin++;
            } else if (gData.getMatches().get(i).getHome_team() == currentTeam.getId() && gData.getMatches().get(i).getHome_result() < gData.getMatches().get(i).getAway_result()) {
                numOfLose++;
            } else if (gData.getMatches().get(i).getHome_team() == currentTeam.getId() && gData.getMatches().get(i).getHome_result() == gData.getMatches().get(i).getAway_result()) {
                numOfDraw++;
            }
        }
        int totalPoint = (numOfWin * 3) + (numOfDraw);
        int totalGS = 0;
        int totalGC = 0;

        for (int i = 0; i < gData.getMatches().size(); i++) {
            if (gData.getMatches().get(i).getAway_team() == currentTeam.getId()) {
                totalGS = totalGS + gData.getMatches().get(i).getAway_result();
                totalGC = totalGC + gData.getMatches().get(i).getHome_result();
            }

        }
        for (int i = 0; i < gData.getMatches().size(); i++) {
            if (gData.getMatches().get(i).getHome_team() == currentTeam.getId()) {
                totalGS = totalGS + gData.getMatches().get(i).getHome_result();
                totalGC = totalGC + gData.getMatches().get(i).getAway_result();
            }

        }

        int totalAverage = totalGS - totalGC;

        Picasso.with(context).load(teams.get(position).getFlag()).into(holder.imgFlag);
        holder.txtName.setText(teams.get(position).getName());
        holder.txtPlayed.setText("" + numOfMatch + "");
        holder.txtWdl.setText(numOfWin + "-" + numOfDraw + "-" + numOfLose);
        holder.txtGoals.setText(totalGS + "-" + totalGC);
        holder.txtAverage.setText("" + totalAverage + "");
        holder.txtPoint.setText("" + totalPoint + "");


    }

    @Override
    public int getItemCount() {
        return (gData.getMatches().size() / 2) + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFlag;
        TextView txtName;
        TextView txtPlayed;
        TextView txtWdl;
        TextView txtGoals;
        TextView txtAverage;
        TextView txtPoint;

        public ViewHolder(View itemView) {
            super(itemView);
            imgFlag = itemView.findViewById(R.id.team_flag);
            txtName = itemView.findViewById(R.id.team_name);
            txtPlayed = itemView.findViewById(R.id.played_match);
            txtWdl = itemView.findViewById(R.id.wdl);
            txtGoals = itemView.findViewById(R.id.goals);
            txtAverage = itemView.findViewById(R.id.average);
            txtPoint = itemView.findViewById(R.id.point);
        }
    }

}
