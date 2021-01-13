package Classes;

import Controllers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    Window window = new Window();
    @Override
    public void start(Stage primaryStage) throws Exception{
        window.createWindow("../fxml/main.fxml", false, primaryStage, false, 1000, 1000);
        //MainController mainController = new MainController();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
