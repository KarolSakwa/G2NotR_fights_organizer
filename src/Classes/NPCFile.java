package Classes;
import Controllers.MainController;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
            if (name.toString().contains(NPCName.toLowerCase())) {
                selectCorrectFile(name.toString());
            }
            }
    }
    private String selectCorrectFile(String name) {
        String correctFile = "";
        if (name.contains("jorgen"))
            correctFile = "vlk_4250_jorgen.d";
        else if (name.contains("talbin"))
            correctFile = "vlk_4130_talbin.d";
        else if (name.contains("thorus"))
            correctFile = "bdt_10014_addon_thorus.d";
        else if (name.contains("lares"))
            correctFile = "vlk_449_lares.d";
        else if (name.contains("jack"))
            correctFile = "vlk_444_jack.d";
        else if (name.contains("pardos"))
            correctFile = "strf_1127_addon_pardos_nw.d";
        else if (name.contains("tonak"))
            correctFile = "strf_1120_addon_tonak.d";
        else if (name.contains("telbor"))
            correctFile = "strf_1121_addon_telbor.d";
        else if (name.contains("monty"))
            correctFile = "strf_1119_addon_monty.d";
        else if (name.contains("patrick"))
            correctFile = "strf_1123_addon_patrick_nw.d";
        else if (name.contains("wolf"))
            correctFile = "sld_811_wolf.d";
        else if (name.contains("bennet"))
            correctFile = "sld_809_bennet.d";
        else if (name.contains("rod"))
            correctFile = "djg_702_rod.d";
        else if (name.contains("cipher"))
            correctFile = "djg_703_cipher.d";
        else if (name.contains("sylvio"))
            correctFile = "djg_700_sylvio.d";
        else if (name.contains("bullco"))
            correctFile = "djg_701_bullco.d";
        else if (name.contains("torlof"))
            correctFile = "sld_801_torlof.d";
        else if (name.contains("lee"))
            correctFile = "sld_800_lee.d";
        else if (name.contains("skip"))
            correctFile = "pir_1301_addon_skip_nw.d";
        else if (name.contains("greg"))
            correctFile = "pir_1300_addon_greg_nw.d";
        else if (name.contains("thief"))
            correctFile = "pc_thiefow.d";
        else if (name.contains("fighter"))
            correctFile = "pc_fighter_djg.d";
        else if (name.contains("psionic"))
            correctFile = "pc_psionic.d";
        else if (name.contains("pedro"))
            correctFile = "nov_600_pedro.d";
        else if (name.contains("mario"))
            correctFile = "none_101_mario_di.d";
        else if (name.contains("biff"))
            correctFile = "djg_713_biff.d";
        else if (name.contains("angar"))
            correctFile = "djg_705_angar.d";
        return correctFile;
    }

}
