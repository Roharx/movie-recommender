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
    @Override
    public void addCategoryToMovie(int id, int movieID, int categoryID) throws SQLException {
        catMovieDAO.addCategoryToMovie(id ,movieID ,categoryID);
    }

    @Override
    public void removeCategoryFromMovie(int movieID, int categoryID) throws SQLException {
        catMovieDAO.removeCategoryFromMovie(movieID,categoryID);
    }

    @Override
    public int getMaxIDForCatMovie() throws SQLException  {
        return catMovieDAO.getMaxIDForCatMovie();
    }


}
