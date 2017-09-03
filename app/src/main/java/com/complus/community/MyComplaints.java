package com.complus.community;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.complus.community.models.Complaint;

import java.util.ArrayList;

public class MyComplaints extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Complaint> complaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complaints);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.complaints_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
