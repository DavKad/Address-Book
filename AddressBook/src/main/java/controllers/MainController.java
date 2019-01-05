package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import utils.database.fx.AddressFx;
import utils.database.mvc.AddressMVC;
import utils.fxutils.FxUtilsConnection;

import java.io.IOException;
import java.sql.SQLException;


public class MainController {

    @FXML
    public TextField street;

    @FXML
    public TextField owner;

    @FXML
    public TableColumn<AddressFx, Number> idColumn;

    @FXML
    public TableColumn<AddressFx, String> streetColumn;

    @FXML
    public TableColumn<AddressFx, String> localColumn;

    @FXML
    public TableColumn<AddressFx, String> cityColumn;

    @FXML
    public TableColumn<AddressFx, String> ownerColumn;

    @FXML
    public TextField local;

    @FXML
    public TextField city;

    @FXML
    private TableView<AddressFx> tableView;

    @FXML
    private MenuItem closeButton;

    @FXML
    private MenuItem aboutButton;

    @FXML
    private Button searchButton;

    @FXML
    public Button addAddressButton;

    @Getter
    private AddressMVC addressMVC;

    private ObservableList<AddressFx> addressesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        this.addressMVC = new AddressMVC();
        try {
            addressesList = this.addressMVC.initTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Table listing
        this.idColumn.setCellValueFactory(s -> s.getValue().getId());
        this.streetColumn.setCellValueFactory(s -> s.getValue().getStreet());
        this.localColumn.setCellValueFactory(s -> s.getValue().getLocal());
        this.cityColumn.setCellValueFactory(s -> s.getValue().getCity());
        this.ownerColumn.setCellValueFactory(s -> s.getValue().getOwnerName());
        tableView.setItems(addressesList);


        //Disable searchButton, when address or owner is not given.
        searchButton.disableProperty().bind(street.textProperty().isEmpty()
                .and(local.textProperty().isEmpty()
                        .and(city.textProperty().isEmpty()
                                .and(owner.textProperty().isEmpty()))));

        //Refresh tableView if no address or owner is not given.
        searchButton.disabledProperty().addListener(s -> {
            if(searchButton.disabledProperty().getValue()){
                try {
                    addressesList = this.addressMVC.initTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        searchButton.setOnAction(s -> {
            addressesList.clear();
            try {
                addressesList = addressMVC.findAddress(street.getText(), owner.getText(), local.getText(), city.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            tableView.setItems(addressesList);
        });

        closeButton.setOnAction(s -> Platform.exit());
        aboutButton.setOnAction(s -> System.out.println("About app..."));

        addAddressButton.setOnAction(s -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(this.getClass().getResource("/FXML/AddAddressWindow.fxml"));
                BorderPane pane = loader.load();
                Stage stage = FxUtilsConnection.setStage(pane);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
