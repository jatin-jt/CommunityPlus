package com.complus.community;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.complus.community.models.EarnEvent;
import com.complus.community.models.RewardEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RewardActivity extends AppCompatActivity {

    TextView rewardTitle, rewardPoints,rewardDesc;
    ImageView rewardpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        String rid = getIntent().getStringExtra("ID");
        rewardTitle = (TextView) findViewById(R.id.reward_title);
        rewardPoints = (TextView) findViewById(R.id.rewards_points);
        rewardDesc = (TextView) findViewById(R.id.reward_desc);
        rewardpic = (ImageView) findViewById(R.id.reward_pic);
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("reward-events").child(rid);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RewardEvent re = dataSnapshot.getValue(RewardEvent.class);
                rewardTitle.setText(re.getTitle());
                rewardPoints.setText(re.getPoints());
                rewardDesc.setText(re.getDesc());
                Glide.with(getApplicationContext()).load(re.getPiclink()).into(rewardpic);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
