package gui.controllers;

import be.Category;
import be.Movie;
import bll.CatMovieManager;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditCategoryController {

    @FXML
    public Label lblMovieTitle,
            btnEditMovie;
    @FXML
    public Button btnOk,
            btnAddCategoryToMovie;
    @FXML
    private ChoiceBox choiceCategory;

    private MovieController movieController;
    private Movie selectedMovie;
    private Category selectedCategory;
    MovieModel movieModel;
    CategoryModel categoryModel;
    private ObservableList<Category> categories = FXCollections.observableArrayList();

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
        // TODO add category to selected movie
    }
}



