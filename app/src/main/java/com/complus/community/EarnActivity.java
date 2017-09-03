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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

                SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat fmt2 = new SimpleDateFormat("MMM d, ''yy");
                String frDate = "";
                try {
                    Date date = fmt.parse(ee.getStartdate().toString());
                    frDate = fmt2.format(date);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }
                String frDate2 = "";
                try {
                    Date date2 = fmt.parse(ee.getEnddate().toString());
                    frDate2 = fmt2.format(date2);
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }

                earnDur.setText(frDate+" - "+frDate2);
                earnLoc.setText(ee.getLocation());
                earnPoints.setText(ee.getPoints());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
