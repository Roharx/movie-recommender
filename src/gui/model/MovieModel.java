package gui.model;

import be.Movie;
import bll.MovieManager;
import bll.interfaces.ICatMovieManager;
import bll.interfaces.IMovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieModel {
    private IMovieManager movieManager;
    private ObservableList<Movie> movies;

    public MovieModel(){

        movieManager = new MovieManager();
    }

    public ObservableList<Movie> getAllMovies() throws SQLException {
        List<Movie> temp = movieManager.getAllMovies();
        return movies = FXCollections.observableArrayList(temp);
    }

    public void deleteMovie(Movie movie) throws SQLException {
        movieManager.deleteMovie(movie.getId());
        movies.remove(movies.indexOf(movie));
    }

    public void createMovie(Movie movie) throws SQLException{
        movieManager.createMovie(movie);
    }

    public void search(String query) throws SQLException {
        movies.clear();
        movies.addAll(movieManager.searchMovies(query));
    }
    public int getMaxID() throws SQLException {
        return movieManager.getMaxID();
    }
}
