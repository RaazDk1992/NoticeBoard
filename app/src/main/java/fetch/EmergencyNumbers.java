package fetch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmergencyNumbers {

    private String Title;
    private String Address;

    public String getTitle() {
        return Title;
    }

    public String getAddress() {
        return Address;
    }

    public String getContact() {
        return Contact;
    }

    @SerializedName("Phone Number")
    @Expose private String Contact;

    public EmergencyNumbers(String title, String address, String contact) {
        Title = title;
        Address = address;
        Contact = contact;
    }


}
