package gui.controllers;

import be.Movie;
import bll.MovieManager;
import bll.interfaces.IMovieManager;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class AddMovieController {

    @FXML
    public Button btnChooseFile,
            btnOk,
            btnCancel;
    @FXML
    public TextField txfUserRating,
            txfFileLocation,
            txfMovieTitle,
            txfIMDBRating;
    public TextField txfEditMovieTitle,
            txfEditMovieImdbRating,
            txfEditUserRating;
    public Button btnEditMovie,
            btnCancelEdit;
    public Label lblMovieTitle;
    private File file;
    private String path;
    private MediaPlayer mediaPlayer;
    MovieModel movieModel;


    public AddMovieController() {
        movieModel = new MovieModel();
    }

    public void chooseFilePressed(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("mp4, mpeg4", "*.mp4", "*.mpeg4"));
        file = fileChooser.showOpenDialog(null);
        path = file.toURI().toString();

        if (path != null) {
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
            txfFileLocation.setText(file.getPath());
            txfMovieTitle.setText(file.getName());

        }
    }


    public void okPressed(ActionEvent actionEvent) throws IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            movieModel.createMovie(new Movie(
                    movieModel.getMaxID() + 1,
                    Float.parseFloat(txfIMDBRating.getText()),
                    txfMovieTitle.getText(),
                    0,
                    txfFileLocation.getText(),
                    sdf.format(new Date())));
            closeWindow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelPressed(ActionEvent actionEvent) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}


