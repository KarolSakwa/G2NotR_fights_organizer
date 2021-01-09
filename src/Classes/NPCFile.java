package Classes;
import Controllers.MainController;


public class NPCFile {
    MainController mainController;
    public String NPCFolder;

    public NPCFile(MainController mainController) {
        this.mainController = mainController;
        NPCFolder = mainController.mainPath + "\\_Work\\Data\\Scripts\\Content\\Story\\NPC";
    }

    public String getNPCFileContent() {

    }

}
