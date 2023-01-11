package gui.models;

import be.Category;
import be.Movie;
import bll.CatMovieManager;
import bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieModel {

    private MovieManager movieManager;
    private CatMovieManager catMovieManager;
    private ObservableList<Movie> movies;

    public MovieModel() {
        movieManager = new MovieManager();
        catMovieManager = new CatMovieManager();
    }

    public ObservableList<Movie> getAllMovies() throws SQLException {
        List<Movie> temp = movieManager.getAllMovies();
        return movies = FXCollections.observableList(temp);
    }

    public void createMovie(Movie movie) throws SQLException {
        movieManager.createMovie(movie);
        movies.remove(movie.getId());
    }

    public void deleteMovie(Movie movie) throws SQLException {
        movieManager.deleteMovie(movie.getId());
        movies.remove(movie.getId());
    }

    public void deleteMovieByID(int movieID) throws SQLException {
        movieManager.deleteMovie(movieID);
        movies.remove(movieID);
    }

    public void deleteMovieByTitle(String movieTitle) throws SQLException{
        for (Movie m : movies) {
            if(m.getTitle() == movieTitle){
                movieManager.deleteMovie(m.getId());
                movies.remove(m.getId());
                System.out.println("Deleted movie: " + m.getId() + ", " + m.getTitle());//for testing, delete line later
            }

        }
    }

    public ObservableList<Movie> getMoviesByCategory(Category category) throws SQLException { //very unoptimized, fix later
        List<Integer> movieIDs = catMovieManager.getMovieIDsByCategoryForID(category.getId());
        List<Movie> temp = new ArrayList<>();

        for (int i : movieIDs) {
            temp.add(movieManager.getMovieByID(i));
        }

        return movies = FXCollections.observableList(temp);
    }
}
