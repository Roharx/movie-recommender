package gui.model;

import be.Movie;
import bll.MovieManager;
import bll.interfaces.ICatMovieManager;
import bll.interfaces.IMovieManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieModel {
    IMovieManager movieManager = new MovieManager();
    List<Movie> listMovies;

    public MovieModel(){

        listMovies = new ArrayList<>();
    }

    public List<Movie> getAllMovies() throws SQLException {
        return movieManager.getAllMovies();
    }

    public void deleteMovie(Movie movie) throws SQLException {
        movieManager.deleteMovie(movie.getId());
    }

    public void creatMovie(Movie movie) throws SQLException{
        movieManager.createMovie(movie);
    }


}
