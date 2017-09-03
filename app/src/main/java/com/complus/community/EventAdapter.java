package com.complus.community;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.complus.community.models.EarnEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private static final String TAG = "EventAdapter";
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.event_name.setText(eventList.get(position).getTitle());
        holder.event_location.setText(eventList.get(position).getLocation());

        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat fmt2 = new SimpleDateFormat("MMM d, ''yy");
        String frDate = "";
        try {
            Date date = fmt.parse(eventList.get(position).getStartdate().toString());
            frDate = fmt2.format(date);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        String frDate2 = "";
        try {
            Date date2 = fmt.parse(eventList.get(position).getEnddate().toString());
            frDate2 = fmt2.format(date2);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }



        if(eventList.get(position).getStartdate().equals(eventList.get(position).getEnddate())){
            holder.event_date.setText(frDate);
        }
        else {
            holder.event_date.setText(frDate + " - " + frDate2);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,EarnActivity.class);
                intent.putExtra("ID",eventList.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });
        Log.d(TAG, "onBindViewHolder: "+ eventList.get(position).getEnddate());
    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }



}

