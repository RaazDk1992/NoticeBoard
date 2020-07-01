package fetch;

public class Staffs {
    private String title;
    private String image;

    public Staffs(String title, String image, String designation) {
        this.title = title;
        this.image = image;
        Designation = designation;
    }

    private String Designation;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }


}
