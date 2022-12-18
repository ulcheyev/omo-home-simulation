package cvut.omo.app_utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {

    public static void generateNewLog(String fileName, String report) throws IOException {
        File file = new File("reports/"+fileName+".txt");
        file.getParentFile().mkdirs(); //create parent directory
        file.createNewFile(); // if file already exists will do nothing

        java.io.FileWriter fileWriter = new java.io.FileWriter(file.getCanonicalPath());
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(report);
        printWriter.close();
    }
}
