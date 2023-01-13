package gui.model;

import be.Category;
import bll.CategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryModel {
    private CategoryManager categoryManager;
    private ObservableList<Category> categories;

    public CategoryModel(){
        categoryManager = new CategoryManager();
    }
    public ObservableList<Category> getAllCategories() throws SQLException {
        List<Category> temp = categoryManager.getAllCategories();
        return categories = FXCollections.observableArrayList(temp);
    }


    public void createCategory(Category category) throws SQLException {
        categoryManager.createCategory(category);
        categories.add((category));
    }


    public void deleteCategory(Category category) throws SQLException {
        categoryManager.deleteCategory(category.getId());
        categories.remove(categories.indexOf(category));
    }
}


