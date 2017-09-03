package com.complus.community;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
        public ImageView reward_pic;

        public MyViewHolder(View view) {
            super(view);
            reward_name = (TextView) view.findViewById(R.id.reward_item_name);
            reward_pic = (ImageView) view.findViewById(R.id.reward_item_pic);
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

    private static final String TAG = "RewardsAdapter";
    @Override
    public void onBindViewHolder(RewardsAdapter.MyViewHolder holder, final int position) {

        holder.reward_name.setText(rewardList.get(position).getTitle());
        Log.d(TAG, "onBindViewHolder: "+rewardList.get(position).getPiclink());
        Glide.with(mcontext).load(rewardList.get(position).getPiclink())
                .into(holder.reward_pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,RewardActivity.class);
                intent.putExtra("ID",rewardList.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return rewardList.size();
    }

}


