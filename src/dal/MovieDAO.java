package dal;

import be.Movie;
import dal.database.DatabaseConnector;
import dal.interfaces.IMovieDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements IMovieDAO {
    //TODO Balint
    private PreparedStatement preparedStatement;
    private DatabaseConnector databaseConnector;

    public MovieDAO(){
        databaseConnector = new DatabaseConnector();
    }

    @Override
    public List<Movie> getAllMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT * FROM Movie";
        preparedStatement = databaseConnector.createConnection().prepareStatement(sql);
        ResultSet resultSet =preparedStatement.executeQuery();
        while(resultSet.next()){
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
