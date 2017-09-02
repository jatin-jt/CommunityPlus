package com.complus.community;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.complus.community.models.RewardEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Rewards extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tpoints;

    private int points;

    private ArrayList<RewardEvent> list;
    private RewardsAdapter mAdapter;

    private static final String TAG = "Rewards";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = new ArrayList<>();

        tpoints = (TextView)findViewById(R.id.rewards_points);

        recyclerView = (RecyclerView)findViewById(R.id.rewards_list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);

        DatabaseReference rewardRef = FirebaseDatabase.getInstance().getReference().child("reward-events");
        rewardRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: "+ds);
                    list.add(ds.getValue(RewardEvent.class));
                }
                mAdapter = new RewardsAdapter(list,getApplicationContext());
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
