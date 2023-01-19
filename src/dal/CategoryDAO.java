package dal;

import be.Category;
import dal.database.DatabaseConnector;
import dal.interfaces.ICategoryDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {

    private PreparedStatement preparedStatement;
    private DatabaseConnector databaseConnector;

    public CategoryDAO() {
        databaseConnector = new DatabaseConnector();
    }

    @Override
    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM Category";
        preparedStatement = databaseConnector.createConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            categories.add(new Category(
                    resultSet.getInt("id"),
                    resultSet.getString("categoryname")
            ));
        }
        return categories;
    }

    @Override
    public Category getCategoryByID(int id) throws SQLException {
        Category result = null;

        String sql = "SELECT categoryid FROM Category WHERE id = ?";

        preparedStatement = databaseConnector.createConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            result = new Category(
                    resultSet.getInt("id"),
                    resultSet.getString("categoryname")
            );
        return result;
    }

    @Override
    public void createCategory(Category category) throws SQLException {
        String sql = "INSERT INTO Category( id, categoryname ) VALUES ( ?,?)";

        preparedStatement = databaseConnector.createConnection().prepareStatement(sql);

        preparedStatement.setInt(1, category.getId());
        preparedStatement.setString(2, category.getCategoryname());

        preparedStatement.execute();

    }


    @Override
    public void deleteCategory(int id) throws SQLException {
        String sql = "DELETE FROM Category WHERE id= ?";
        Connection conn = databaseConnector.createConnection();

        PreparedStatement psrmt = conn.prepareStatement(sql);

        psrmt.setInt(1, id);
        psrmt.executeUpdate();
    }

    @Override
    public int getMaxID() throws SQLException {
        String sql = "SELECT MAX(id) AS id FROM Category";

        Connection conn = databaseConnector.createConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        int id = 0;
        if (rs.next())
            id = rs.getInt("id");

        return id;
    }}




