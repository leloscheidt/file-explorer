package br.com.scheidt.fileexplorer.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Util class.
 */
public class Utils {
    
    /**
     * Check if the given text is null or empty after trim.
     * 
     * @param text The text to check.
     * 
     * @return true if the given text if null or empty. False otherwise.
     */
    public static boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
    
    /**
     * Check if the given file path exits as a file.
     * 
     * @param filepath The file path to check.
     * 
     * @return true if the given file path is a existing file.
     */
    public static boolean checkFile(String filepath) {
        return !isBlank(filepath) && Files.exists(Paths.get(filepath)) && checkFile(new File(filepath));
    }
    
    /**
     * Check if the given file exits.
     * 
     * @param file The file to check.
     * 
     * @return true if the given file exists and is not a directory. False otherwise.
     */
    public static boolean checkFile(File file) {
        return file != null && file.exists() && !file.isDirectory();
    }
}
