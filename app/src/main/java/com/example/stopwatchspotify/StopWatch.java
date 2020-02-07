package com.example.stopwatchspotify;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

public class StopWatch {
    public enum StopWatchStatus {
        RUNNING,
        PAUSED,
        STOPPED

    }
    private int hours;
    private int Minutes;
    private int Seconds;
    private int MilliSeconds ;
    private StopWatchStatus Status;
    private long StartTime;
    private long UpdateTime;
    private long TimeBuff;
    private long MillisecondTime;
    private Handler handler;
    private TextView TextView;
    public StopWatch(){
        this.MillisecondTime = 0;
        this.hours = 0;
        this.Minutes = 0;
        this.Seconds = 0;
        this.MilliSeconds = 0;
        this.StartTime =0;
        this.UpdateTime = 0;
        this.TimeBuff = 0;
        this.Status = StopWatchStatus.STOPPED;
    }

    public int getMilliSeconds() {
        return MilliSeconds;
    }
    public int getSeconds() {
        return Seconds;
    }
    public int getMinutes() {
        return Minutes;
    }
    public StopWatchStatus getStatus(){
        return Status;
    }
    public void setMinutes(int min){
        this.Minutes = min;
    }
    public void setSeconds(int sec){
        this.Seconds = sec;
    }
    public void setMilliSeconds(int milSec){
        this.MilliSeconds = milSec;
    }
    public void setStatus(StopWatchStatus status){
        this.Status = status;
    }

    public long getStartTime(){
        return this.StartTime;
    }

    public void setStartTime(long StartTime){
        this.StartTime = StartTime;
    }



    public void runTimer(final TextView timeView){
        this.TextView = timeView;
        if(handler == null){
            handler = new Handler();
        }
        if(this.getStatus() == StopWatchStatus.RUNNING){
            this.StartTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
        }
        else if(this.getStatus() == StopWatchStatus.PAUSED){
            TimeBuff += MillisecondTime;
            handler.removeCallbacks(runnable);
        }
        else{
            this.resetValues();
        }
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            TextView.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

    public void resetValues(){
        MillisecondTime = 0L ;
        StartTime = 0L ;
        TimeBuff = 0L ;
        UpdateTime = 0L ;
        Seconds = 0 ;
        Minutes = 0 ;
        MilliSeconds = 0 ;
        TextView.setText("0:00:00:000");
    }

}
