package be;

import dal.CategoryDAO;

public class Category {
    private int id;
    private String categoryname;

    //region getters and setters
    public int getId(){return id;}
    public void setId(int id){
        this.id = id;
    }
    public String getCategoryname(){
        return categoryname;
    }
    public void setCategoryname(String categoryname){
        this.categoryname = categoryname;
    }

    //endregion



    public Category(int id, String categoryname){
        this.categoryname = categoryname;
        this.id = id;
    }


    @Override
    public String toString(){
        return id + " " + categoryname;
    }
}
