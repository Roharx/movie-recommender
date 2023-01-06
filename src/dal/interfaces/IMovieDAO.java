package dal.interfaces;

import be.Movie;

import java.util.List;

public interface IMovieDAO {
    //TODO Balint
    List<Movie> getAllMovies() throws Exception;
}
