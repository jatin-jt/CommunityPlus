package com.complus.community;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.complus.community.models.EarnEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EarnActivity extends AppCompatActivity {

    TextView earnTitle, earnDesc, earnPoints, earnLoc, earnDur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn);

        String eId = getIntent().getStringExtra("ID");

        earnTitle = (TextView) findViewById(R.id.earn_title);
        earnDur = (TextView) findViewById(R.id.earn_duration);
        earnDesc = (TextView) findViewById(R.id.earn_desc);
        earnLoc = (TextView) findViewById(R.id.earn_location);
        earnPoints = (TextView) findViewById(R.id.earn_points);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("earn-events");
        myRef.child(eId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                EarnEvent ee = dataSnapshot.getValue(EarnEvent.class);
                earnTitle.setText(ee.getTitle());
                earnDesc.setText(ee.getDesc());
                earnDur.setText(ee.getStartdate()+"-"+ee.getEnddate());
                earnLoc.setText(ee.getLocation());
                earnPoints.setText(ee.getPoints());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
