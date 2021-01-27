package Classes;

import Controllers.MainController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class Helper {

    public static String[] doubledNPC = new String[]{"jorgen", "talbin", "thorus", "lares", "jack", "padros", "tonak", "telbor", "monty",
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

    public static void addMainPathLabelTextListener(Label mainPathLabel, Label errorLabel, Preferences preferences, MainController mainController) {
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
        for (File file : mainPathFolder.listFiles()) {
            if (file.toString().contains("_Work"))
                containsWorkFolder = true;
        }

        if (mainPathLabel.getText() == "Unknown") {
            errorLabel.setVisible(true);
        } else if (!containsWorkFolder) {
            errorLabel.setText("Your main path does not contain correct path to G2 NotR!");
            errorLabel.setVisible(true);
            preferences.put("mainPath", mainPathLabel.getText());
        } else {
            errorLabel.setVisible(false);
            preferences.put("mainPath", mainPathLabel.getText());
        }
    }

    public static String generateFightRoutine(String fileContent, String fighterNum) {
        Integer npcIDPartStartIndex = fileContent.toLowerCase().indexOf("id = ") + 5; //+5, because I want to get an index of the first character after what I have in double quotes
        String npcIDPartStart = fileContent.substring(npcIDPartStartIndex, fileContent.length());
        Integer npcIDPartEndIndex = npcIDPartStart.indexOf(";");
        String npcIDPart = npcIDPartStart.substring(0, npcIDPartEndIndex);
        String fightRoutine = "\nfunc void rtn_fight_" + npcIDPart + "()\n" +
                "{\n" +
                "\tta_stand_wp(8,0,0,10,\"" + fighterNum + "\");\n" +
                "\tta_stand_wp(0,10,8,0,\"" + fighterNum + "\");\n" +
                "};";
        return fightRoutine;
    }

    public static String getFightFileContent(MainController mainController) {
        String tempFileContent = "";
        try (FileReader fileStream = new FileReader(mainController.mainPath + "\\_Work\\Data\\Scripts\\Content\\Story\\B_Content\\B_Addon_PiratesGoHome.d");
             BufferedReader bufferedReader = new BufferedReader(fileStream)) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                tempFileContent += line + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFileContent;
    }

    public static String getReplacedFilePart(String fileContent, String replaceStartingFrom, String finishingChar) {
        Integer replacingPartIndexStart = fileContent.toLowerCase().indexOf(replaceStartingFrom) + replaceStartingFrom.length(); // + length, because I want to get index of first character after replaceFrom
        String replacingPartStart = fileContent.substring(replacingPartIndexStart, fileContent.length());
        Integer replacingPartIndexEnd = replacingPartStart.toLowerCase().indexOf(finishingChar);
        String replacingPart = replacingPartStart.substring(0, replacingPartIndexEnd);

        return replacingPart;
    }

    public static void replaceFilePart(String fileContent, String replaceStartingFrom, String replaceTo, String finishingChar) {
        Integer replacingPartIndexStart = fileContent.toLowerCase().indexOf(replaceStartingFrom) + replaceStartingFrom.length(); // + length, because I want to get index of first character after replaceFrom
        String replacingPartStart = fileContent.substring(replacingPartIndexStart, fileContent.length());
        Integer replacingPartIndexEnd = replacingPartStart.toLowerCase().indexOf(finishingChar);
        String replacingPart = replacingPartStart.substring(0, replacingPartIndexEnd);
        fileContent.replace(replacingPart, replaceTo);

    }

    public static String getFighterWPPart(String fileContent) {
        // searching for proper routine
        String routinePart = getReplacedFilePart(fileContent, "func void rtn_fight_", "};");
        // searching for WP
        String WPPart = getReplacedFilePart(routinePart, ",\"", "\");");

        return WPPart;
    }

        public static void replaceFighter(MainController mainController, String fighterNum) {
            String fightFileContent = getFightFileContent(mainController);
            replaceFilePart(fightFileContent, "F1 = Hlp_GetNpc(", "AHU", ");");

        }

}