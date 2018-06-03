package br.com.scheidt.fileexplorer.engine.commands;

import java.io.File;
import java.util.List;

import br.com.scheidt.fileexplorer.exception.ApplicationException;

/**
 * Represents a single command that is executed against the file. 
 */
public interface Command {
    
    /**
     * The name of the this command.
     * 
     * @return The name.
     */
    String name();
    
    /**
     * The description or utilization of this command.
     * @return
     */
    String description();

    /**
     * Execute the given parameters against the given file.
     * 
     * @param file The file to execute against.
     * @param parameters The parameters to execute this command.
     * 
     * @return The result of the execution.
     * 
     * @throws ApplicationException if couldn't execute this commands or if the given parameters are wrong.
     */
    String execute(File file, List<String> parameters) throws ApplicationException;
    
    /**
     * Validates if the given parameters are handle by this command.
     * 
     * @param parameters The parameters to handle.
     * 
     * @return true if this command handle this parameters. False otherwise.
     */
    boolean validate(List<String> parameters);
}
