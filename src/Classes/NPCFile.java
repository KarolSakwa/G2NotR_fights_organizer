package Classes;
import Controllers.MainController;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class NPCFile {
    MainController mainController;
    String NPCName;
    public String NPCFolder;

    public NPCFile(MainController mainController, String NPCName) {
        this.mainController = mainController;
        this.NPCName = NPCName;

        NPCFolder = mainController.mainPath + "\\_Work\\Data\\Scripts\\Content\\Story\\NPC";
        getNPCFileContent();
    }

    private void getNPCFileContent() {
        for (Object name: Helper.listFilesForFolder(NPCFolder)) {
            if (name.toString().contains(NPCName)){
                System.out.println(name + "\n");
            }
            else {
                System.out.println("AHU \n");
            }
        }
    }

}
