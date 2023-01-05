package gui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MovieController implements Initializable {
    @FXML
    private Button resetButton;
    @FXML
    private Button previousMedia;
    @FXML
    private Button nextMedia;
    @FXML
    private Slider volumeSlider;
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
    private Timer timer;
    private TimerTask task;
    private boolean running;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        file = new File("mp4/CL - ‘HELLO BITCHES’  MV.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

    }

    public void playMedia(ActionEvent actionEvent) {
        beginTimer();
        mediaPlayer.play();
        changeVolume();
    }

    public void pauseMedia(ActionEvent actionEvent) {
        mediaPlayer.pause();
    }
    public void beginTimer() {
        timer = new Timer();
        task = new TimerTask() {
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();

                progressBar.setProgress(current/end);
                if(current/end == 1){
                    cancelTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
    public void cancelTimer(){
        running = false;
        timer.cancel();
    }
    public void changeVolume() {
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);}
        });
    }
    public void resetMedia(ActionEvent actionEvent){
        if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
        }
    }

    }

