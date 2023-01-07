package bll.interfaces;

import be.Movie;

import java.sql.SQLException;
import java.util.List;

public interface IMovieManager {
    public List<Movie> getAllMovies() throws SQLException;
    public Movie getMovieByID(int id) throws SQLException;
    public List<Movie> getMoviesByIMDB(float rating) throws SQLException;
    public List<Movie> getMoviesByUserRating(int rating) throws SQLException;
    public void createMovie(Movie movie) throws SQLException;
    public void deleteMovie(int id) throws SQLException;
    public int getMaxID() throws SQLException;
}
