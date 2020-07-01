package fetch;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.noticeboard.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchEmergencyNumbers {

    private Activity appref;
    public FetchEmergencyNumbers(Activity ref){
        this.appref = ref;
    }

    public void listNumers(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Feed.base_url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        final Feed feed = retrofit.create(Feed.class);
        Call<List<EmergencyNumbers>> call = feed.getEmergencyNumbers();
        call.enqueue(new Callback<List<EmergencyNumbers>>() {
            @Override
            public void onResponse(Call<List<EmergencyNumbers>> call, Response<List<EmergencyNumbers>> response) {
                /*
                List<EmergencyNumbers> emergencyNumbers = response.body();
                LayoutInflater inflater = (LayoutInflater)appref.getBaseContext().getSystemService(appref.getBaseContext().LAYOUT_INFLATER_SERVICE);
                View tableRow = null;
                TableLayout table = null;
                ViewFlipper fliper = appref.findViewById(R.id.emergency_contacts);
                int rowcount = 0;
                int maxdata = 5;
                TextView address;
                TextView number;
                for (EmergencyNumbers e:
                      emergencyNumbers) {
                   // Log.e("Emergency",e.getContact());
                    tableRow  = inflater.inflate(R.layout.important_single,null,false);
                    if(tableRow.getParent() != null){
                        ( (ViewGroup)tableRow.getParent()).removeView(tableRow);
                    }
                    address = tableRow.findViewById(R.id.details);
                    number = tableRow.findViewById(R.id.number);


                    if(rowcount % maxdata == 0){
                        table  = (TableLayout) inflater.inflate(R.layout.importantnumers_layout, null,false);
                        address.setText(e.getTitle());
                        number.setText(e.getContact());
                        table.addView(tableRow);
                        fliper.addView(table);



                        // Log.e("Number",e.getContact());
                    }else {
                        address.setText(e.getTitle());
                        number.setText(e.getContact());
                        // Log.e("Number",e.getContact());
                        table.addView(tableRow);
                    }

                    rowcount++;
                }
                fliper.startFlipping();
                fliper.setFlipInterval(6000);*/
            }

            @Override
            public void onFailure(Call<List<EmergencyNumbers>> call, Throwable t) {

            }
        });
    }
}
