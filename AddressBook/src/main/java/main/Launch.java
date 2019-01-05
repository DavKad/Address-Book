package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import utils.database.Connection;
import utils.fxutils.FxUtilsConnection;

public class Launch extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage) throws Exception {
        Connection.initDatabase();
        primaryStage = FxUtilsConnection.loadFXML("/FXML/MainWindow.fxml", "Address catalog");
        primaryStage.show();

        primaryStage.setOnCloseRequest(s -> {
            Connection.closeConnection();
            Platform.exit();
        });
    }
}
