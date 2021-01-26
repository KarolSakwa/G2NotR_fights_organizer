package Controllers;

import Classes.Helper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import Classes.NPCFile;

import javax.xml.soap.Text;

public class MainController {

    @FXML Label pathErrorLabel, mainPathLabel;
    @FXML Button selectMainPathButton, submitButton;
    @FXML TextField fighter1TextField, fighter2TextField;
    public File mainDirectory;
    public Preferences userPreferences;
    Stage mainStage;
    NPCFile fighter1File, fighter2File;
    public String mainPath;


    public void initialize() {
        mainStage = new Stage();
        userPreferences = Preferences.userNodeForPackage(this.getClass());
        mainPath = userPreferences.get("mainPath", "");
        mainPathLabel.setText(mainPath);
        Helper.checkMainPath(mainPathLabel, pathErrorLabel, userPreferences, this);
        Helper.addMainPathLabelTextListener(mainPathLabel, pathErrorLabel, userPreferences, this);

        // Loading user preferences


        submitButton.setOnAction(e -> {
            try {
                replaceFileContent(fighter1File, fighter1TextField, "", 0);
                replaceFileContent(fighter2File, fighter2TextField, "", 0);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        setMainDirectory();
    }

    private void setMainDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        selectMainPathButton.setOnAction(e -> {
            mainDirectory = directoryChooser.showDialog(mainStage);
            mainPathLabel.setText(mainDirectory.toString());
        });
    }

    private void replaceFileContent(NPCFile npcFile, TextField textField, String role, Integer ID) throws IOException {
        String fighterNum = textField == fighter1TextField ? "F1" : "F2";
        npcFile = new NPCFile(this, textField.getText());
        String fileContent = npcFile.getNPCFileContent();
        String fightRoutine = Helper.generateFightRoutine(fileContent, fighterNum);
        Boolean hasFightRoutine = fileContent.toLowerCase().contains("func void rtn_fight_");

        // detecting proper routine waypoint part of the file
        if (hasFightRoutine) {
            Integer fightRoutinePartStartIndex = fileContent.toLowerCase().indexOf("func void rtn_fight_");
            String fightRoutinePartStart = fileContent.substring(fightRoutinePartStartIndex, fileContent.length());
            Integer fightRoutinePartIndexEnd = fightRoutinePartStart.toLowerCase().indexOf("};");
            String fightRoutinePart = fightRoutinePartStart.substring(0, fightRoutinePartIndexEnd);
            Integer fighterWPPartIndexStart = fightRoutinePart.indexOf(",\"") + 2; // +2, because I want to get an index of the first character inside double quotes
            String fighterWPPartStart = fightRoutinePart.substring(fighterWPPartIndexStart, fightRoutinePart.length());
            Integer fighterWPPartIndexEnd = fighterWPPartStart.indexOf("\");");
            String fighterWPPart = fighterWPPartStart.substring(0, fighterWPPartIndexEnd);

            npcFile.setNPCFileContent(fileContent.replace(fighterWPPart, "fighterNum"), false); //
        }
        else {
            npcFile.setNPCFileContent(Helper.generateFightRoutine(fileContent, fighterNum), true);
        }
    }
}
