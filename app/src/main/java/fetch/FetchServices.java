package fetch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.noticeboard.R;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchServices {
    Activity appreference;
    private static final int max_rows = 5;
    public FetchServices( Activity application){

        this.appreference = application;

    }
    public void listServices(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Feed.base_url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        final Feed feed = retrofit.create(Feed.class);
        final ProgressDialog dialog;


        dialog = new ProgressDialog(appreference);
        dialog.setMessage(appreference.getString(R.string.progress_message));
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
        Call<List<ServicesModel>> call = feed.getServiceFeed();
        call.enqueue(new Callback<List<ServicesModel>>() {
            @Override
            public void onResponse(Call<List<ServicesModel>> call, Response<List<ServicesModel>> response) {
                dialog.dismiss();
                List<ServicesModel> servicesFeed = response.body();

                /**
                 * Find view add view start animatng.
                 *
                 */
                ViewFlipper flipper = appreference.findViewById(R.id.chart_container);
                flipper.setFlipInterval(3*60000);
                flipper.startFlipping();


                Log.e("Siiiiiiiiiiiiiiize",servicesFeed.size()+"");

                TableLayout view = null;
                View tableRow = null;

                if(servicesFeed.size() > max_rows){

                    int rowcount = 0;
                    int index = 1;
                    TextView service;
                    TextView required_docs;
                    TextView time;
                    TextView fare;
                    TextView dept;
                    TextView employee;
                    TextView process;
                    LayoutInflater in = (LayoutInflater) appreference.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    for(ServicesModel model : servicesFeed ){
                        /**
                         *
                         * Load new  table row layout.
                         */
                        tableRow = in.inflate(R.layout.table_row,null,false);

                        /**
                         * Table fields.
                         */
                        service =(TextView)tableRow.findViewById(R.id.service);
                        required_docs = tableRow.findViewById(R.id.docs);
                        process = tableRow.findViewById(R.id.process);
                        time = tableRow.findViewById(R.id.r_time);
                        fare = tableRow.findViewById(R.id.fare);
                        dept = tableRow.findViewById(R.id.dept);
                        employee = tableRow.findViewById(R.id.res_employee);



                        if(tableRow.getParent() != null){
                            ( (ViewGroup)tableRow.getParent()).removeView(tableRow);
                        }


                        /**
                         *
                         * Check for need of new View. if necessary -> inject dynamically.
                         */

                        if(rowcount % max_rows == 0){
                            Log.e("New", "New table created.. for"+rowcount);
                            view = (TableLayout) in.inflate(R.layout.table_layout,null,false);


                            service.setText(model.getTitle());
                            required_docs.setText(model.getReq_docs());
                            process.setText(model.getProcess());
                            time.setText(model.getReq_time());
                            fare.setText(model.getService_fare());
                            dept.setText(model.getDept());
                            employee.setText(model.getRes_employee());

                            view.addView(tableRow);
                            flipper.addView(view);

                            index++;

                        } else {
                            /**
                             *
                             *
                             */
                            service.setText(model.getTitle());
                            required_docs.setText(model.getReq_docs());
                            process.setText(model.getProcess());
                            time.setText(model.getReq_time());
                            fare.setText(model.getService_fare());
                            dept.setText(model.getDept());
                            employee.setText(model.getRes_employee());
                            view.addView(tableRow);



                        }
                        rowcount++;


                    }

                }else {
                    /**
                     *
                     * Part that handles if there are very few data.
                     */



                }
            }

            @Override
            public void onFailure(Call<List<ServicesModel>> call, Throwable t) {
                Log.e("Errrrrrrror","Failllllll");

            }
        });



    }
    public boolean addData(TextView view, String message ){
        boolean success = false;
        if(view.getParent() != null){
            ( (ViewGroup)view.getParent()).removeView(view);
        }
        view.setText(message);


        return success;
    }


}
