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
import Classes.NPCFile;

public class MainController {

    @FXML Label pathErrorLabel, mainPathLabel;
    @FXML Button selectMainPathButton;
    public File mainDirectory;
    public Preferences userPreferences;
    Stage mainStage;
    NPCFile npcFile;
    public String mainPath;

    public void initialize() {
        mainStage = new Stage();
        addMainPathLabelTextListener(mainPathLabel);

        // Loading user preferences
        userPreferences = Preferences.userNodeForPackage(this.getClass());
        mainPath = userPreferences.get("mainPath", "");
        mainPathLabel.setText(mainPath);

        setMainDirectory();
        npcFile = new NPCFile(this, "jorgen");

    }
    private void addMainPathLabelTextListener(Label label){
        label.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (mainPathLabel.getText() != "Unknown") {
                    pathErrorLabel.setVisible(false);
                    userPreferences.put("mainPath", mainPathLabel.getText());
                } else {
                    pathErrorLabel.setVisible(true);
                }
            }
        });
    }
    private void setMainDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        selectMainPathButton.setOnAction(e -> {
            mainDirectory = directoryChooser.showDialog(mainStage);
            mainPathLabel.setText(mainDirectory.toString());
        });
    }

}
