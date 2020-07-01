package fetch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicesModel {

    @SerializedName("सेवा प्रकार")
    @Expose
    private String service_type;
    @SerializedName("सेवा समय")
    @Expose
    private String req_time;
    @SerializedName("जिम्मेवार अधिकारी")
    @Expose
    private String res_employee;

    @SerializedName("सेवा दिने कार्यालय")
    @Expose
    private String dept;
    @SerializedName("सेवा शुल्क")
    @Expose
    private String service_fare;

    @SerializedName("आवश्यक कागजातहरु")
    @Expose
    private String req_docs;
    @SerializedName("प्रक्रिया")
    @Expose
    private String process;



    public ServicesModel(String service_type, String title, String req_time, String service_fare, String req_docs,
                         String process, String res_employee, String dept) {
        this.service_type = service_type;
        Title = title;
        this.req_time = req_time;
        this.service_fare = service_fare;
        this.req_docs = req_docs;
        this.process = process;
        this.res_employee = res_employee;
        this.dept = dept;
    }




    public String getService_type() {
        return service_type;
    }

    public String getReq_docs() {
        return req_docs;
    }

    public String getProcess() {
        return process;
    }

    public String getRes_employee() {
        return res_employee;
    }

    public String getDept() {
        return dept;
    }

    public String getTitle() {
        return Title;
    }

    public String getReq_time() {
        return req_time;
    }

    public String getService_fare() {
        return service_fare;
    }


    private String Title;



}
