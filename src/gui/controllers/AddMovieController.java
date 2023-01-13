package gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

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

    public void chooseFilePressed(ActionEvent actionEvent) {
        //TODO Choose file, it fills out txfFileLocation
        //TODO After Choose file is done, it should only display .mp4 and .mpeg4
    }

    public void okPressed(ActionEvent actionEvent) {
        //TODO save information from txfs to DB
    }

    public void cancelPressed(ActionEvent actionEvent) {
        //TODO close window
    }
}
