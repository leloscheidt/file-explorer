package br.com.scheidt.fileexplorer.parser;

import java.io.File;

import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.parser.csv.CsvParser;
import br.com.scheidt.fileexplorer.utils.Utils;

public enum FileParserFactory {

    CSV {
        @Override
        public FileParser parser(File file) {
            return new CsvParser(file);
        }
    };

    public static FileParser newParser(File file) throws ApplicationException {
        
        if(!Utils.checkFile(file)) {
            throw new ApplicationException(Messages.FILE_NOT_FOUND); 
        }

        for (FileParserFactory factory : values()) {

            if (file.getName().toLowerCase().endsWith(factory.name().toLowerCase())) {
                return factory.parser(file);
            }
        }
       
        throw new ApplicationException(Messages.PARSER_NOT_FOUND);
    }

    public static boolean supports(File file) throws ApplicationException {
        
        if (Utils.checkFile(file)) {

            for (FileParserFactory factory : values()) {

                if (file.getName().toLowerCase().endsWith(factory.name().toLowerCase())) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public abstract FileParser parser(File file);

}
