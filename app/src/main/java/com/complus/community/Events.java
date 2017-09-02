package com.complus.community;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.complus.community.models.EarnEvent;

import java.util.ArrayList;

public class Events extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView list;
    private ArrayList<EarnEvent> current,past,future;
    private EventAdapter adapter_current,adapter_past,adapter_future;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home: {

                    if(current == null || current.isEmpty())
                    {
                        load_current();
                    }
                    if(adapter_current == null)
                    {
                        adapter_current = new EventAdapter(current,getApplicationContext());
                    }
                    adapter_current.notifyDataSetChanged();
                    list.setAdapter(adapter_current);
                    return true;

                }
                case R.id.navigation_dashboard: {

                    if(future == null || future.isEmpty())
                    {
                        load_future();
                    }
                    if(adapter_future == null)
                    {
                        adapter_future = new EventAdapter(future,getApplicationContext());
                    }
                    adapter_future.notifyDataSetChanged();
                    list.setAdapter(adapter_future);
                    return true;

                }
                case R.id.navigation_notifications: {

                    if(past == null || past.isEmpty())
                    {
                        load_past();
                    }
                    if(adapter_past == null)
                    {
                        adapter_past = new EventAdapter(past,getApplicationContext());
                    }
                    adapter_past.notifyDataSetChanged();
                    list.setAdapter(adapter_past);
                    return true;

                }
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        list = (RecyclerView)findViewById(R.id.event_recycler_view);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void load_current(){}

    private void load_future(){}

    private void load_past(){}


}
