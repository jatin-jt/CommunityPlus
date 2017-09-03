package com.complus.community;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.complus.community.models.Complaint;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyComplaints extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static final String TAG = "MyComplaints";
    private ArrayList<Complaint> complaints;
    private ArrayList<String> ids;
    DatabaseReference mainRef;
    ComplaintAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complaints);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.complaints_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        mainRef = FirebaseDatabase.getInstance().getReference();
        mainRef.child("users-complaints").child(fUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ids = new ArrayList<String>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ids.add(ds.getKey());
                    Log.d(TAG, "onDataChange: " + ds);
                }
                populateList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    int count;
    int i;

    void populateList() {
        complaints = new ArrayList<>();
        count = ids.size();
        i = 0;
        for(String s:ids) {
            mainRef.child("complaints").child(s).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Complaint comp = dataSnapshot.getValue(Complaint.class);
                    Log.d(TAG, "onDataChange: " + dataSnapshot);
                    complaints.add(comp);
                    if (i == count) {
                        adapter = new ComplaintAdapter(complaints, getApplicationContext());
                        Log.d(TAG, "populateList: " + adapter.getItemCount());
                        recyclerView.setAdapter(adapter);
                        //attach
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            i++;

        }
    }
}
