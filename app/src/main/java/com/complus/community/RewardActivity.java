package com.complus.community;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.complus.community.models.EarnEvent;
import com.complus.community.models.RewardEvent;
import com.complus.community.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RewardActivity extends AppCompatActivity {

    private static final String TAG = "RewardActivity";
    TextView rewardTitle, rewardPoints, rewardDesc;
    ImageView rewardpic;
    Button redeem;
    String score, newScore;
    RewardEvent re;
    User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        redeem = (Button) findViewById(R.id.reward_redeem_button);
        String rid = getIntent().getStringExtra("ID");
        Log.d(TAG, "onCreate: " + rid);

        rewardTitle = (TextView) findViewById(R.id.reward_title);
        rewardPoints = (TextView) findViewById(R.id.reward_points_detail);
        rewardDesc = (TextView) findViewById(R.id.reward_desc);
        rewardpic = (ImageView) findViewById(R.id.reward_pic);

        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deductPoints();
            }
        });


        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("reward-events").child(rid);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                re = dataSnapshot.getValue(RewardEvent.class);
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
    Date newDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    final String date = sdf.format(newDate);
    private void deductPoints() {
        final DatabaseReference mainRef = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        mainRef.child("users").child(fUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUser = dataSnapshot.getValue(User.class);
                score = mUser.getPoints();
                newScore = String.valueOf(Integer.parseInt(score) - Integer.parseInt(rewardPoints.getText().toString()));
                mainRef.child("users").child(fUser.getUid()).child("points").setValue(newScore).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mainRef.child("user-points").child(fUser.getUid())
                                .child("score").setValue(newScore).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                mainRef.child("user-redeem-events").child(fUser.getUid())//TODO
                                        .child(re.getId()).setValue(date).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        mainRef.child("local-user-points").child(mUser.getPincode().toString())
                                                .child(fUser.getUid()).child("score").setValue(newScore).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                finish();
                                                startActivity(new Intent(RewardActivity.this,Rewards.class));
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
