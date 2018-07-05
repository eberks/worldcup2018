package com.example.egebe.worldcup2018;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class KnockoutFragment extends Fragment {

    private Knockout mKnockout;
    private MatchDisplayAdapter mBottomAdapter;
    private MatchDisplayAdapter.OnMatchItemClickListener tempListener;

    public static KnockoutFragment newInstance(Knockout knockOut) {
        KnockoutFragment knockoutFragment = new KnockoutFragment();
        Bundle args = new Bundle();
        args.putSerializable("extra_knockout", knockOut);
        knockoutFragment.setArguments(args);
        return knockoutFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mKnockout = (Knockout) getArguments().getSerializable("extra_knockout");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.viewpager_layout, container, false);

        RecyclerView bottomGroupView = view.findViewById(R.id.knockout_matches);
        TextView tvRoundTitle = view.findViewById(R.id.round_title);
        tvRoundTitle.setText(mKnockout.getName());

        mBottomAdapter = new MatchDisplayAdapter(getContext(), (ArrayList<Match>) mKnockout.getMatches(), tempListener);
        bottomGroupView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bottomGroupView.setAdapter(mBottomAdapter);

        return view;
    }
}
