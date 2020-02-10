package com.example.stopwatchspotify.ViewModel;

import com.example.stopwatchspotify.Model.Class.StopWatch;
import com.example.stopwatchspotify.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class BaseViewModel {
    public List<StopWatch> StopWatchList;
    private MainActivity _activity;

    public BaseViewModel(MainActivity activity) {
        _activity = activity;

    }

    public void init() {
        StopWatchList = new ArrayList<StopWatch>();
        StopWatchList.add(new StopWatch());
    }

    public void addNewStopWatch(){
        StopWatchList.add(new StopWatch());
    }
}
