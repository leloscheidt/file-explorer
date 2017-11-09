package br.com.scheidt.fileexplorer.view;

/**
 * Class thats shows and read from and to the user.
 */
public interface View {

    /**
     * Shows a message to the user.
     * 
     * @param message
     */
    void show(String message);

    /**
     * Reads from the user.
     * 
     * @return The user input.
     */
    String read();

    /**
     * Clears and close the view.
     */
    void clear();

}
