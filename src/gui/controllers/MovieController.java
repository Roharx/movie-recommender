package gui.controllers;


import be.Category;
import be.Movie;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Callable;

public class MovieController implements Initializable {
    //region FXML
    @FXML
    public TableView tbvCategories,
            tbvMovies;
    @FXML
    public TableColumn tbcCategories,
            tbcIMDB,
            tbcUserRating,
            tbcTitle;
    @FXML
    public AnchorPane window;
    @FXML
    public Button btnAddCategory,
            btnDeleteCategory,
            btnAddMovie,
            btnDeleteMovie,
            btnEditMovie;
    @FXML
    private Slider sliderTime;
    @FXML
    private Label labelCurrentTime,
            labelTotalTime;
    @FXML
    private Button buttonPps,
            resetButton;
    private ImageView ivPlay,
            ivPause;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ListView moviesView,
            categoryView;
    @FXML
    private MediaView mediaView;
    @FXML
    private TextField txtSearch;

    private boolean isPlaying;
    private boolean isEndOfVideo = false;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Timer timer;
    private TimerTask task;
    private boolean running;
    private MovieModel movieModel;
    private CategoryModel categoryModel;
    private AnchorPane popupContent;
    public MovieController movieController;

    private String path;

    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        file = new File("mp4/CL - ‘HELLO BITCHES’  MV.mp4");
        movieModel = new MovieModel();
        categoryModel = new CategoryModel();
        showAllTables();
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        playPauseMedia();
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    movieModel.search(newValue);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
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
                if (isPlaying) {
                    buttonPlay.setGraphic(ivPlay);
                    mediaPlayer.pause();
                    isPlaying = false;


                } else {
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
                if (!isChanging) {
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
                return getTime(mediaPlayer.getCurrentTime());
            }
        }, mediaPlayer.currentTimeProperty()));

    }


    private String getTime(Duration time) {
        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();

        if (seconds > 59) seconds = seconds % 60;
        if (minutes > 59) minutes = minutes % 60;
        if (hours > 59) hours = hours % 60;

        if (hours > 0) return String.format("%d:%02d:%02d",
                hours,
                minutes,
                seconds);
        else return String.format("%02d:%02d",
                minutes,
                seconds);

    }

    public void labelMatchEndVideo(String labelTime, String labelTotalTime) {
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

    public void fastForward(ActionEvent event) {
        mediaPlayer.seek(mediaPlayer.getCurrentTime()
                .add(Duration.seconds(10)));
    }

    public void fastRewind(ActionEvent event) {
        mediaPlayer.seek(mediaPlayer.getCurrentTime()
                .add(Duration.seconds(-10)));
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
        showCategoryTable();

        //TODO on category double click: display all movies of that category
        //TODO on movie double click: play selected movie


        showMovieTable(new Category(1, "All"));
    }

    private void showCategoryTable() {

        tbvCategories.setFocusTraversable(false);

        tbcCategories.setResizable(false);
        tbcCategories.setResizable(false);
        tbcCategories.setCellValueFactory(new PropertyValueFactory<Category, String>("categoryname"));

        try {
            tbvCategories.setItems(categoryModel.getAllCategories());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showMovieTable(Category category) {
        tbvMovies.setFocusTraversable(false);

        tbcIMDB.setResizable(false);
        tbcUserRating.setResizable(false);
        tbcTitle.setResizable(false);

        tbcIMDB.setCellValueFactory(new PropertyValueFactory<Category, String>("imdbrating"));
        tbcUserRating.setCellValueFactory(new PropertyValueFactory<Category, String>("userrating"));
        tbcTitle.setCellValueFactory(new PropertyValueFactory<Category, String>("title"));


        try {
            tbvMovies.setItems(movieModel.getAllMovies());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCategoryPressed(ActionEvent actionEvent) {
        try{
            displayAddCategoryPopup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteCategoryPressed(ActionEvent actionEvent) {
        Category selectedCategory = (Category) tbvCategories.getSelectionModel().getSelectedItem();
        try {
            categoryModel.deleteCategory(selectedCategory);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMoviePressed(ActionEvent actionEvent) {
        try {
            displayAddMoviePopup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void editMoviePressed(ActionEvent actionEvent) {
        try{
            displayEditMoviePopup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteMoviePressed(ActionEvent actionEvent) {
        Movie selectedMovie = (Movie) tbvMovies.getSelectionModel().getSelectedItem();
        try {
            movieModel.deleteMovie(selectedMovie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void displayAddMoviePopup() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/AddMovie.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add Movie");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }

    public void displayAddCategoryPopup() throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/AddCategory.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add Category");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }

    public void displayEditMoviePopup() throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/EditMovie.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Edit Movie");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }




    }



















