package gui.controllers;

import be.Category;
import be.Movie;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class AddCategoryController {
    @FXML
    private Button btnOk,
            btnCancel;
    @FXML
    private TextField txfCategoryName;
    CategoryModel categoryModel;

    public AddCategoryController() {
        categoryModel= new CategoryModel();
    }



    public void okPressed(ActionEvent actionEvent) throws IOException {
        try {
            categoryModel.createCategory(new Category(
                    categoryModel.getMaxID() +1,
                    txfCategoryName.getText()));
            closeWindow();
            categoryModel.getAllCategories();

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
