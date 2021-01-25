package Classes;

import Controllers.MainController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class Helper {

    public static String[] doubledNPC = new String[] {"jorgen", "talbin", "thorus", "lares", "jack", "padros", "tonak", "telbor", "monty",
            "patrick", "wolf", "bennet", "rod", "cipher", "sylvio", "bullco", "torlof", "lee", "skip", "greg", "thief", "fighter", "mage", "psionic", "pedro", "mario", "biff", "angar"};

    public static List listFilesForFolder(String path) {
        Path folderPath = Paths.get(path);
        File folder = folderPath.toFile();
        List<String> allFiles = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry.toString());
            } else {
                allFiles.add(fileEntry.getName());
            }
        }
        return allFiles;
    }

    public static void addMainPathLabelTextListener(Label mainPathLabel, Label errorLabel, Preferences preferences, MainController mainController){
        mainPathLabel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                checkMainPath(mainPathLabel, errorLabel, preferences, mainController);
            }
        });
    }

    public static void checkMainPath(Label mainPathLabel, Label errorLabel, Preferences preferences, MainController mainController) {
        File mainPathFolder = Paths.get(mainPathLabel.getText()).toFile();
        Boolean containsWorkFolder = false;
        for (File file: mainPathFolder.listFiles()) {
            if (file.toString().contains("_Work"))
                containsWorkFolder = true;
        }

        if (mainPathLabel.getText() == "Unknown") {
            errorLabel.setVisible(true);
        } else if (!containsWorkFolder){
            errorLabel.setText("Your main path does not contain correct path to G2 NotR!");
            errorLabel.setVisible(true);
            preferences.put("mainPath", mainPathLabel.getText());
        }
        else {
            errorLabel.setVisible(false);
            preferences.put("mainPath", mainPathLabel.getText());
        }
    }

    public static String generateFightRoutine(String fileContent) {
        Integer npcIDPartStartIndex = fileContent.toLowerCase().indexOf("id = ") + 5; //+5, because I want to get an index of the first character after what I have in double quotes
        String npcIDPartStart = fileContent.substring(npcIDPartStartIndex, fileContent.length());
        Integer npcIDPartEndIndex = npcIDPartStart.indexOf(";");
        String npcIDPart = npcIDPartStart.substring(0, npcIDPartEndIndex);
        String fightRoutine = "\nfunc void rtn_fight_" + npcIDPart + "()\n" +
                "{\n" +
                "\tta_stand_wp(8,0,0,10,\"F1\");\n" +
                "\tta_stand_wp(0,10,8,0,\"F1\");\n" +
                "};";
        return fightRoutine;
    }

}
