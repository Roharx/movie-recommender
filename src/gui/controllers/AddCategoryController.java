package gui.controllers;

import be.Category;
import gui.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddCategoryController {
    @FXML
    private Button btnOk,
            btnCancel;
    @FXML
    private TextField txfCategoryName;
    private CategoryModel categoryModel;
    private String titleOfMovie;

    public AddCategoryController() {
        categoryModel= new CategoryModel();
    }



    public void okPressed(ActionEvent actionEvent) throws IOException {
        try {
            categoryModel.createCategory(new Category(
                    categoryModel.getMaxID() +1,
                    txfCategoryName.getText()));
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
