/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.noticeboard;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Timer;
import java.util.TimerTask;

import fetch.ElectedOfficials;
import fetch.FetchElectedOfficials;
import fetch.FetchEmergencyNumbers;
import fetch.FetchNewsFeed;
import fetch.FetchServices;


/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class MainActivity extends Activity {

    TableLayout serviceTable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(isConnected()){
            setContentView(R.layout.notice_board_main);

           // serviceTable = findViewById(R.id.mytable);
            new FetchServices(this).listServices();
            new FetchEmergencyNumbers(this).listNumers();
            new FetchElectedOfficials(this).DisplayElectedOfficials();

            new Update(this);
         //   startActivity(intent);
        }else {

            setContentView(R.layout.no_connection);


        }

    }

    public boolean isConnected(){
        boolean connected = false;
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        connected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return connected;
    }
}
