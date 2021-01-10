package Classes;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Helper {

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

}
