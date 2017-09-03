package com.complus.community;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.complus.community.models.Complaint;
import com.complus.community.models.EarnEvent;

import java.util.List;

/**
 * Created by Aditya on 9/3/2017.
 */

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.MyViewHolder> {

    private List<Complaint> complaintList;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView complaint_title;
        public TextView complaint_category;
        public TextView complaint_threat;
        public TextView complaint_date;

        public MyViewHolder(View view) {
            super(view);
            complaint_title = (TextView) view.findViewById(R.id.complaint_title);
            complaint_category = (TextView) view.findViewById(R.id.complaint_category);
            complaint_threat = (TextView) view.findViewById(R.id.complaint_threat);
            complaint_date = (TextView) view.findViewById(R.id.complaint_date);
        }
    }


    public ComplaintAdapter(List<Complaint> complaintList, Context context) {
        this.complaintList = complaintList;
        mcontext = context;
    }

    @Override
    public ComplaintAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaint_item, parent, false);

        return new ComplaintAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ComplaintAdapter.MyViewHolder holder, final int position) {

        holder.complaint_title.setText(complaintList.get(position).getTitle());
        holder.complaint_category.setText(complaintList.get(position).getCategory());
        holder.complaint_threat.setText(complaintList.get(position).getThreatLevel());
        holder.complaint_date.setText(complaintList.get(position).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,ComplaintActivity.class);
                intent.putExtra("ID",complaintList.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return complaintList.size();
    }



}