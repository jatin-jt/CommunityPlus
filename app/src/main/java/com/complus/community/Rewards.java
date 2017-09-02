package com.complus.community;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.complus.community.models.RewardEvent;

import java.util.ArrayList;

public class Rewards extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tpoints;

    private int points;

    private ArrayList<RewardEvent> list;
    private RewardsAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tpoints = (TextView)findViewById(R.id.rewards_points);

        recyclerView = (RecyclerView)findViewById(R.id.rewards_list);
        mAdapter = new RewardsAdapter(list,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
