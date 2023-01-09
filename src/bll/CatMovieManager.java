package bll;

import bll.interfaces.ICatMovieManager;
import dal.CatMovieDAO;
import dal.interfaces.ICatMovieDAO;

import java.sql.SQLException;
import java.util.List;

public class CatMovieManager implements ICatMovieManager{

    ICatMovieDAO catMovieDAO = new CatMovieDAO();
    @Override
    public List<Integer> getMovieIDsByCategoryForID(int catID) throws SQLException {
        return catMovieDAO.getMovieIDsByCategoryForID(catID);
    }

    @Override
    public List<Integer> getCategoriesForMovieID(int movieID) throws SQLException {
        return catMovieDAO.getCategoriesForMovieID(movieID);
    }
}
