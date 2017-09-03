package com.complus.community;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.complus.community.models.Complaint;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import static java.security.AccessController.getContext;

public class ComplaintActivity extends AppCompatActivity {

    TextView complaintTitle, complaintDesc, complaintDate, complaintCat, complaintThreat;
    ImageView complaintPic;
    Uri uri;
    File localFile = null;

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
            public void onCancelled(DatabaseError databaseError) {

            }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Complaint com = dataSnapshot.getValue(Complaint.class);
                complaintCat.setText(com.getCategory());
                complaintTitle.setText(com.getTitle());
                complaintDesc.setText(com.getDesc());
                complaintDate.setText(com.getDate());
                complaintThreat.setText(com.getThreatLevel());
                if (com.getPicEnabled().equals("true")) {

                    complaintPic.setVisibility(View.VISIBLE);
                    if (uri != null) {
                        Glide.with(getApplicationContext()).load(uri).into(complaintPic);
                    } else {

                        try {
                            localFile = File.createTempFile(com.getId(), "jpg");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        StorageReference ref = FirebaseStorage.getInstance().getReference().child("com"+com.getId());
                        uri = Uri.fromFile(localFile);
                        ref.getFile(localFile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        Glide.with(getApplicationContext()).load(localFile).into(complaintPic);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle failed download
                                // ...
                            }
                        });
                    }
                }
            }
        });

    }
}
