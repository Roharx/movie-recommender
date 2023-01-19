package dal;

import dal.database.DatabaseConnector;
import dal.interfaces.ICatMovieDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatMovieDAO implements ICatMovieDAO {

    private PreparedStatement preparedStatement;
    private DatabaseConnector databaseConnector;

    public CatMovieDAO() {
        databaseConnector = new DatabaseConnector();
    }

    @Override
    public List<Integer> getMovieIDsByCategoryForID(int catID) throws SQLException {
        List<Integer> result = new ArrayList<>();

        String sql = "SELECT movieid FROM CatMovie WHERE categoryid = ?";
        preparedStatement = databaseConnector.createConnection().prepareStatement(sql);
        preparedStatement.setInt(1, catID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result.add(resultSet.getInt("movieid"));
        }
        return result;
    }

    @Override
    public List<Integer> getCategoriesForMovieID(int movieID) throws SQLException {
        List<Integer> result = new ArrayList<>();

        String sql = "SELECT catagoryid FROM CatMovie WHERE movieid = ?";
        preparedStatement = databaseConnector.createConnection().prepareStatement(sql);
        preparedStatement.setInt(1, movieID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result.add(resultSet.getInt("categoryid"));
        }
        return result;
    }

    @Override
    public void addCategoryToMovie(int id, int movieID, int categoryID) throws SQLException {

        String sql = "INSERT INTO CatMovie (id, categoryid, movieid) VALUES (?,?,?)";

        preparedStatement = databaseConnector.createConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, categoryID);
        preparedStatement.setInt(3, movieID);

        preparedStatement.execute();
    }

    @Override
    public void removeCategoryFromMovie(int movieID, int categoryID) throws SQLException {
        String sql = "DELETE FROM CatMovie WHERE movieid = ? AND categoryid = ?";

        preparedStatement = databaseConnector.createConnection().prepareStatement(sql);
        preparedStatement.setInt(1, movieID);
        preparedStatement.setInt(2, categoryID);

        preparedStatement.execute();
    }

    @Override
    public int getMaxIDForCatMovie() throws SQLException  {
        String sql = "SELECT MAX(id) AS id FROM CatMovie";

        Connection conn = databaseConnector.createConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        int id = 0;
        if (rs.next())
            id = rs.getInt("id");

        return id;
    }
}



