package com.complus.community;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.complus.community.models.EarnEvent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Events extends AppCompatActivity {

    private TextView mTextMessage;
    private static final String TAG = "Events";
    private RecyclerView list;
    private ArrayList<EarnEvent> current, past, future;
    private EventAdapter adapter_current, adapter_past, adapter_future;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_ongoing: {
                    current = new ArrayList<>();
                    Date newDate = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    final String date = sdf.format(newDate);
                    Query curref = FirebaseDatabase.getInstance().getReference().child("earn-events").orderByChild("startdate");
                    curref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    EarnEvent haha = ds.getValue(EarnEvent.class);
                                    if (haha.getStartdate().compareTo(date) > 0) {
                                        break;
                                    }
                                    if(haha.getEnddate().compareTo(date)<0){
                                        continue;
                                    }
                                    Log.d(TAG, "onDataChange: " + haha.getEnddate());
                                    current.add(haha);
                                }
                                adapter_current = new EventAdapter(current, getApplicationContext());
                                list.setAdapter(adapter_current);
                            }
                        }

                    });

                    return true;


                }
                case R.id.navigation_upcoming: {

                    future = new ArrayList<>();
                    Date newDate = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    final String date = sdf.format(newDate);
                    Query futref = FirebaseDatabase.getInstance().getReference().child("earn-events").orderByChild("startdate");
                    futref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    EarnEvent haha = ds.getValue(EarnEvent.class);
                                    if (haha.getStartdate().compareTo(date) <= 0) {
                                        continue;
                                    }
                                    Log.d(TAG, "onDataChange: " + haha.getEnddate());
                                    future.add(haha);
                                }
                            }
                            adapter_future = new EventAdapter(future, getApplicationContext());
                            list.setAdapter(adapter_future);
                        }

                    });

                    return true;

                }
                case R.id.navigation_past: {

                    past = new ArrayList<>();
                    Date newDate = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    final String date = sdf.format(newDate);
                    Query pastref = FirebaseDatabase.getInstance().getReference().child("earn-events").orderByChild("enddate");
                    pastref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    EarnEvent haha = ds.getValue(EarnEvent.class);
                                    if (haha.getEnddate().compareTo(date) >= 0) {
                                        break;
                                    }
                                    Log.d(TAG, "onDataChange: " + haha.getEnddate());
                                    past.add(haha);
                                }
                                adapter_past = new EventAdapter(past, getApplicationContext());
                                list.setAdapter(adapter_past);
                            }
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
        setContentView(R.layout.activity_events);

        list = (RecyclerView) findViewById(R.id.event_recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(mLayoutManager);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        View view = navigation.findViewById(R.id.navigation_ongoing);
        view.performClick();
    }

}
