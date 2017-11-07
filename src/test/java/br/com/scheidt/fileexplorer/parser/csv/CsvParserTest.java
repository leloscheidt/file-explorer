package br.com.scheidt.fileexplorer.parser.csv;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.file.TestFile;
import br.com.scheidt.fileexplorer.parser.FileParser;

public class CsvParserTest {
    
    @Test(expected = NullPointerException.class)
    public void testInitializeWithNullFile() throws ApplicationException {
        FileParser parser = new CsvParser(null);
        
        parser.initialize();
    }
    
    @Test(expected = ApplicationException.class)
    public void testInitializeWithInvalidFile() throws ApplicationException {
        File file = new File("invalid_file.csv");
        FileParser parser = new CsvParser(file);
        
        parser.initialize();
    }
    
    @Test
    public void testInitializeWithCorrectFile() throws ApplicationException {
        FileParser parser = new CsvParser(TestFile.csv());
        
        parser.initialize();
        
        assertEquals(5, parser.attributes().length);
    }
    
    @Test(expected = ApplicationException.class)
    public void testStreamWithoutInitialize() throws ApplicationException {
        FileParser parser = new CsvParser(null);
        
        parser.stream();
    }
}
