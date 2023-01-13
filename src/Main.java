import be.Category;
import be.Movie;
import dal.CategoryDAO;
import dal.MovieDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {

       launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("view/MovieRecommender.fxml"));
        primaryStage.setTitle(" Movie Application");
        primaryStage.setScene(new Scene(root,1200,600));
        primaryStage.show();

    }
}