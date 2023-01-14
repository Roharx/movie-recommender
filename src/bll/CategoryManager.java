package bll;

import be.Category;
import bll.interfaces.ICategoryManager;
import dal.CategoryDAO;
import dal.interfaces.ICategoryDAO;

import java.sql.SQLException;
import java.util.List;

public class CategoryManager implements ICategoryManager {

    ICategoryDAO categoryDAO;

    public CategoryManager(){ categoryDAO = new CategoryDAO();}

    @Override
    public List<Category> getAllCategories() throws SQLException{
        return  categoryDAO.getAllCategories();
    }

    @Override
    public Category getCategoryByID(int id) throws SQLException{
        return categoryDAO.getCategoryByID(id);
    }

    @Override
    public void createCategory( Category category) throws SQLException{
        categoryDAO.createCategory(category);

    }

    @Override
    public void deleteCategory(int id) throws SQLException{
        categoryDAO.deleteCategory(id);
    }
    @Override
    public int getMaxID() throws SQLException {
        return categoryDAO.getMaxID();
    }




}
