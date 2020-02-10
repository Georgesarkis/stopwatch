package com.example.stopwatchspotify.ViewModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.SystemClock;

import com.example.stopwatchspotify.Model.Class.StopWatch;
import com.example.stopwatchspotify.View.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BaseViewModel {
    public List<StopWatch> StopWatchList;
    private MainActivity _activity;
    public List<Handler> handlerList;
    private SharedPreferences sharedPreferences;

    public BaseViewModel(MainActivity activity) {
        _activity = activity;
    }

    public void init() {
        loadData();
    }

    public void addNewStopWatch(){
        StopWatchList.add(new StopWatch());
        handlerList.add(new Handler());
    }

    private void saveData() {
        if (sharedPreferences == null) {
            sharedPreferences = _activity.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(StopWatchList);
        editor.putString("StopWatchList", json);
        editor.apply();
    }

    private void loadData() {
        if (sharedPreferences == null) {
            sharedPreferences = _activity.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        }
        Gson gson = new Gson();
        String json = sharedPreferences.getString("StopWatchList", null);
        Type type = new TypeToken<ArrayList<StopWatch>>() {
        }.getType();
        List<StopWatch> TempStopWatchList = gson.fromJson(json, type);

        if (TempStopWatchList == null) {
            StopWatchList = new ArrayList<StopWatch>();
            handlerList = new ArrayList<Handler>();
            addNewStopWatch();
        }else{
            StopWatchList = TempStopWatchList;
            handlerList = new ArrayList<Handler>();
            for(int i = 0; i< StopWatchList.size();i++){
                handlerList.add(new Handler());
            }
        }

    }

    public void onDestroy() {
        saveData();
    }

}
