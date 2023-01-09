package bll;

import be.Movie;
import bll.interfaces.IMovieManager;
import dal.MovieDAO;
import dal.interfaces.IMovieDAO;

import java.sql.SQLException;
import java.util.List;

public class MovieManager implements IMovieManager{

    IMovieDAO movieDAO;

    public MovieManager(){
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
        return 0;
    }
}
