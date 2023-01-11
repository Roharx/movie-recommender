package gui.controllers;





import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;




import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class MovieController implements Initializable {
    @FXML
    private Slider sliderTime;
    @FXML
    private Label labelCurrentTime;
    @FXML
    private Label labelTotalTime;
    @FXML
    private Button buttonPps;
    private ImageView ivPlay;
    private ImageView ivPause;
    private boolean isPlaying;
    private boolean isEndOfVideo = false;
    
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
        playPauseMedia();

    }



    public void playPauseMedia() {
        final int IV_SIZE = 18;

        Image imagePlay = new Image(new File("mp4/play.png").toURI().toString());
        ivPlay = new ImageView(imagePlay);
        ivPlay.setFitHeight(IV_SIZE);
        ivPlay.setFitWidth(IV_SIZE);

        Image imagePause = new Image(new File("mp4/pause.png").toURI().toString());
        ivPause = new ImageView(imagePause);
        ivPause.setFitHeight(IV_SIZE);
        ivPause.setFitWidth(IV_SIZE);

        buttonPps.setGraphic(ivPlay);

        buttonPps.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bindCurrentTimeLabel();
                Button buttonPlay = (Button) actionEvent.getSource();
                if(isPlaying){
                    buttonPlay.setGraphic(ivPlay);
                    mediaPlayer.pause();
                    isPlaying = false;


                }else {
                    buttonPlay.setGraphic(ivPause);
                    mediaPlayer.play();
                    isPlaying = true;
                    changeVolume();
                    bindCurrentTimeLabel();

                }

            }
        });
        mediaPlayer.totalDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldDuration, Duration newDuration) {
                bindCurrentTimeLabel();
                sliderTime.setMax(newDuration.toSeconds());
                labelTotalTime.setText(getTime(newDuration));

            }
        });


        sliderTime.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean isChanging) {
                bindCurrentTimeLabel();
                if( !isChanging) {
                   mediaPlayer.seek(Duration.seconds(sliderTime.getValue()));
               }
            }
        });


        sliderTime.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                bindCurrentTimeLabel();
                double currentTime = mediaPlayer.getCurrentTime().toSeconds();
                if (Math.abs(currentTime - newValue.doubleValue()) > 0.5) {
                    mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                }
                labelMatchEndVideo(labelCurrentTime.getText(), labelTotalTime.getText());
            }
        });

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                sliderTime.setValue(newValue.toSeconds());
            }
        });
    }



    public void bindCurrentTimeLabel() {
       labelCurrentTime.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
           @Override
           public String call() throws Exception {
               return getTime(mediaPlayer.getCurrentTime()) ;
           }
       }, mediaPlayer.currentTimeProperty()));

    }


    private String getTime(Duration time){
        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();

        if (seconds > 59) seconds = seconds % 60;
        if (minutes > 59) minutes = minutes % 60;
        if (hours > 59) hours = hours % 60;

        if(hours > 0) return String.format("%d:%02d:%02d" ,
                hours,
                minutes,
                seconds);
        else return String.format("%02d:%02d",
                minutes,
                seconds);

    }

    public void labelMatchEndVideo(String labelTime,String labelTotalTime) {
        for (int i = 0; i < labelTotalTime.length(); i++) {
            if (labelTime.charAt(i) != labelTotalTime.charAt(i)) {
                isEndOfVideo = false;
                if (isPlaying) buttonPps.setGraphic(ivPause);
                else buttonPps.setGraphic(ivPlay);
                break;
            } else {
                isEndOfVideo = true;
                buttonPps.setGraphic(ivPause);
            }
        }


    }

    public void fastForward(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime()
                .add(Duration.seconds(10)));
    }
    public void fastRewind(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime()
                .add(Duration.seconds(-10)));
    }

    public void beginTimer() {
        timer = new Timer();
        task = new TimerTask() {
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();

                progressBar.setProgress(current / end);
                if (current / end == 1) {
                    cancelTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelTimer() {
        running = false;
        timer.cancel();
    }


    public void changeVolume() {
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });
    }

    public void resetMedia(ActionEvent actionEvent) {
        if (mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
        }
    }
}

