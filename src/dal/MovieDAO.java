package dal;

import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.database.DatabaseConnector;
import dal.interfaces.IMovieDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MovieDAO implements IMovieDAO {
    private PreparedStatement preparedStatement;
    private DatabaseConnector databaseConnector;

    public MovieDAO() {
        databaseConnector = new DatabaseConnector();
    }

    @Override
    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT * FROM Movie";
        preparedStatement = databaseConnector.createConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            movies.add(new Movie(
                    resultSet.getInt("id"),
                    resultSet.getFloat("imdbrating"),
                    resultSet.getString("title"),
                    resultSet.getString("userrating"),
                    resultSet.getString("filelink"),
                    resultSet.getString("lastview")
            ));

        }

        return movies;
    }


    @Override
    public Movie getMovieByID(int id) throws SQLException {
        Movie result = null;

        String sql = "SELECT * FROM Movie WHERE id = ?";

        preparedStatement = databaseConnector.createConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            result = new Movie(
                    resultSet.getInt("id"),
                    resultSet.getFloat("imdbrating"),
                    resultSet.getString("title"),
                    resultSet.getString("userrating"),
                    resultSet.getString("filelink"),
                    resultSet.getString("lastview")
            );

        return result;
    }

    @Override
    public List<Movie> getMoviesByIMDB(float rating) throws SQLException {
        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT * FROM Movie WHERE imdbrating = ?";

        preparedStatement = databaseConnector.createConnection().prepareStatement(sql);
        preparedStatement.setFloat(1, rating);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            movies.add(new Movie(
                    resultSet.getInt("id"),
                    resultSet.getFloat("imdbrating"),
                    resultSet.getString("title"),
                    resultSet.getString("userrating"),
                    resultSet.getString("filelink"),
                    resultSet.getString("lastview")
            ));

        }

        return movies;
    }

    @Override
    public List<Movie> getMoviesByUserRating(int rating) throws SQLException {
        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT * FROM Movie WHERE id = ?";

        preparedStatement = databaseConnector.createConnection().prepareStatement(sql);
        preparedStatement.setInt(1, rating);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            movies.add(new Movie(
                    resultSet.getInt("id"),
                    resultSet.getFloat("imdbrating"),
                    resultSet.getString("title"),
                    resultSet.getString("userrating"),
                    resultSet.getString("filelink"),
                    resultSet.getString("lastview")
            ));

        }

        return movies;
    }


}
