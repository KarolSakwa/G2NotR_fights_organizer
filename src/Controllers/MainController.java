package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;

public class MainController {

    @FXML Label pathErrorLabel;
    @FXML Button selectMainPathButton;

    public void initialize() {
        Stage thisStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        selectMainPathButton.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(thisStage);
        });
    }

}
