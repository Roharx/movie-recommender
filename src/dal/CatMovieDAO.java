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
        Connection connection = databaseConnector.createConnection();
        String insert = "'" + movieID + "'" + "," + "'" + categoryID + "'";
        String sql = "INSERT INTO CatMovie (id, movieID, categoryID) VALUES (?,?,?)";

        Statement statement = connection.createStatement();
        statement.execute(sql);

    }


}
