package com.complus.community;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.complus.community.models.LeaderboardPerson;
import com.complus.community.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard extends AppCompatActivity {

    private RecyclerView leaderboard;
    private LeaderboardAdapter adapter;
    User user;
    private ArrayList<LeaderboardPerson> overall,local;
    private static final String TAG = "Leaderboard";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.leaderboard_overall: {
                    Query globalScoreRef = FirebaseDatabase.getInstance().
                            getReference().child("user-points").orderByChild("score");
                    overall = new ArrayList<>();
                    globalScoreRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long k = dataSnapshot.getChildrenCount(),counter = 0;
                            Log.d(TAG, "onDataChange: "+k);
                            for(DataSnapshot ds:dataSnapshot.getChildren()){
                                counter++;
                                overall.add(new LeaderboardPerson(String.valueOf(counter)
                                        ,ds.child("name").getValue().toString()
                                        ,ds.child("score").getValue().toString()));
                            }
                            Collections.sort(overall,new Comparator<LeaderboardPerson>() {
                                @Override
                                public int compare(LeaderboardPerson s1, LeaderboardPerson s2) {
                                    if (Integer.parseInt(s1.getPoints()) < Integer.parseInt(s2.getPoints()))
                                        return 1;
                                    else if(Integer.parseInt(s1.getPoints()) > Integer.parseInt(s2.getPoints()))
                                        return -1;
                                    else
                                        return 0;
                                }
                            });
                            for(int i = 0;i<overall.size();++i){
                                overall.get(i).setRank(String.valueOf(i+1));
                            }
                            adapter = new LeaderboardAdapter(overall,getApplicationContext());
                            leaderboard.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    return true;
                }
                case R.id.leaderboard_local: {
                    FirebaseDatabase.getInstance().getReference().child("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    user = dataSnapshot.getValue(User.class);
                                    doThis();
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
        setContentView(R.layout.activity_leaderboard);

        leaderboard = (RecyclerView)findViewById(R.id.leaderboard_list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        leaderboard.setLayoutManager(mLayoutManager);

        FirebaseDatabase.getInstance().getReference().child("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        user = dataSnapshot.getValue(User.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        View view = navigation.findViewById(R.id.leaderboard_overall);
        view.performClick();
    }
    private void doThis(){
        Query localScoreRef = FirebaseDatabase.getInstance().
                getReference().child("local-user-points").child(user.getPincode()).orderByChild("score");
        local = new ArrayList<>();
        localScoreRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long k = dataSnapshot.getChildrenCount(),counter = 0;
                Log.d(TAG, "onDataChange: "+k);
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    counter++;
                    local.add(new LeaderboardPerson(String.valueOf(counter)
                            ,ds.child("name").getValue().toString()
                            ,ds.child("score").getValue().toString()));
                }



                adapter = new LeaderboardAdapter(local,getApplicationContext());
                leaderboard.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
