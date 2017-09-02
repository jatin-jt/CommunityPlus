package com.complus.community;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RewardActivity extends AppCompatActivity {

    TextView rewardTitle, rewardPoints;
    ImageView rewardpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        rewardTitle = (TextView) findViewById(R.id.reward_title);
        rewardPoints = (TextView) findViewById(R.id.rewards_points);
        rewardpic = (ImageView) findViewById(R.id.reward_pic);

    }
}
