package com.complus.community;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.complus.community.models.EarnEvent;
import com.complus.community.models.RewardEvent;

import java.util.List;

/**
 * Created by Aditya on 9/2/2017.
 */

public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.MyViewHolder> {

    private List<RewardEvent> rewardList;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView reward_name;

        public MyViewHolder(View view) {
            super(view);
            reward_name = (TextView) view.findViewById(R.id.reward_item_name);
        }
    }


    public RewardsAdapter(List<RewardEvent> rewardList, Context context) {
        this.rewardList = rewardList;
        mcontext = context;
    }

    @Override
    public RewardsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rewards_item, parent, false);

        return new RewardsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RewardsAdapter.MyViewHolder holder, final int position) {

        holder.reward_name.setText(rewardList.get(position).getTitle());

    }


    @Override
    public int getItemCount() {
        return rewardList.size();
    }

}


