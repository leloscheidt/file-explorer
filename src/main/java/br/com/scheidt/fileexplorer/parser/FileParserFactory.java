package br.com.scheidt.fileexplorer.parser;

import java.io.File;

import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.parser.csv.CsvParser;
import br.com.scheidt.fileexplorer.utils.Utils;

/**
 * Returns the proper file parser thats handles the specified file.
 */
public enum FileParserFactory {

    CSV {
        @Override
        public FileParser parser(File file) {
            return new CsvParser(file);
        }
    };

    /**
     * Returns the proper file parser thats handles the specified file.
     * 
     * @param file The file to parse.
     * 
     * @return The file parser.
     * 
     * @throws ApplicationException if couldn't find the parser.
     */
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

    /**
     * Check if the factory supports the given file and has a proper file parser.
     * 
     * @param file The file to parse.
     * 
     * @return true if the factory supports this kind of file. False otherwise.
     */
    public static boolean supports(File file) {
        
        if (Utils.checkFile(file)) {

            for (FileParserFactory factory : values()) {

                if (file.getName().toLowerCase().endsWith(factory.name().toLowerCase())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns a new file parser thats parse the specified kind of file.
     * 
     * @param file The file to parse.
     * 
     * @return The file parser.
     */
    public abstract FileParser parser(File file);

}
