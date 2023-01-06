package be;

import javafx.beans.property.SimpleStringProperty;

public class Movie {
    private int id;
    private String title;
    private float imdbrating;
    private String userrating;
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

    public float getImdbrating(){return imdbrating;}
    public void setImdbrating(float imdbrating){this.imdbrating = imdbrating;}

    public String getUserrating() {
        return userrating;
    }

    public void setUserrating(String userrating) {
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



    public Movie(float imdbrating,String title,String userrating,String filelink,String lastview){
        generateID();
        this.imdbrating = imdbrating;
        this.title = title;
        this.userrating = userrating;
        this.filelink = filelink;
        this.lastview = lastview;
    }



    public Movie(int id,float imdbrating,String title,String userrating,String filelink,String datatime){
        this(imdbrating,title,userrating,filelink,datatime);
        checkID(id);
    }

    private void checkID(int id) {
        //TODO check the id, if good: this.id = id;
        this.id = id;
    }

    private void generateID() {
        //TODO generate ID based on DB
    }






}

