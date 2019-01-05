package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import jdk.nashorn.internal.objects.annotations.Function;
import lombok.Data;
import utils.database.mvc.AddressMVC;
import utils.database.ormclasses.Address;
import utils.database.ormclasses.Owner;

@Data
public class AddOwnerController {

    @FXML
    private TextFlow streetText;

    @FXML
    private TextFlow localText;

    @FXML
    private TextFlow cityText;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField peselField;

    @FXML
    private Button saveAllButton;

    @Function
    private String getTextFromFlow(TextFlow flow) {
        StringBuilder value = new StringBuilder();
        for (Node x : flow.getChildren()) {
            if (x instanceof Text) {
                value.append(((Text) x).getText());
            }
        }
        return value.toString();
    }

    @Function
    void setAddressInformation(String street, String local, String city) {
        this.getStreetText().getChildren().add(new Text(street));
        this.getLocalText().getChildren().add(new Text(local));
        this.getCityText().getChildren().add(new Text(city));
    }

    @FXML
    public void initialize() {
        AddressMVC addressMVC = new AddressMVC();

        saveAllButton.disableProperty().bind(nameField.textProperty().isEmpty()
                .or(ageField.textProperty().isEmpty()
                        .or(peselField.textProperty().isEmpty())));

        saveAllButton.setOnAction(s -> {
            addressMVC.addAddressWithOwner(new Address(this.getTextFromFlow(streetText), this.getTextFromFlow(localText), this.getTextFromFlow(cityText)),
                    new Owner(nameField.getText(), Integer.parseInt(ageField.getText()), Long.parseLong(peselField.getText())));
            Stage stage = (Stage) saveAllButton.getScene().getWindow();
            stage.close();
        });
    }
}
