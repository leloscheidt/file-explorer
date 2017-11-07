package br.com.scheidt.fileexplorer.engine.commands.abstracts;

import java.io.File;

import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.engine.commands.Command;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.parser.FileParser;
import br.com.scheidt.fileexplorer.parser.FileParserFactory;

public abstract class AbstractCommand implements Command {

    @Override
    public boolean validate(String[] parameters) {
        return parameters != null && parameters.length == this.requiredParameters();
    }
    
    @Override
    public String execute(File file, String[] parameters) throws ApplicationException {

        if (!this.validate(parameters)) {
            throw new ApplicationException(Messages.WRONG_PARAMETER_NUMBER);
        }

        try {
            
            FileParser parser = FileParserFactory.newParser(file);
            
            parser.initialize();
            
            return this.execute(parser, parameters);
            
        } catch (NullPointerException e) {
            throw new ApplicationException(Messages.ERROR_EXECUTING_COMMAND, e);
        }
    }
    
    protected abstract String execute(FileParser parser, String[] parameters) throws ApplicationException;

    protected abstract int requiredParameters();
}
