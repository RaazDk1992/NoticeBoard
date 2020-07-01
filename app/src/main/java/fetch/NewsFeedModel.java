package fetch;

/*
   {"title":"\u0938\u0941\u091a\u0928\u093e","body":"","image":""}

 */
public class NewsFeedModel {

    private String title;
    private String body;
    private String image;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getImage() {
        return image;
    }



    public NewsFeedModel(String title, String body, String image) {
        this.title = title;
        this.body = body;
        this.image = image;
    }


}
