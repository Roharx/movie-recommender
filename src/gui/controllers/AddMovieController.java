package gui.controllers;

import be.Movie;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

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
    private File file;
    private String path;
    private MediaPlayer mediaPlayer;



    public void chooseFilePressed(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video types", "*.mp4", "*.mpeg4"));
        file = fileChooser.showOpenDialog(null);
        path = file.toURI().toString();

        if (path != null){
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
            txfFileLocation.setText(file.getPath());
            txfMovieTitle.setText(file.getName());

        };
    }


    public void okPressed(ActionEvent actionEvent) {
        //TODO save information from txfs to DB
    }

    public void cancelPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}


