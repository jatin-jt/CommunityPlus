package com.complus.community;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.complus.community.models.EarnEvent;

import java.util.List;

/**
 * Created by Aditya on 9/2/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private List<EarnEvent> eventList;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView event_name;
        public TextView event_location;
        public TextView event_date;

        public MyViewHolder(View view) {
            super(view);
            event_name = (TextView) view.findViewById(R.id.event_item_name);
            event_location = (TextView) view.findViewById(R.id.event_item_location);
            event_date = (TextView) view.findViewById(R.id.event_item_date);
        }
    }


    public EventAdapter(List<EarnEvent> eventList, Context context) {
        this.eventList = eventList;
        mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.event_name.setText(eventList.get(position).getTitle());
        holder.event_location.setText(eventList.get(position).getLocation());
        holder.event_date.setText(eventList.get(position).getStartDate() + " to " + eventList.get(position).getEndDate());

    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }



}

