package com.complus.community;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.complus.community.models.EarnEvent;
import com.complus.community.models.LeaderboardPerson;

import java.util.List;

/**
 * Created by Aditya on 9/2/2017.
 */

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.MyViewHolder> {

    private List<LeaderboardPerson> personList;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView person_name;
        public TextView person_points;
        public TextView person_rank;

        public MyViewHolder(View view) {
            super(view);
            person_name = (TextView) view.findViewById(R.id.leaderboard_item_name);
            person_points = (TextView) view.findViewById(R.id.leaderboard_item_points);
            person_rank = (TextView) view.findViewById(R.id.leaderboard_item_rank);
        }
    }


    public LeaderboardAdapter(List<LeaderboardPerson> personList, Context context) {
        this.personList = personList;
        mcontext = context;
    }

    @Override
    public LeaderboardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leaderboard_item, parent, false);

        return new LeaderboardAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(LeaderboardAdapter.MyViewHolder holder, final int position) {

        holder.person_name.setText(personList.get(position).getName());
        holder.person_points.setText(personList.get(position).getPoints());
        holder.person_rank.setText(personList.get(position).getRank());
    }


    @Override
    public int getItemCount() {
        return personList.size();
    }



}