package gui.models;

import be.Category;
import bll.CategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class CategoryModel {
    CategoryManager cbll = new CategoryManager();
    private ObservableList<Category> categories = FXCollections.observableArrayList();


    public ObservableList<Category> getCategories() {
        return categories;
    }


    public void createCategory( Category category) throws SQLException {
        cbll.createCategory(category);
        categories.remove((category.getId()));
    }


    public void deleteCategory(int id) throws SQLException{
        cbll.deleteCategory(id);


    }


    public void fetchAllCategories() throws SQLException {
        categories.addAll(cbll.getAllCategories());
    }
}


