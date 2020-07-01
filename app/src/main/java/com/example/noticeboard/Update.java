package com.example.noticeboard;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class Update {
    Activity apref;
    public Update(final Activity a){
        apref = a;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                boolean hasInternet  = false ;
                hasInternet = new Internet(a).isInternetAvailable();

                if(hasInternet){
                    Intent i = new Intent(apref.getApplicationContext(),MainActivity.class);
                    apref.startActivity(i);

                }
            }
        };

        timer.schedule(task,30*60000,30*60000);
    }

}
