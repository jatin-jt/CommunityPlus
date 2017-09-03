package com.complus.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.complus.community.models.EarnEvent;
import com.complus.community.models.RewardEvent;
import com.complus.community.models.User;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    User mUser;
    TextView textView,tvScore;
    ImageView imageView;

    Button btnClaimPoints;

    private RecyclerView history;

    private ArrayList<RewardEvent> redeemed;
    private ArrayList<EarnEvent> earned;

    private RewardsAdapter redeemed_adapter;
    private EventAdapter earned_adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.history_earned: {
                    return true;
                }
                case R.id.history_redeemed: {
                    return true;
                }
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvScore = (TextView) findViewById(R.id.tv_point);
        history = (RecyclerView)findViewById(R.id.lv_recents);

        btnClaimPoints = (Button) findViewById(R.id.btn_claim_points);

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    startActivity(new Intent(HomeActivity.this, EditProfileActivity.class));
                }
                else {
                    tvScore.setText(dataSnapshot.getValue(User.class).getPoints());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        btnClaimPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,ShowQRCode.class));
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigation.findViewById(R.id.history_earned);
        view.performClick();

        View header = navigationView.getHeaderView(0);


        imageView = (ImageView) header.findViewById(R.id.imageView);
        textView = (TextView) header.findViewById(R.id.textView);
        Glide.with(this).load(user.getPhotoUrl().toString()).into(imageView);
        textView.setText("Hello, " + user.getDisplayName());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation_events view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {

        } else if (id == R.id.nav_events) {

            Intent intent = new Intent(this,Events.class);
            startActivity(intent);

        } else if (id == R.id.nav_history) {

            Intent intent = new Intent(this,History.class);
            startActivity(intent);

        } else if (id == R.id.nav_addcomplaint) {
            startActivity(new Intent(this,AddComplaint.class));
        } else if (id == R.id.nav_leaderboard) {
            startActivity(new Intent(this,Leaderboard.class));

        } else if (id == R.id.nav_rewards) {

            Intent intent = new Intent(this,Rewards.class);
            startActivity(intent);

        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("userid", user.getUid());

            startActivity(intent);
        }
        else if(id == R.id.nav_signout)
        {
            AuthUI.getInstance()
                    .signOut(HomeActivity.this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            finish();
                            startActivity(new Intent(HomeActivity.this, StartActivity.class));
                        }
                    });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class RecentAdapter extends BaseAdapter {
        class Holder {
            TextView name;
            TextView location;
            TextView date;
        }

        ArrayList<EarnEvent> mList;

        public RecentAdapter(ArrayList<EarnEvent> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater li = LayoutInflater.from(HomeActivity.this);
            Holder holder = new Holder();
            if (convertView == null) {
                convertView = li.inflate(R.layout.events_item, null);

                holder.name = (TextView) convertView.findViewById(R.id.event_item_date);
                holder.location = (TextView) convertView.findViewById(R.id.event_item_location);
                holder.date = (TextView) convertView.findViewById(R.id.event_item_date);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }



            return convertView;
        }

    }



}
