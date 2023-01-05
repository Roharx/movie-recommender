package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieController implements Initializable {
    @FXML
    private ListView moviesView;
    @FXML
    private ListView categoryView;
    @FXML
    private MediaView mediaView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button playMedia;
    @FXML
    private Button pauseMedia;
    @FXML
    private TextField txtSearch;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        file = new File("mp4/CL - ‘HELLO BITCHES’  MV.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

    }

    public void playMedia(ActionEvent actionEvent) {
        mediaPlayer.play();
    }

    public void pauseMedia(ActionEvent actionEvent) {
        mediaPlayer.pause();
    }
}
