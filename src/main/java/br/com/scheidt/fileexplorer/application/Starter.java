package br.com.scheidt.fileexplorer.application;

import br.com.scheidt.fileexplorer.engine.executor.EngineExecutor;
import br.com.scheidt.fileexplorer.view.console.ConsoleView;

public class Starter {
    
    public static void main(String[] args) {
        
        App application = new App(new EngineExecutor(), new ConsoleView());
        
        application.start();
    }

}
