package fetch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Feed {

    String base_url = "http://lekbeshimun.gov.np";
    @GET("newsfeed")
    Call<List<NewsFeedModel>> getNewsFeed();


    @GET("services-api")
    Call<List<ServicesModel>> getServiceFeed();

    @GET("emergency-numbers-api")
    Call<List<EmergencyNumbers>>getEmergencyNumbers();

    @GET("electedofficialsfeed")
    Call<List<ElectedOfficials>> getElectedOfficials();

    @GET("staffsfeed")
    Call<List<Staffs>> getStaffsFeed();


}
