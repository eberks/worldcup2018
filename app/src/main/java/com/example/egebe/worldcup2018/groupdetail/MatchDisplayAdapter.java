package com.example.egebe.worldcup2018.groupdetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.egebe.worldcup2018.FullMatchObject;
import com.example.egebe.worldcup2018.Match;
import com.example.egebe.worldcup2018.R;

import java.util.ArrayList;
import java.util.List;

public class MatchDisplayAdapter extends RecyclerView.Adapter<MatchDisplayAdapter.ViewHolder> {

    private LayoutInflater customInflater;
    private Context context;
    private ArrayList<Match> listMatch;
    private MatchDisplayAdapter.OnMatchItemClickListener matchItemClickListener;
    private List<FullMatchObject> fullMatchObjectList = new ArrayList<>();

    @NonNull
    @Override
    public MatchDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = customInflater.inflate(R.layout.match_row, parent, false);
        return new ViewHolder(view);
    }


    public MatchDisplayAdapter(Context context, ArrayList<Match> listMatch, MatchDisplayAdapter.OnMatchItemClickListener listener) {
        this.context = context;
        this.listMatch = listMatch;
        this.customInflater = LayoutInflater.from(this.context);
        this.matchItemClickListener = listener;

        for (int i = 0; i < getItemCount(); i++) {
            FullMatchObject currentMatch = listMatch.get(i).getMatchInformations(listMatch);
            fullMatchObjectList.add(currentMatch);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull MatchDisplayAdapter.ViewHolder holder, int position) {


        holder.homeTeam.setText(fullMatchObjectList.get(position).getHomeTeamName() + "");
        holder.awayTeam.setText(fullMatchObjectList.get(position).getAwayTeamName() + "");
        holder.homeScore.setText(fullMatchObjectList.get(position).getHomeTeamScore() + "");
        holder.awayScore.setText(fullMatchObjectList.get(position).getAwayTeamScore() + "");

        holder.matchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchItemClickListener.onMatchItemClicked(listMatch.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMatch.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView homeTeam;
        TextView homeScore;
        TextView awayScore;
        TextView awayTeam;
        LinearLayout matchLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            homeTeam = itemView.findViewById(R.id.home_team);
            homeScore = itemView.findViewById(R.id.home_score);
            awayScore = itemView.findViewById(R.id.away_score);
            awayTeam = itemView.findViewById(R.id.away_team);
            matchLayout = itemView.findViewById(R.id.match_row);
        }
    }

    public interface OnMatchItemClickListener {
        void onMatchItemClicked(Match match);
    }
}
