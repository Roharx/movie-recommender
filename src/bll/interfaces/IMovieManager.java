package bll.interfaces;

import be.Movie;

import java.sql.SQLException;
import java.util.List;

public interface IMovieManager {
    List<Movie> getAllMovies() throws SQLException;
    Movie getMovieByID(int id) throws SQLException;
    List<Movie> getMoviesByIMDB(float rating) throws SQLException;
    List<Movie> getMoviesByUserRating(int rating) throws SQLException;
    void createMovie(Movie movie) throws SQLException;
    void deleteMovie(int id) throws SQLException;
    int getMaxID() throws SQLException;
}
