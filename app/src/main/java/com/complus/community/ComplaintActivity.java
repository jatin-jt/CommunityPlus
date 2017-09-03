package com.complus.community;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.complus.community.models.Complaint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ComplaintActivity extends AppCompatActivity {

    TextView complaintTitle, complaintDesc, complaintDate, complaintCat, complaintThreat;
    ImageView complaintPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        String id = getIntent().getStringExtra("ID");
        complaintTitle = (TextView) findViewById(R.id.complaint_title);
        complaintCat = (TextView) findViewById(R.id.complaint_cat);
        complaintDesc = (TextView) findViewById(R.id.complaint_desc);
        complaintDate = (TextView) findViewById(R.id.complaint_date);
        complaintThreat = (TextView) findViewById(R.id.complaint_threat);

        complaintPic = (ImageView) findViewById(R.id.complaint_pic);
        FirebaseDatabase.getInstance().getReference().child("complaints").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Complaint com = dataSnapshot.getValue(Complaint.class);
                complaintCat.setText(com.getCategory());
                complaintTitle.setText(com.getTitle());
                complaintDesc.setText(com.getDesc());
                complaintDate.setText(com.getDate());
                complaintThreat.setText(com.getThreatLevel());
//                if(com.getPicEnabled().equals("true")){
//                    Glide.with(getApplicationContext()).load()
//                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
