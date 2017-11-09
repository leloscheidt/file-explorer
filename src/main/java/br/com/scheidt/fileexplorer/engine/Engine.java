package br.com.scheidt.fileexplorer.engine;

import java.io.File;
import java.util.List;

import br.com.scheidt.fileexplorer.engine.commands.Command;
import br.com.scheidt.fileexplorer.exception.ApplicationException;

/**
 * Engine interface thats handles the commands sent and results.
 */
public interface Engine {

    /**
     * Returns the description of the currents loaded commands.
     * 
     * @return The description.
     */
    String help();

    /**
     * Tries to execute the given input against the loaded commands.
     * 
     * @param input The input to execute.
     * 
     * @return The result of the command execution.
     * 
     * @throws ApplicationException if couldn't execute the command or couldn't parse the command.
     */
    String execute(String input) throws ApplicationException;

    /**
     * Initialize the engine with the given file.
     * 
     * @param file The file used to run the commands against.
     * 
     * @throws ApplicationException if couldn't find the file.
     */
    void initialize(File file) throws ApplicationException;

    /**
     * Returns the current loaded commands.
     * 
     * @return The commands.
     */
    List<Command> commands();

    /**
     * Returns the command with the given name.
     * 
     * @param name The name to find the command.
     * 
     * @return The command.
     * 
     * @throws ApplicationException if couldn't find the command with the given name.
     */
    Command command(String name) throws ApplicationException;

    /**
     * Adds the given command on the engine.
     * 
     * @param command The command to add.
     */
    void add(Command command);

}
