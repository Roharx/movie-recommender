package gui.controllers;

import be.Category;
import be.Movie;
import gui.models.MovieModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MovieController implements Initializable {

    @FXML
    public TableView tbvCategories,
            tbvMovies;
    @FXML
    public TableColumn tbcIMDB,
            tbcUserRating,
            tbcTitle,
            tbcCategories;
    @FXML
    private Button resetButton,
            previousMedia,
            nextMedia,
            playMedia,
            pauseMedia;
    @FXML
    private ListView moviesView,
            categoryView;
    @FXML
    private Slider volumeSlider;
    @FXML
    private MediaView mediaView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    public TextField txfSearchBar;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Timer timer;
    private TimerTask task;
    private boolean running;
    private MovieModel movieModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        file = new File("mp4/CL - ‘HELLO BITCHES’  MV.mp4");
        movieModel = new MovieModel();
        showAllTables();
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

    public void fastForward(ActionEvent event) {
        mediaPlayer.seek(mediaPlayer.getCurrentTime()
                .add(Duration.seconds(10)));
    }

    public void fastRewind(ActionEvent event) {
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

    private void showAllTables() {
        try {
            showCategoryTable();
            showMoviesTable(new Category( 1,"all"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showCategoryTable() throws SQLException {
        tbvCategories.setFocusTraversable(false);
        tbcCategories.setResizable(false);

        tbcTitle.setCellValueFactory(new PropertyValueFactory<Category, String>("categoryname"));

        //tbvCategories.setItems(categoryModel.getAllCategories()); need categoryModel
        //TODO double click -> showMoviesTable(selectedCategory);
    }

    private void showMoviesTable(Category category) throws SQLException {

        tbvMovies.setFocusTraversable(false);

        tbcIMDB.setResizable(false);
        tbcUserRating.setResizable(false);
        tbcTitle.setResizable(false);

        tbcIMDB.setCellValueFactory(new PropertyValueFactory<Movie, String>("imdbrating"));
        tbcUserRating.setCellValueFactory(new PropertyValueFactory<Movie, String>("userrating"));
        tbcTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));


        if (category.getCategoryname().equalsIgnoreCase("all"))
            tbvMovies.setItems(movieModel.getAllMovies());
        else {
            movieModel.getMoviesByCategory(category);
        }
    }

}

