package com.complus.community;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.complus.community.models.RewardEvent;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by jatin on 3/9/17.
 */

public class RedeemAdapter extends RecyclerView.Adapter<RedeemAdapter.MyViewHolder> {
    private List<Pair<RewardEvent,String>> redeemList;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView reward_name;
        public TextView date_redeemed;

        public MyViewHolder(View view) {
            super(view);
            reward_name = (TextView) view.findViewById(R.id.redeem_item_name);
            date_redeemed = (TextView) view.findViewById(R.id.redeem_item_date);
        }
    }


    public RedeemAdapter(List<Pair<RewardEvent,String>> redeemList, Context context) {
        this.redeemList = redeemList;
        mcontext = context;
    }

    @Override
    public RedeemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.redeem_item, parent, false);

        return new RedeemAdapter.MyViewHolder(itemView);
    }

    private static final String TAG = "RewardsAdapter";
    @Override
    public void onBindViewHolder(RedeemAdapter.MyViewHolder holder, final int position) {

        holder.reward_name.setText(redeemList.get(position).first.getTitle());
        holder.date_redeemed.setText(redeemList.get(position).second);

    }


    @Override
    public int getItemCount() {
        return redeemList.size();
    }

}
