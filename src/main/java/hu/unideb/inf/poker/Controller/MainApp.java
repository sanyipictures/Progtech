package hu.unideb.inf.poker.Controller;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenuScene.fxml"));
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/MainMenuScene.fxml"));
        Parent root = fxmlloader.load();
        
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add("/styles/welcome.css");
        
        stage.setTitle("MainMenu");
        stage.setScene(scene);
        fxmlloader.<MainMenuController>getController().afterInitialize();
        stage.show();
        
    }
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
