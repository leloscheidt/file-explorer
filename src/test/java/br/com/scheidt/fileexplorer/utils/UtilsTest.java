package br.com.scheidt.fileexplorer.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import br.com.scheidt.fileexplorer.file.TestFile;

public class UtilsTest {
    
    @Test
    public void testIsBlankWithNull() {
        assertTrue(Utils.isBlank(null));
    }
    
    @Test
    public void testIsBlankWithEmpty() {
        assertTrue(Utils.isBlank(""));
    }
    
    @Test
    public void testIsBlankWithBlank() {
        assertTrue(Utils.isBlank("  "));
    }
    
    @Test
    public void testIsBlankWithString() {
        assertFalse(Utils.isBlank("not empty"));
    }
    
    @Test
    public void testCheckFileWithNullString() {
        assertFalse(Utils.checkFile((String)null));
    }
    
    @Test
    public void testCheckFileWithEmptyString() {
        assertFalse(Utils.checkFile(""));
    }
    
    @Test
    public void testCheckFileWithBlankString() {
        assertFalse(Utils.checkFile(" "));
    }
    
    @Test
    public void testCheckFileWithInvalidPath() {
        assertFalse(Utils.checkFile("invalid.path"));
    }
    
    @Test
    public void testCheckFileWithDirectorythPath() {
        String path = System.getProperty("java.home");
        
        assertFalse(Utils.checkFile(path));
    }
    
    @Test
    public void testCheckFileWithRealFilepath() {
        assertTrue(Utils.checkFile(TestFile.csv().getAbsolutePath()));
    }
    
    @Test
    public void testCheckFileWithNullFile() {
        assertFalse(Utils.checkFile((File)null));
    }
    
    @Test
    public void testCheckFileWithInvalidFile() {
        File file = new File("invalid.file");
        
        assertFalse(Utils.checkFile(file));
    }
    
    @Test
    public void testCheckFileWithDirectory() {
        String path = System.getProperty("java.home");
        
        assertFalse(Utils.checkFile(new File(path)));
    }
}
