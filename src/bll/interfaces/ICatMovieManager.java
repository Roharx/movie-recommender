package bll.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface ICatMovieManager {
    List<Integer> getMovieIDsByCategoryForID(int catID) throws SQLException;

    List<Integer> getCategoriesForMovieID(int movieID) throws SQLException;
}
