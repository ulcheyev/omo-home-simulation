package cvut.omo.app_utils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {

    /**
     *
     * @param fileName
     * @param report
     * @throws IOException
     */
    public static void generateNewConsumptionReport(String fileName, String report) throws IOException {
        writeToFile(openFile("reports/", fileName), report);
    }

    /**
     *
     * @param fileName
     * @param report
     * @throws IOException
     */
    public static void generateNewReport(String fileName, String report) throws IOException {
        writeToFile(openFile("reports/", fileName), report);
    }

    /**
     *
     * @param fileName
     * @param text
     * @throws IOException
     */
    public static void generateNewDocumentation(String fileName, String text) throws IOException {
        writeToFile(openFile("documentation/", fileName), text);
    }

    /**
     *
     * @param fileName
     * @param text
     * @throws IOException
     */
    public static void generateNewEmail(String fileName, String text) throws IOException {
        writeToFile(openFile("email/", fileName), text);
    }

    /**
     *
     * @param path
     * @param fileName
     * @return
     * @throws IOException
     */
    private static File openFile(String path, String fileName) throws IOException {
        File file = new File(path+fileName+".txt");
        file.getParentFile().mkdirs(); //create parent directory
        file.createNewFile(); // if file already exists will do nothing
        return file;
    }

    /**
     *
     * @param file
     * @param string
     * @throws IOException
     */
    private static void writeToFile(File file, String string) throws IOException {
        java.io.FileWriter fileWriter = new java.io.FileWriter(file.getCanonicalPath());
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(string);
        printWriter.close();
    }

    /**
     *
     * @param directory
     * @throws IOException
     */
    public static void cleanDirectory(String directory) throws IOException {
        File file = new File(directory);
        FileUtils.cleanDirectory(file);
    }



}
