package br.com.scheidt.fileexplorer.file;

import java.io.File;

public class TestFile {
    
    public static File csv() {
        String path = TestFile.class.getResource("test_data.csv").getFile();
        
        return new File(path);
    }

    public static File unsupported() {
        String path = TestFile.class.getResource("unsupported.none").getFile();
        
        return new File(path);
    }
}
