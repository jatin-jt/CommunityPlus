package com.complus.community;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.complus.community.models.EarnEvent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

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

                    if (current == null || current.isEmpty()) {
                        load_current();
                    }
                    if (adapter_current == null) {
                        adapter_current = new EventAdapter(current, getApplicationContext());
                    }
                    adapter_current.notifyDataSetChanged();
                    list.setAdapter(adapter_current);
                    return true;

                }
                case R.id.navigation_upcoming: {

                    if (future == null || future.isEmpty()) {
                        load_future();
                    }
                    if (adapter_future == null) {
                        adapter_future = new EventAdapter(future, getApplicationContext());
                    }
                    adapter_future.notifyDataSetChanged();
                    list.setAdapter(adapter_future);
                    return true;

                }
                case R.id.navigation_past: {

                    past = new ArrayList<>();
                    DatabaseReference pastref = FirebaseDatabase.getInstance().getReference().child("earn-events");
                    pastref.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Log.d(TAG, "onChildAdded: " + dataSnapshot.getValue(EarnEvent.class).getEnddate());
                            past.add(dataSnapshot.getValue(EarnEvent.class));
                            adapter_past = new EventAdapter(past, getApplicationContext());
                            list.setAdapter(adapter_past);
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

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
        setContentView(R.layout.activity_events);

        list = (RecyclerView) findViewById(R.id.event_recycler_view);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void load_current() {

    }

    private void load_future() {
    }



}
