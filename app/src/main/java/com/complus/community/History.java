package com.complus.community;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.complus.community.models.EarnEvent;
import com.complus.community.models.RewardEvent;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    private RecyclerView history;

    private ArrayList<RewardEvent> redeemed;
    private ArrayList<EarnEvent> earned;

    private RewardsAdapter redeemed_adapter;
    private EventAdapter earned_adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.history_earned: {
                    return true;
                }
                case R.id.history_redeemed: {
                    return true;
                }
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        history = (RecyclerView)findViewById(R.id.history_list);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
