package com.complus.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.complus.community.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.complus.community.R.id.fab;

public class ProfileActivity extends AppCompatActivity {

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("users");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FloatingActionButton fabEdit;

    TextView tvEmailItem;
    TextView tvLocation;
    TextView tvPoints;
    TextView tvContact;
    ImageView ivProfilePic;
    CollapsingToolbarLayout collapsingToolbarLayout;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id = getIntent().getStringExtra("userid");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ivProfilePic = (ImageView) findViewById(R.id.iv_profile_pic);
        tvEmailItem = (TextView) findViewById(R.id.tv_email_item);
        tvContact = (TextView) findViewById(R.id.tv_contact_item);
        tvPoints = (TextView) findViewById(R.id.tv_point_item);
        tvLocation = (TextView) findViewById(R.id.tv_location);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        myRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User mUser = dataSnapshot.getValue(User.class);
                tvEmailItem.setText(user.getEmail());
                tvLocation.setText(mUser.getAddress());
                tvPoints.setText(mUser.getPoints());
                collapsingToolbarLayout.setTitle(mUser.getName());
                tvContact.setText(mUser.getContact());

                Glide
                        .with(ProfileActivity.this)
                        .load(user.getPhotoUrl())
                        .placeholder(R.mipmap.ic_launcher)
                        .crossFade()
                        .centerCrop()
                        .into(ivProfilePic);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        fabEdit = (FloatingActionButton) findViewById(R.id.fab_edit);
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
