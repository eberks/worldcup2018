package com.example.egebe.worldcup2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private LayoutInflater customInflater;
    private Context context;
    List<Group> tData;
    private OnGroupItemClickListener tListener;

    public CustomAdapter(Context context, List<Group> data, OnGroupItemClickListener listener) {
        this.context = context;
        this.customInflater = LayoutInflater.from(context);
        this.tData = data;
        this.tListener = listener;

    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = customInflater.inflate(R.layout.each_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter.ViewHolder holder, final int position) {

        holder.groupName.setText(tData.get(position).getName().toUpperCase());

        holder.topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tListener.onGroupItemClicked(tData.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return tData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView groupName;
        RelativeLayout topLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            groupName = itemView.findViewById(R.id.group_name);
            topLayout = itemView.findViewById(R.id.row_id);


        }
    }

    interface OnGroupItemClickListener {
        void onGroupItemClicked(Group group);
    }
}

