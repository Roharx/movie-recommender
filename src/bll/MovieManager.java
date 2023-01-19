package bll;

import be.Movie;
import bll.interfaces.IMovieManager;
import dal.MovieDAO;
import dal.interfaces.IMovieDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieManager implements IMovieManager {

    IMovieDAO movieDAO;

    public MovieManager() {
        movieDAO = new MovieDAO();
    }
    @Override
    public List<Movie> getAllMovies() throws SQLException {
        return movieDAO.getAllMovies();
    }
    @Override
    public Movie getMovieByID(int id) throws SQLException {
        return movieDAO.getMovieByID(id);
    }
    @Override
    public List<Movie> getMoviesByIMDB(float rating) throws SQLException {
        return movieDAO.getMoviesByIMDB(rating);
    }
    @Override
    public List<Movie> getMoviesByUserRating(int rating) throws SQLException {
        return movieDAO.getMoviesByIMDB(rating);
    }
    @Override
    public void createMovie(Movie movie) throws SQLException {
        movieDAO.createMovie(movie);
    }
    @Override
    public void deleteMovie(int id) throws SQLException {
        movieDAO.deleteMovie(id);
    }
    @Override
    public int getMaxID() throws SQLException {
        return movieDAO.getMaxID();
    }
    @Override
    public List<Movie> searchMovies(String query) throws SQLException {
        List<Movie> movies = movieDAO.getAllMovies();
        List<Movie> filtered = new ArrayList<>();

        for (Movie m : movies) {
            if (("" + m.getTitle().toLowerCase()).contains(query.toLowerCase())) {
                filtered.add(m);
            }
        }
        return filtered;
    }



   public void setUserRatingForMovie(int movieID, int userRating){
        try {
            movieDAO.setUserRatingForMovie(movieID, userRating);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Movie getMovieByTitle(String title) throws SQLException {
        return movieDAO.getMovieByTitle(title);
    }
}
