package gui.controllers;

import be.Category;
import gui.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddCategoryController {
    @FXML
    private Button btnOk,
            btnCancel;
    @FXML
    private TextField txfCategoryName;
    @FXML
    private CategoryModel categoryModel;

    public void okPressed(ActionEvent actionEvent) throws SQLException {
        Stage stage = (Stage) btnOk.getScene().getWindow();
        Alert a = new Alert(Alert.AlertType.NONE); // New alert

        if(txfCategoryName.getText().trim().isEmpty()){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please fill in field");
            a.show();
        }else {
            categoryModel.createCategory(new Category(txfCategoryName.getText()));
            stage.close();
        }
    }

        public void cancelPressed(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
