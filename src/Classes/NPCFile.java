package Classes;
import Controllers.MainController;


public class NPCFile {
    MainController mainController;

    public NPCFile(MainController mainController) {
        this.mainController = mainController;

        String NPCFolder = mainController.mainDirectory.toString();
        System.out.println(NPCFolder);
    }

}
