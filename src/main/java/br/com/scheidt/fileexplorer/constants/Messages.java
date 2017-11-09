package br.com.scheidt.fileexplorer.constants;

/**
 * Messages used by the application.
 */
public class Messages {
    
    public static final String SEPARATOR = "---------------------------";
    
    public static final String WELCOME = "Hi there! Welcome to the file explorer."
            + "\nTo start enter the location of the file to explore or press return to use the default file (cidades.csv).";
    
    public static final String FILE_NOT_FOUND = "Sorry, I can't find the file with the given path."
            + "\nCheck if the file is really there and try again. Bye!";
    
    public static final String FILE_FOUND = "Great! I found the file! Let's advance.";
    
    public static final String TYPE_HERE = "Type here: ";
    
    public static final String AVAILABLE_COMMANDS = "This are the available commands to explore the file, please choose one.";
    
    public static final String NO_COMMAND = "No command was entered, try again.";
    
    public static final String INVALID_COMMAND = "An invalid command was entered, try again.";
    
    public static final String PARSER_NOT_FOUND = "No parser was found for the given file.";
    
    public static final String WRONG_PARAMETER_NUMBER = "Wrong number of parameters";
    
    public static final String TOTAL_NUMBER_OF_RECORDS = "The total number of imported records is: %d.";
    
    public static final String TOTAL_NUMBER_OF_DISTINCT_RECORDS = "The total number of distinct values in the %s property is: %d.";
    
    public static final String ERROR_READ_FILE = "Error while reading the file.";
    
    public static final String ERROR_CLOSE_FILE = "Error while closing the file.";
    
    public static final String ERROR_EXECUTING_COMMAND = "Error while executing command.";
    
    public static final String EMPTY_RESULT = "Empty result set.";
    
    public static final String ATTRIBUTE_NOT_FOUND = "Property not found.";
    
    public static final String PARSER_NOT_INITIALIZED = "The parser is not initialized.";
    
    public static final String ENGINE_NOT_INITIALIZED = "The engine is not initialized.";

}
