package cvut.omo.device.pdf_documentation;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriterForDocumentation {

    public static void generateNewLog(String fileName, String report) throws IOException {
        File file = new File("documentation/"+fileName+".txt");
        file.getParentFile().mkdirs(); //create parent directory
        file.createNewFile(); // if file already exists will do nothing

        java.io.FileWriter fileWriter = new java.io.FileWriter(file.getCanonicalPath());
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(report);
        printWriter.close();
    }

}
