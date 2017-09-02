package com.complus.community;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.complus.community.models.EarnEvent;
import com.complus.community.models.RewardEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    private RecyclerView history;

    private ArrayList<Pair<RewardEvent,String>> redeemed;
    private ArrayList<EarnEvent> earned;
    private static final String TAG = "History";
    private ArrayList<Pair<String,String> > myList,hylist;

    private RedeemAdapter redeemed_adapter;
    private EventAdapter earned_adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.history_earned: {
                    myList = new ArrayList<>();
                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("user-earn-events");
                    myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                                        Log.d(TAG, "onDataChange: "+ds);
                                        myList.add(new Pair<String, String>(ds.getKey(),ds.getValue().toString()));
                                    }
                                    populateEvents();
                                    //Log.d(TAG, "onDataChange: " + earned.size());

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                    return true;
                }
                case R.id.history_redeemed: {
                    hylist = new ArrayList<>();
                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("user-redeem-events");
                    myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                                        Log.d(TAG, "onDataChange: "+ds);
                                        hylist.add(new Pair<>(ds.getKey(),ds.getValue().toString()));
                                    }
                                    populateRedeemedEvents();
                                    Log.d(TAG, "onDataChange: "+redeemed.size());
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
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
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        history.setLayoutManager(mLayoutManager);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    long k,i;

    void populateRedeemedEvents(){
        redeemed = new ArrayList<>();
        k = hylist.size();
        i=0;
        for(final Pair<String,String> pss:hylist){
            i++;
            String eventID = pss.first;
            FirebaseDatabase.getInstance().getReference().child("reward-events").child(eventID)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            RewardEvent re = dataSnapshot.getValue(RewardEvent.class);
                            redeemed.add(new Pair<>(re,pss.second));
                            if(i==k){
                                redeemed_adapter = new RedeemAdapter(redeemed,getApplicationContext());
                                history.setAdapter(redeemed_adapter);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }
    }

    void populateEvents(){
        earned = new ArrayList<>();
        k = myList.size();
        i=0;
        for(final Pair<String,String> pss:myList){
            i++;
            String eventID = pss.first;
            FirebaseDatabase.getInstance().getReference().child("earn-events").child(eventID)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            EarnEvent ee = dataSnapshot.getValue(EarnEvent.class);
                            ee.setStartdate(pss.second);
                            ee.setEnddate(pss.second);
                            earned.add(ee);
                            if(i==k){
                                earned_adapter = new EventAdapter(earned,getApplication());
                                history.setAdapter(earned_adapter);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }


    }


}
