package br.com.scheidt.fileexplorer.engine.commands.abstracts;

import java.io.File;
import java.util.List;

import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.engine.commands.Command;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.parser.FileParser;
import br.com.scheidt.fileexplorer.parser.FileParserFactory;

/**
 * Generic command with basic functionality.
 */
public abstract class AbstractCommand implements Command {

    @Override
    public boolean validate(List<String> parameters) {
        return parameters != null && parameters.size() == this.requiredParameters();
    }
    
    @Override
    public String execute(File file, List<String> parameters) throws ApplicationException {

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
    
    /**
     * Execute the command with the given parser and parameters.
     * 
     * @param parser The parser to execute the command.
     * @param parameters The parameters of the execution.
     * 
     * @return The result of the execution.
     * 
     * @throws ApplicationException if couldn't execute this command.
     */
    protected abstract String execute(FileParser parser, List<String> parameters) throws ApplicationException;

    /**
     * Returns the number of required parameters of this command.
     * 
     * @return The number of required parameters.
     */
    protected abstract int requiredParameters();
}
