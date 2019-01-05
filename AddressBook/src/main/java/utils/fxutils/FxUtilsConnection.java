package utils.fxutils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FxUtilsConnection {
    private static final Image image = new Image(FxUtilsConnection.class.getResource("/images/icon.png").toString());

    public static Stage loadFXML(String fxmlPath, String windowTittle) throws IOException {
        FXMLLoader loader = new FXMLLoader(FxUtilsConnection.class.getResource(fxmlPath));
        BorderPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setTitle(windowTittle);
        stage.getIcons().add(image);
        stage.setScene(scene);
        return stage;
    }

    public static Stage setStage(Pane pane){
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setTitle("Add street");
        stage.getIcons().add(new Image(FxUtilsConnection.class.getResource("/images/icon.png").toString()));
        stage.setScene(scene);
        return stage;
    }
}
