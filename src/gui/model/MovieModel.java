package gui.model;

import be.Category;
import be.Movie;
import bll.CatMovieManager;
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
    private ICatMovieManager catMovieManager;

    public MovieModel() {
        movieManager = new MovieManager();
        catMovieManager = new CatMovieManager();
    }

    public ObservableList<Movie> getAllMovies() throws SQLException {
        List<Movie> temp = movieManager.getAllMovies();
        return movies = FXCollections.observableArrayList(temp);
    }

    public void deleteMovie(Movie movie) throws SQLException {
        movieManager.deleteMovie(movie.getId());
        movies.remove(movie);
    }

    public void createMovie(Movie movie) throws SQLException {
        movieManager.createMovie(movie);
    }

    public void search(String query) throws SQLException {
        movies.clear();
        movies.addAll(movieManager.searchMovies(query));
    }

    public int getMaxID() throws SQLException {
        return movieManager.getMaxID();
    }

    public void addCategoryToMovie(int id, int movieID, int categoryID) throws SQLException {
        catMovieManager.addCategoryToMovie(id, movieID, categoryID);
    }

    public ObservableList<Movie> getAllMoviesForCategory(Category category) {
        List<Integer> movieIDs = null;
        List<Movie> moviesForCategory = new ArrayList<>();
        try {
            movieIDs = catMovieManager.getMovieIDsByCategoryForID(category.getId());

            for (int i : movieIDs) {
                moviesForCategory.add(movieManager.getMovieByID(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return movies = FXCollections.observableArrayList(moviesForCategory);
    }

    public void setUserRatingForMovie(int movieID, int userRating) throws SQLException {
        movieManager.setUserRatingForMovie(movieID, userRating);
    }

    public int getMaxIDForCatMovie() throws SQLException {
        return catMovieManager.getMaxIDForCatMovie();

    }

    public Movie getMovieByTitle(String title) throws SQLException {
        return movieManager.getMovieByTitle(title);
    }

    public void removeCategoryFromMovie(int movieID, int categoryID) throws SQLException {
        catMovieManager.removeCategoryFromMovie(movieID, categoryID);
    }
}
