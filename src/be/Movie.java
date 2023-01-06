package be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {
    private int id;
    private SimpleStringProperty title;
    private SimpleStringProperty imdbrating;
    private SimpleStringProperty userrating;
    private SimpleStringProperty filelink;
    private SimpleStringProperty datatime;

    public Movie(String imdbrating,String title,String userrating,String filelink,String datatime){
        this.imdbrating.set(imdbrating);
        this.title.set(title);
        this.userrating.set(userrating);
        this.filelink.set(filelink);
        this.datatime.set(datatime);
    }
    public Movie(int id,String imdbrating,String title,String userrating,String filelink,String datatime){
        this(imdbrating,title,userrating,filelink,datatime);
        this.id= id;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title.get();
    }
    public SimpleStringProperty titleProperty() {
        return title;
    }
    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getImdbrating(){return imdbrating.get();}
    public SimpleStringProperty imdbratingProperty(){return imdbrating;}
    public void setImdbrating(String imdbrating){this.imdbrating.set(imdbrating);}




}

