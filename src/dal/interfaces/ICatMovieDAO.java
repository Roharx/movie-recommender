package dal.interfaces;

import be.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICatMovieDAO {

    List<Integer> getMovieIDsByCategoryForID(int catID) throws SQLException;

    List<Integer> getCategoriesForMovieID(int movieID) throws SQLException;
    void addCategoryToMovie(int id, int movieID, int categoryID) throws SQLException;
}
