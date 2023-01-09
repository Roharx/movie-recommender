package bll.interfaces;

import be.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryManager {
    public List<Category> getAllCategories() throws SQLException;
    public Category getCategoryByID(int id) throws SQLException;
    public void createCategory( Category category) throws SQLException;
    public void deleteCategory(int id) throws SQLException;
}
