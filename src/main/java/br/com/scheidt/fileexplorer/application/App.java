package br.com.scheidt.fileexplorer.application;

import java.io.File;

import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.engine.Engine;
import br.com.scheidt.fileexplorer.engine.commands.exit.Exit;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.utils.Utils;
import br.com.scheidt.fileexplorer.view.View;

/**
 * Main application class thats controls flow.
 */
public class App {
    
    private static final String DEFAULT_FILEPATH = "assets/cidades.csv";
    
    private View view;

    private Engine engine;

    /**
     * Default constructor.
     * 
     * @param engine The engine thats performs the commands.
     * @param view The output view.
     */
    public App(Engine engine, View view) {
        this.view = view;
        this.engine = engine;
    }
    
    /**
     * Start the application flow.
     */
    public void start() {
        this.show(Messages.WELCOME);
        
        String filepath = this.read();
        
        if(Utils.isBlank(filepath)) {
            filepath = DEFAULT_FILEPATH;
        }
        
        try {
            this.initialize(filepath);

            this.show(Messages.FILE_FOUND);

            this.showCommands();
            
        } catch (ApplicationException e) {
            this.show(e.getMessage());
        }
    }
    
    /**
     * Shows and handle the commands inputs.
     */
    public void showCommands() {
        String input = null;
        String result = null;
        
        do {
            this.show(Messages.SEPARATOR);
            this.show(Messages.AVAILABLE_COMMANDS);
            this.show(this.engine.help());
            
            input = this.read();
            
            try {
                if(!Utils.isBlank(input)) {
                    
                    result = this.engine.execute(input);
                    
                    this.show(result);
                    
                } else {
                    this.show(Messages.NO_COMMAND);
                }
                
            } catch (ApplicationException e) {
                this.show(e.getMessage());
            }
            
        } while(!input.equals(Exit.NAME));
        
        this.view.clear();
    }
    
    /**
     * Initialize the engine with the given pathname file.
     * 
     * @param pathname The path of the file to read.
     * 
     * @throws ApplicationException if couldn't find the file.
     */
    public void initialize(String pathname) throws ApplicationException {
        if(!Utils.checkFile(pathname)) {
            throw new ApplicationException(Messages.FILE_NOT_FOUND);
        }

        File file = new File(pathname);
        
        this.engine.initialize(file);
    }
    
    /**
     * Shows messages to the output.
     * 
     * @param message The message to show.
     */
    private void show(String message) {
        this.view.show(message);
    }
    
    /**
     * Reads from the input.
     * 
     * @return the inputed command.
     */
    private String read() {
        return this.view.read();
    }
}
