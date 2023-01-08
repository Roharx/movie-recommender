package dal.interfaces;

import be.Category;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryDAO {
    List<Category> getAllCategories() throws SQLException;
    Category getCategoryByID(int id) throws SQLException;
    void createCategory(Category category) throws SQLException;
    void deleteCategory(int id) throws SQLException;

}
