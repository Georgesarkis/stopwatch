package com.example.stopwatchspotify;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
