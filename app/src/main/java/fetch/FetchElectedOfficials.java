package fetch;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.noticeboard.R;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchElectedOfficials {

    private Activity AppRef;

    public FetchElectedOfficials(Activity appRef) {
        AppRef = appRef;
    }

    public void DisplayElectedOfficials(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Feed.base_url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        final Feed feed = retrofit.create(Feed.class);
        Call<List<ElectedOfficials>> call = feed.getElectedOfficials();

        final ViewFlipper candidateImageHolder = AppRef.findViewById(R.id.elected_candidates);

        call.enqueue(new Callback<List<ElectedOfficials>>() {
            @Override
            public void onResponse(Call<List<ElectedOfficials>> call, Response<List<ElectedOfficials>> response) {

                List<ElectedOfficials> official = response.body();
                for (ElectedOfficials o: official
                     ) {

                    Log.e("Designation=>",o.getImage());
                    LayoutInflater inflater = (LayoutInflater)AppRef.getBaseContext().getSystemService(AppRef.getBaseContext().LAYOUT_INFLATER_SERVICE);
                    View view = inflater.inflate(R.layout.image_holder_layout, null,false);
                    ImageView image = view.findViewById(R.id.candidate_image);
                    Glide.with(AppRef).load(new CleanImageSoruce().clean(o.getImage())).override(100,100).fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
                   // new CleanImageSoruce().clean(o.getImage());
                    Log.e("Designation", "");
                    TextView designation = view.findViewById(R.id.candidate_name);
                    TextView candidateName = view.findViewById(R.id.candidate_designation);

                    candidateName.setText(o.getTitle());
                    designation.setText(StringUtils.substringBetween(o.getDesignation(),">","<"));


                    candidateImageHolder.addView(view);
                    candidateImageHolder.startFlipping();
                    candidateImageHolder.setFlipInterval(5000);


                }

            }

            @Override
            public void onFailure(Call<List<ElectedOfficials>> call, Throwable t) {


            }
        });


    }

}
