package fetch;

public class ElectedOfficials {
    private String title;
    private String image;

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDesignation() {
        return Designation;
    }

    private String Designation;

    public ElectedOfficials(String title, String image, String designation) {
        this.title = title;
        this.image = image;
        Designation = designation;
    }


}
