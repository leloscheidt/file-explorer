package br.com.scheidt.fileexplorer.exception;

public class ApplicationException extends Exception {
    
    private static final long serialVersionUID = 2833378795082817641L;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
