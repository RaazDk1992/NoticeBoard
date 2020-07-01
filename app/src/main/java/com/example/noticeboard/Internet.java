package com.example.noticeboard;

import android.app.Activity;
import android.content.Intent;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Internet {
    Activity activity;
    public Internet(Activity a){

        activity = a;
    }

    public boolean isInternetAvailable() {
        try {
            final InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            // Log error
            return false;
        }

    }
}
