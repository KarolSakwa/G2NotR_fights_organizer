package Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.function.Function;
import java.util.prefs.Preferences;

public class MainController {

    @FXML Label pathErrorLabel, mainPathLabel;
    @FXML Button selectMainPathButton;
    File mainDirectory;
    Preferences userPreferences = Preferences.userRoot();

    public void initialize() {
        Stage thisStage = new Stage();
        addMainPathLabelTextListener(mainPathLabel);
        DirectoryChooser directoryChooser = new DirectoryChooser();
        selectMainPathButton.setOnAction(e -> {
            mainDirectory = directoryChooser.showDialog(thisStage);
            mainPathLabel.setText(mainDirectory.toString());
        });
    }
    private void addMainPathLabelTextListener(Label label){
        label.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (mainPathLabel.getText() != "Unknown") {
                    pathErrorLabel.setVisible(false);
                } else {
                    pathErrorLabel.setVisible(true);
                    userPreferences.put("mainPathLabel", mainPathLabel.getText());
                    System.out.println(userPreferences.get("mainPathLabel", "YU"));
                }
            }
        });
    }


}
