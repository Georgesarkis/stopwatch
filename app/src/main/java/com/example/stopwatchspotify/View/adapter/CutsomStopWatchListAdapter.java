package com.example.stopwatchspotify.View.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stopwatchspotify.Model.Class.StopWatch;
import com.example.stopwatchspotify.R;
import com.example.stopwatchspotify.View.MainActivity;
import com.example.stopwatchspotify.ViewModel.BaseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CutsomStopWatchListAdapter<S> extends ArrayAdapter<StopWatch> {
    private Context mContext;
    private MainActivity owner;
    private List<StopWatch> StopwatchList;
    private BaseViewModel BaseViewModel;

    public CutsomStopWatchListAdapter(Context context, MainActivity Owner, ArrayList<StopWatch> list, BaseViewModel baseViewModel) {
        super(context, 0, list);
        mContext = context;
        StopwatchList = list;
        BaseViewModel = baseViewModel;
        owner = Owner;
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

        if (listView != null){
           return listView;
        }
        listView = LayoutInflater.from(mContext).inflate(R.layout.row_list, parent, false);
        final TextView TimeStampTextView = (TextView)listView.findViewById(R.id.TimeStampTextView);
        TimeStampTextView.setText(mContext.getString(R.string.ZeroTimeValue));

        final FloatingActionButton ResetButton = listView.findViewById(R.id.ResetButton);
        final FloatingActionButton StartResumeButton = listView.findViewById(R.id.StartResumeButton);
        ImageView deleteBtn = convertView.findViewById(R.id.Delete);

        StopWatch StopWatch = getItem(position);

        if(StopWatch.getStatus() == com.example.stopwatchspotify.Model.Class.StopWatch.StopWatchStatus.RUNNING || StopWatch.getStatus() == com.example.stopwatchspotify.Model.Class.StopWatch.StopWatchStatus.NEWLYFETCHED ){
            ResetButton.setVisibility(View.INVISIBLE);
            StopWatch.runTimer(TimeStampTextView,BaseViewModel.handlerList.get(position));
            StartResumeButton.setImageResource(android.R.drawable.ic_media_pause);
        }
        else if(StopWatch.getStatus() == com.example.stopwatchspotify.Model.Class.StopWatch.StopWatchStatus.STOPPED){
            ResetButton.setVisibility(View.INVISIBLE);
            StartResumeButton.setImageResource(android.R.drawable.ic_media_play);
        }
        else{
            ResetButton.setVisibility(View.VISIBLE);
            //StopWatch.runTimer(TimeStampTextView,BaseViewModel.handlerList.get(position));
            StopWatch.setTextToTextView(TimeStampTextView);
            StartResumeButton.setImageResource(android.R.drawable.ic_media_play);
        }

        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetButton.setVisibility(View.INVISIBLE);
                StopWatch StopWatch = getItem(position);
                StopWatch.setStatus(com.example.stopwatchspotify.Model.Class.StopWatch.StopWatchStatus.STOPPED);
                StopWatch.resetValues();
                TimeStampTextView.setText(mContext.getString(R.string.ZeroTimeValue));
            }
        });

        StartResumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StopWatch StopWatch = getItem(position);
                if(StopWatch.getStatus() == com.example.stopwatchspotify.Model.Class.StopWatch.StopWatchStatus.RUNNING){
                    StopWatch.setStatus(com.example.stopwatchspotify.Model.Class.StopWatch.StopWatchStatus.PAUSED);
                    StopWatch.runTimer(TimeStampTextView,BaseViewModel.handlerList.get(position));
                    ResetButton.setVisibility(View.VISIBLE);
                    StartResumeButton.setImageResource(android.R.drawable.ic_media_play);
                }else{
                    StopWatch.setStatus(com.example.stopwatchspotify.Model.Class.StopWatch.StopWatchStatus.RUNNING);
                    StopWatch.runTimer(TimeStampTextView,BaseViewModel.handlerList.get(position));
                    ResetButton.setVisibility(View.INVISIBLE);
                    StartResumeButton.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });



        return listView;
    }


}
