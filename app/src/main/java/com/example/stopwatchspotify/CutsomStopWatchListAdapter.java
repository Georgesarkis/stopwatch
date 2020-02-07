package com.example.stopwatchspotify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CutsomStopWatchListAdapter<S> extends ArrayAdapter<StopWatch> {
    private Context mContext;
    private MainActivity _owner;
    private List<StopWatch> StopwatchList;

    public CutsomStopWatchListAdapter(Context context, MainActivity Owner, ArrayList<StopWatch> list) {
        super(context, 0, list);
        mContext = context;
        StopwatchList = list;
        _owner = Owner;
    }

    @Override
    public int getCount() {
        return StopwatchList.size();
    }

    @Override
    public StopWatch getItem(int position) {
        return this.StopwatchList.get(position);
    }

    @SuppressLint({"RestrictedApi", "ResourceAsColor"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listView = convertView;

        if (listView == null)
            listView = LayoutInflater.from(mContext).inflate(R.layout.row_list, parent, false);

        final TextView TimeStampTextView = (TextView)listView.findViewById(R.id.TimeStampTextView);

        final FloatingActionButton ResetButton = listView.findViewById(R.id.ResetButton);
        final FloatingActionButton StartResumeButton = listView.findViewById(R.id.StartResumeButton);
        ImageView deleteBtn = listView.findViewById(R.id.Delete);

        StopWatch StopWatch = getItem(position);

        if(StopWatch.getStatus() == com.example.stopwatchspotify.StopWatch.StopWatchStatus.RUNNING){
            ResetButton.setVisibility(View.INVISIBLE);
            StartResumeButton.setImageResource(android.R.drawable.ic_media_pause);
        }
        else if(StopWatch.getStatus() == com.example.stopwatchspotify.StopWatch.StopWatchStatus.STOPPED){
            ResetButton.setVisibility(View.INVISIBLE);
            StartResumeButton.setImageResource(android.R.drawable.ic_media_play);
        }
        else{
            ResetButton.setVisibility(View.VISIBLE);
            StartResumeButton.setImageResource(android.R.drawable.ic_media_play);
        }

        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetButton.setVisibility(View.INVISIBLE);
                StopWatch StopWatch = getItem(position);
                StopWatch.setStatus(com.example.stopwatchspotify.StopWatch.StopWatchStatus.STOPPED);
                StopWatch.resetValues(TimeStampTextView);
            }
        });

        StartResumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StopWatch StopWatch = getItem(position);
                if(StopWatch.getStatus() == com.example.stopwatchspotify.StopWatch.StopWatchStatus.RUNNING){
                    StopWatch.setStatus(com.example.stopwatchspotify.StopWatch.StopWatchStatus.PAUSED);
                    StopWatch.runTimer(TimeStampTextView);
                    ResetButton.setVisibility(View.VISIBLE);
                    StartResumeButton.setImageResource(android.R.drawable.ic_media_play);
                }else{
                    StopWatch.setStatus(com.example.stopwatchspotify.StopWatch.StopWatchStatus.RUNNING);
                    StopWatch.runTimer(TimeStampTextView);
                    ResetButton.setVisibility(View.INVISIBLE);
                    StartResumeButton.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StopwatchList.remove(position);
                notifyDataSetChanged();
            }
        });

        return listView;
    }


}
