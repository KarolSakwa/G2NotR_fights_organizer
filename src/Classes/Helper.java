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

    public static void addMainPathLabelTextListener(Label label, Label errorLabel, Preferences preferences, MainController mainController){
        label.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (label.getText() == "Unknown") {
                    errorLabel.setVisible(true);
                } else if (!listFilesForFolder(mainController.mainPath).contains("_Work")){
                    System.out.println(listFilesForFolder(mainController.mainPath));
                    errorLabel.setText("Your main path does not contain correct path to G2 NotR!");
                    errorLabel.setVisible(true);
                    preferences.put("mainPath", label.getText());
                }
                else {
                    errorLabel.setVisible(false);
                    preferences.put("mainPath", label.getText());
                }
            }
        });
    }

}
