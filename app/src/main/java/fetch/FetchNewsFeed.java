package fetch;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.noticeboard.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.lang.Object;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class FetchNewsFeed {
    private Activity app_ref;
    private ProgressBar fetch_progress;
    public FetchNewsFeed(Activity app){

        this.app_ref = app;
        fetch_progress = new ProgressBar(app);

        fetch_progress.setIndeterminate(true);
        fetch_progress.setX(500.0f);
        fetch_progress.setY(500.0f);


    }

    /**
     *
     *
     * @return
     */



    public List<NewsFeedModel> loadNewsFeed(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Feed.base_url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        final Feed feed = retrofit.create(Feed.class);
        Call<List<NewsFeedModel>> call = feed.getNewsFeed();
        call.enqueue(new Callback<List<NewsFeedModel>>() {
            @Override
            public void onResponse(Call<List<NewsFeedModel>> call, Response<List<NewsFeedModel>> response) {

                List<NewsFeedModel> newsFeed= response.body();
                String imageSource = null;

                Pattern p = Pattern.compile("src=\"(.*?)\"");

                Log.e("Size","hj");
                for(NewsFeedModel news: newsFeed){

                    Matcher m = p.matcher(news.getImage());
                    if (m.find()) {
                        Log.e("ImagePath",m.group(1));

                       new DownLoad().execute(buildUrl(m.group(1)));
                    } else {
                        Log.e("Invalid source",news.getImage());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<NewsFeedModel>> call, Throwable t) {

                Log.e("Faiiiiiiiiiiiiii000il","Failed");

            }
        });


        return null;
    }

   class DownLoad extends AsyncTask<URL,Boolean, Bitmap>{

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           fetch_progress.setVisibility(View.VISIBLE);



       }

       @Override
       protected Bitmap doInBackground(URL... urls) {
           URL url = urls[0];
           HttpURLConnection connection = null;

           try{
               // Initialize a new http url connection
               connection = (HttpURLConnection) url.openConnection();

               // Connect the http url connection
               connection.connect();

               // Get the input stream from http url connection
               InputStream inputStream = connection.getInputStream();

                /*
                    BufferedInputStream
                        A BufferedInputStream adds functionality to another input stream-namely,
                        the ability to buffer the input and to support the mark and reset methods.
                */
                /*
                    BufferedInputStream(InputStream in)
                        Creates a BufferedInputStream and saves its argument,
                        the input stream in, for later use.
                */
               // Initialize a new BufferedInputStream from InputStream
               BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                /*
                    decodeStream
                        Bitmap decodeStream (InputStream is)
                            Decode an input stream into a bitmap. If the input stream is null, or
                            cannot be used to decode a bitmap, the function returns null. The stream's
                            position will be where ever it was after the encoded data was read.

                        Parameters
                            is InputStream : The input stream that holds the raw data
                                              to be decoded into a bitmap.
                        Returns
                            Bitmap : The decoded bitmap, or null if the image data could not be decoded.
                */
               // Convert BufferedInputStream to Bitmap object
               Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);

               // Return the downloaded bitmap


               Log.e("Recieved Data",bmp+"");
               Log.e("Image saved",saveImageToInternalStorage(bmp)+"");
               return bmp;

           }catch(IOException e){
               e.printStackTrace();
           }finally{
               // Disconnect the http url connection
               connection.disconnect();
           }
           return null;

       }
       protected Uri saveImageToInternalStorage(Bitmap bitmap){
           // Initialize ContextWrapper
           ContextWrapper wrapper = new ContextWrapper(app_ref);

           // Initializing a new file
           // The bellow line return a directory in internal storage
           File mydir = app_ref.getDir("Images", Context.MODE_PRIVATE); //Creating an internal dir;
           if (!mydir.exists())
           {
               mydir.mkdirs();
           }

           File file = wrapper.getDir("Images",MODE_PRIVATE);

           // Create a file to save the image
           file = new File(file, "UniqueFileName"+".png");

           try{
               // Initialize a new OutputStream
               OutputStream stream = null;

               // If the output file exists, it can be replaced or appended to it
               stream = new FileOutputStream(file);

               // Compress the bitmap
               bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

               // Flushes the stream
               stream.flush();

               // Closes the stream
               stream.close();

           }catch (IOException e) // Catch the exception
           {
               e.printStackTrace();
           }

           // Parse the gallery image url to uri
           Uri savedImageURI = Uri.parse(file.getAbsolutePath());

           // Return the saved image Uri
           return savedImageURI;
       }

   }
    protected URL buildUrl(String path){
        URL url;
        try {
            url = new URL(path);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
