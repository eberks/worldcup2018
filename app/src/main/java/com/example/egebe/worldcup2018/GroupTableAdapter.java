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
import java.util.Collections;
import java.util.List;


public class GroupTableAdapter extends RecyclerView.Adapter<GroupTableAdapter.ViewHolder> {

    private Group gData;
    private LayoutInflater customInflater;
    private Context context;
    private WorldCupResponse wcrData;

    List<TeamPosition> positioningTeams = new ArrayList<>();

    public GroupTableAdapter(Context context, Group group, WorldCupResponse worldCupResponse) {
        this.context = context;
        this.customInflater = LayoutInflater.from(context);
        this.gData = group;
        this.wcrData = worldCupResponse;

        for (int i = 0; i < getItemCount(); i++) {
            Team currentTeam = gData.getTeamsInGroup().get(i);
            TeamPosition teamPosition = gData.fillTeamPositionObject(currentTeam);
            positioningTeams.add(teamPosition);
            Collections.sort(positioningTeams);
            Collections.reverse(positioningTeams);
        }


    }

    @NonNull
    @Override
    public GroupTableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = customInflater.inflate(R.layout.top_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupTableAdapter.ViewHolder holder, int position) {


        Picasso.with(context).load(positioningTeams.get(position).getPositionedTeam().getFlag()).into(holder.imgFlag);
        holder.txtName.setText(positioningTeams.get(position).getPositionedTeam().getName());
        holder.txtPlayed.setText("" + positioningTeams.get(position).getNumberOfMatch() + "");
        holder.txtWdl.setText(positioningTeams.get(position).getNumberOfWin() + "-" + positioningTeams.get(position).getNumberOfDraw() + "-" + positioningTeams.get(position).getNumberOfLose() + "");
        holder.txtGoals.setText(positioningTeams.get(position).getTotalGoalScored() + "-" + positioningTeams.get(position).getTotalGoalConceded());
        holder.txtAverage.setText("" + positioningTeams.get(position).getTotalAverage() + "");
        holder.txtPoint.setText("" + positioningTeams.get(position).getTotalPoint() + "");


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
