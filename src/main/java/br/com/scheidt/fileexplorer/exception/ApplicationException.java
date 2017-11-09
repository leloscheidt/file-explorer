package br.com.scheidt.fileexplorer.exception;

/**
 * Application exception that is used to handle flow errors.
 */
public class ApplicationException extends Exception {
    
    private static final long serialVersionUID = 2833378795082817641L;

    /**
     * Default constructor.
     * 
     * @param message The message to show.
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * Overloaded constructor.
     * 
     * @param message The message to show.
     * @param cause The cause of the exception.
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
