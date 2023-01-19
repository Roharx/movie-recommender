package gui.model;

import be.Category;
import bll.CategoryManager;
import bll.interfaces.ICategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.List;

public class CategoryModel {
    private ICategoryManager categoryManager;
    private ObservableList<Category> categories = FXCollections.observableArrayList();

    public CategoryModel(){
        categoryManager = new CategoryManager();
    }


    public ObservableList<Category> getAllCategories() throws SQLException {
        List<Category> temp = categoryManager.getAllCategories();
        return categories = FXCollections.observableArrayList(temp);
    }


    public void createCategory(Category category) throws SQLException {
        categoryManager.createCategory(category);
        categories.add(category);

    }

    public void deleteCategory(Category category) throws SQLException {
        categoryManager.deleteCategory(category.getId());
        categories.remove(categories.indexOf(category));
    }


    public int getMaxID() throws SQLException {
        return categoryManager.getMaxID();
    }


}




