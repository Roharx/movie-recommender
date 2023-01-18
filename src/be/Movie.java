package be;

public class Movie {
    private int id;
    private String title;
    private float imdbrating;
    private int userrating;
    private String filelink;
    private String lastview;


    //region getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getImdbrating() {
        return imdbrating;
    }

    public void setImdbrating(float imdbrating) {
        this.imdbrating = imdbrating;
    }

    public int getUserrating() {
        return userrating;
    }

    public void  setUserrating(int userrating) {
        this.userrating = userrating;


    }

    public String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }

    public String getLastview() {
        return lastview;
    }

    public void setLastview(String lastview) {
        this.lastview = lastview;
    }


    //endregion


    public Movie(int id, float imdbRating, String title, int userRating, String fileLink, String lastView) {

        this.id = id;
        this.imdbrating = imdbRating;
        this.title = title;
        this.userrating = userRating;
        this.filelink = fileLink;
        this.lastview = lastView;

    }

    @Override
    public String toString() {
        return id + " " + title + " " + imdbrating + " " + userrating + " " + filelink + " " + lastview;
    }

}

