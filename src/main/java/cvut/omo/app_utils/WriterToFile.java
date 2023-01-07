package cvut.omo.app_utils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class represents write to file function
 */
public class WriterToFile {


    /**
     * Generates report from to the specified file name with specified text report
     * @param fileName
     * @param report report text
     * @throws IOException when the directory does not exist
     */
    public static void generateNewReport(String fileName, String report) throws IOException {
        writeToFile(openFile("reports/", fileName), report);
    }

    /**
     * Generates documentation from to the specified file name with specified text documentation
     * @param fileName file name
     * @param text documentation text
     * @throws IOException when the directory does not exist
     */
    public static void generateNewDocumentation(String fileName, String text) throws IOException {
        writeToFile(openFile("documentation/", fileName), text);
    }


    /**
     * Open file to write
     * @param path path to file
     * @param fileName file name
     * @return
     * @throws IOException when the directory does not exist
     */
    private static File openFile(String path, String fileName) throws IOException {
        File file = new File(path+fileName+".txt");
        file.getParentFile().mkdirs();
        file.createNewFile();
        return file;
    }

    /**
     * Write to file
     * @param file to write
     * @param string text to write to file
     * @throws IOException
     */
    private static void writeToFile(File file, String string) throws IOException {
        java.io.FileWriter fileWriter = new java.io.FileWriter(file.getCanonicalPath());
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(string);
        printWriter.close();
    }

    /**
     * Clean directory
     * @param directory to clean
     * @throws IOException
     */
    public static void cleanDirectory(String directory) throws IOException {
        File file = new File(directory);
        FileUtils.cleanDirectory(file);
    }



}
