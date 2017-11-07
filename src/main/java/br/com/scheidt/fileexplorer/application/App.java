package br.com.scheidt.fileexplorer.application;

import java.io.File;

import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.engine.Engine;
import br.com.scheidt.fileexplorer.engine.commands.exit.Exit;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.utils.Utils;
import br.com.scheidt.fileexplorer.view.View;

public class App {
    
    private static final String DEFAULT_FILEPATH = "assets/cidades.csv";
    
    private View view;

    private Engine engine;

    public App(Engine engine, View view) {
        this.view = view;
        this.engine = engine;
    }
    
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
    
    public void initialize(String pathname) throws ApplicationException {
        if(!Utils.checkFile(pathname)) {
            throw new ApplicationException(Messages.FILE_NOT_FOUND);
        }

        File file = new File(pathname);
        
        this.engine.initialize(file);
    }
    
    private void show(String message) {
        this.view.show(message);
    }
    
    private String read() {
        return this.view.read();
    }
}
