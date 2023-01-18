package gui.controllers;

import be.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EditCategoryController {

    @FXML
    public Label lblMovieTitle,
            btnEditMovie;
    @FXML
    public Button btnOk;
    private MovieController movieController;
    private Movie selectedMovie;

    public EditCategoryController(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
        System.out.println(selectedMovie.getTitle());
    }
    public EditCategoryController() {

    }
    public void initialize(){

    }
    public void okPressed(ActionEvent actionEvent) {



        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }
}
