package fetch;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanImageSoruce {
    public CleanImageSoruce() {
    }

    public String clean(String URL){

        String  url = null;
        Pattern p = Pattern.compile("src=\"(.*?)\"");
        Matcher m = p.matcher(URL);
        if (m.find()) {
            //Log.e("ImagePath",m.group(1));
            url =m.group(1);
            Log.e("Return",url);
            return url;

        } else {
            Log.e("Invalid source","Invalid path :"+URL);
        }

        return url;

    }
}
