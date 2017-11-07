package br.com.scheidt.fileexplorer.parser.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import br.com.scheidt.fileexplorer.constants.Constants;
import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.model.Element;
import br.com.scheidt.fileexplorer.parser.FileParser;
import br.com.scheidt.fileexplorer.parser.mapper.FixedMapper;

public class CsvParser implements FileParser {
    
    private File file;
    private String[] header;
    
    public CsvParser(File file) {
        this.file = file;
    }
    
    @Override
    public void initialize() throws ApplicationException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));
            
            this.header = reader.lines().findFirst().get().split(Constants.COMMA);
            
            reader.close();
            
        } catch (FileNotFoundException e) {
            throw new ApplicationException(Messages.ERROR_READ_FILE, e);
        
        } catch (IOException e) {
            throw new ApplicationException(Messages.ERROR_CLOSE_FILE, e);
        }
    }

    @Override
    public Stream<Element> stream() throws ApplicationException {
        
        if(this.header == null) {
            throw new ApplicationException(Messages.PARSER_NOT_INITIALIZED);
        }
        
        FixedMapper mapper = new FixedMapper(this.header, Constants.COMMA);
        
        try {
            Stream<Element> stream = Files.lines(Paths.get(this.file.getAbsolutePath()))
                    .skip(1)
                    .filter(
                            line -> line != null && !line.trim().isEmpty())
                    .map(mapper.map);
                        
            return stream;
            
        } catch (IOException e) {
            throw new ApplicationException(Messages.ERROR_READ_FILE, e);
        }
    }

    @Override
    public String[] attributes() {
        return this.header;
    }

    @Override
    public boolean has(String attribute) {
        
        if(this.header != null) {
            return Arrays.asList(this.header).contains(attribute);
        } 
        
        return false;
    }
}
