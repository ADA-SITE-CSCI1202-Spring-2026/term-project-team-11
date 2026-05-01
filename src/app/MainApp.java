package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(
                MainApp.class.getResource("/ui/DashboardView.fxml")
        );

       
        Scene scene = new Scene(loader.load(), 800, 600); 

        
        
        scene.getStylesheets().add(getClass().getResource("/ui/style.css").toExternalForm());

        
        stage.setTitle("Ares Base");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}