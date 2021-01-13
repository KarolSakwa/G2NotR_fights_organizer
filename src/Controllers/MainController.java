package Controllers;

import Classes.Helper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.function.Function;
import java.util.prefs.Preferences;
import Classes.NPCFile;

public class MainController {

    @FXML Label pathErrorLabel, mainPathLabel;
    @FXML Button selectMainPathButton;
    @FXML TextField fighter1TextField, fighter2TextField;
    public File mainDirectory;
    public Preferences userPreferences;
    Stage mainStage;
    NPCFile npcFile;
    public String mainPath;


    public void initialize() {
        mainStage = new Stage();
        userPreferences = Preferences.userNodeForPackage(this.getClass());
        Helper.addMainPathLabelTextListener(mainPathLabel, pathErrorLabel, userPreferences);

        // Loading user preferences
        mainPath = userPreferences.get("mainPath", "");
        mainPathLabel.setText(mainPath);

        setMainDirectory();
        npcFile = new NPCFile(this, "psionic");
    }

    private void setMainDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        selectMainPathButton.setOnAction(e -> {
            mainDirectory = directoryChooser.showDialog(mainStage);
            mainPathLabel.setText(mainDirectory.toString());
        });
    }

}
