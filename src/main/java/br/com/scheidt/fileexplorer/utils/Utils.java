package br.com.scheidt.fileexplorer.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    
    public static boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
    
    public static boolean checkFile(String filepath) {
        return !isBlank(filepath) && Files.exists(Paths.get(filepath)) && checkFile(new File(filepath));
    }
    
    public static boolean checkFile(File file) {
        return file != null && file.exists() && !file.isDirectory();
    }
}
