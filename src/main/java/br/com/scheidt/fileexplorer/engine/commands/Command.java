package br.com.scheidt.fileexplorer.engine.commands;

import java.io.File;

import br.com.scheidt.fileexplorer.exception.ApplicationException;

public interface Command {
    
    String name();
    
    String description();

    String execute(File file, String[] parameters) throws ApplicationException;
    
    boolean validate(String[] parameters);
}
