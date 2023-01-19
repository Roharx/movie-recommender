package gui.controllers;

import be.Category;
import be.Movie;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class EditCategoryController {

    //region FXML
    @FXML
    public Label lblMovieTitle,
            lblMovieCategories;
    @FXML
    public Button btnOk,
            btnAddCategoryToMovie,
            btnDelete;
    @FXML
    private ChoiceBox choiceCategory;
    //endregion

    //region Local variables
    private Movie selectedMovie;
    private MovieModel movieModel;
    private CategoryModel categoryModel;
    private ObservableList<Category> categories = FXCollections.observableArrayList();
    //endregion

    public EditCategoryController() {
        movieModel = new MovieModel();
        categoryModel = new CategoryModel();
    }

    public void fillChoiceBox() throws SQLException {
        categories.addAll(categoryModel.getAllCategories());
        choiceCategory.setItems(categories);
    }

    public EditCategoryController(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    public void okPressed(ActionEvent actionEvent) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    public void displayMovieTitle(String title) {
        lblMovieTitle.setText(title);
    }
    public void addCategoryToMovie(ActionEvent actionEvent) {
        try {
            movieModel.addCategoryToMovie(
                    movieModel.getMaxIDForCatMovie() + 1,
                    movieModel.getMovieByTitle(lblMovieTitle.getText()).getId(),
                    choiceCategory.getSelectionModel().getSelectedIndex() + 1);
            JOptionPane.showMessageDialog(null, "Added category to movie.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePressed(ActionEvent actionEvent) {
        try {
            movieModel.removeCategoryFromMovie(
                    movieModel.getMovieByTitle(lblMovieTitle.getText()).getId(),
                    choiceCategory.getSelectionModel().getSelectedIndex() + 1);
            JOptionPane.showMessageDialog(null, "Removed category from movie.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}



