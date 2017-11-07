package br.com.scheidt.fileexplorer.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.file.TestFile;
import br.com.scheidt.fileexplorer.parser.csv.CsvParser;

public class FileParserFactoryTest {
    
    @Test(expected = ApplicationException.class)
    public void testNewParserWithNull() throws ApplicationException {
        FileParserFactory.newParser(null);
    }
    
    @Test(expected = ApplicationException.class)
    public void testNewParserWithUnknowFile() throws ApplicationException {
        File file = new File("invalid.none");
        
        FileParserFactory.newParser(file);
    }
    
    @Test(expected = ApplicationException.class)
    public void testNewParserWithUnsupportedFile() throws ApplicationException {
        FileParserFactory.newParser(TestFile.unsupported());
    }
    
    @Test
    public void testNewParserWithCorrectCsvFile() throws ApplicationException {
        FileParser parser = FileParserFactory.newParser(TestFile.csv());
        
        assertEquals(CsvParser.class, parser.getClass());
    }
    
    @Test
    public void testSupportsWithNull() throws ApplicationException {
        assertFalse(FileParserFactory.supports(null));
        
    }
    
    @Test
    public void testSupportsWithUnknowFile() throws ApplicationException {
        File file = new File("invalid.none");
        
        assertFalse(FileParserFactory.supports(file));
    }
    
    @Test
    public void testSupportsWithCorrectCsvFile() throws ApplicationException {
        assertTrue(FileParserFactory.supports(TestFile.csv()));
    }
}
