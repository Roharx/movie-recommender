package dal.interfaces;

import be.Movie;
import java.sql.SQLException;
import java.util.List;


public interface IMovieDAO {
    List<Movie> getAllMovies() throws SQLException;
    List<Movie> getMoviesByIMDB(float rating) throws SQLException;
    List<Movie> getMoviesByUserRating(int rating) throws SQLException;
    Movie getMovieByID(int id) throws SQLException;
    Movie getMovieByTitle(String title) throws SQLException;
    void createMovie(Movie movie) throws SQLException;
    void deleteMovie(int id) throws SQLException;
    int getMaxID() throws SQLException;
    void setUserRatingForMovie(int movieID, int userRating) throws SQLException;

}
