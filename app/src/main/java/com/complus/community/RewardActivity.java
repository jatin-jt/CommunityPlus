package com.complus.community;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RewardActivity extends AppCompatActivity {

    TextView rewardTitle, rewardPoints;
    ImageView rewardpic;
    Button redeem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        redeem = (Button)findViewById(R.id.reward_redeem_button);
        rewardTitle = (TextView) findViewById(R.id.reward_title);
        rewardPoints = (TextView) findViewById(R.id.rewards_points);
        rewardpic = (ImageView) findViewById(R.id.reward_pic);

        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }
}
