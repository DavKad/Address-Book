package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.database.mvc.AddressMVC;
import utils.database.ormclasses.Address;
import utils.fxutils.FxUtilsConnection;

import java.io.IOException;

public class AddAddressController {

    @FXML
    private TextField streetField;

    @FXML
    private TextField localField;

    @FXML
    private TextField cityField;

    @FXML
    private Button addOwnerButton;

    @FXML
    private Button saveButton;


    @FXML
    public void initialize() {
        AddressMVC addressMVC = new AddressMVC();

        saveButton.disableProperty().bind(streetField.textProperty().isEmpty()
                .or(localField.textProperty().isEmpty()
                        .or(cityField.textProperty().isEmpty()))
        );

        addOwnerButton.disableProperty().bind(streetField.textProperty().isEmpty()
                .or(localField.textProperty().isEmpty()
                        .or(cityField.textProperty().isEmpty()))
        );
        saveButton.setOnAction(s -> {
            Address newAddress = new Address(streetField.getText(), localField.getText(), cityField.getText());
            addressMVC.addAddress(newAddress);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        });
        addOwnerButton.setOnAction(s -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(this.getClass().getResource("/FXML/AddOwnerWindow.fxml"));
                BorderPane pane = loader.load();
                AddOwnerController addOwnerController = loader.getController();
                addOwnerController.setAddressInformation(streetField.getText(), localField.getText(), cityField.getText());
                Stage stage = FxUtilsConnection.setStage(pane);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stageClose = (Stage) addOwnerButton.getScene().getWindow();
            stageClose.close();
        });
    }

}
