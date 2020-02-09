package com.example.stopwatchspotify;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<StopWatch> StopWatchList;
    private CutsomStopWatchListAdapter<StopWatch> adapter ;
    private ListView listView ;
    private Context context;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpActivity();
        setUpStopWatchList();
        setUpFloatButton();
        moveToStartedState();
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void moveToStartedState() {
        Intent intent = new Intent(this, BackgroundService.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    private void setUpActivity(){
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        activity = this;
    }

    private void setUpStopWatchList(){
        StopWatchList = new ArrayList<StopWatch>();
        StopWatchList.add(new StopWatch());
        adapter = new CutsomStopWatchListAdapter<StopWatch>(context,activity, (ArrayList<StopWatch>) StopWatchList);
        listView = (ListView)findViewById(R.id.StopWatchListView);
        listView.setAdapter(adapter);
    }

    private void setUpFloatButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StopWatchList.add(new StopWatch());
                adapter.notifyDataSetChanged();
            }
        });
    }

}
