package com.example.stopwatchspotify.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.stopwatchspotify.Model.Class.StopWatch;
import com.example.stopwatchspotify.Model.Helper.ForegroundService;
import com.example.stopwatchspotify.View.adapter.CutsomStopWatchListAdapter;
import com.example.stopwatchspotify.R;
import com.example.stopwatchspotify.ViewModel.BaseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar mTopToolbar;
    private CutsomStopWatchListAdapter<StopWatch> adapter ;
    private ListView listView ;
    private Context context;
    private MainActivity activity;
    private BaseViewModel baseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseViewModel = new BaseViewModel(this);
        setUpActivity();
        setUpStopWatchList();
        setUpFloatButton();
        moveToStartedState();
    }

    @Override
    protected void onDestroy() {
        baseViewModel.onDestroy();
        super.onDestroy();
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void moveToStartedState() {
        Intent intent = new Intent(this, ForegroundService.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    private void setUpActivity(){
        setContentView(R.layout.activity_main);
        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        context = this;
        activity = this;
    }

    private void setUpStopWatchList(){
        baseViewModel.init();
        adapter = new CutsomStopWatchListAdapter<StopWatch>(context,activity, (ArrayList<StopWatch>) baseViewModel.StopWatchList,baseViewModel);
        listView = (ListView)findViewById(R.id.StopWatchListView);
        listView.setAdapter(adapter);
    }

    private void setUpFloatButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseViewModel.addNewStopWatch();
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            stopService(new Intent(this,ForegroundService.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
