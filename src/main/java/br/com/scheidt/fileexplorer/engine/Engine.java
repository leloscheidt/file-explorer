package br.com.scheidt.fileexplorer.engine;

import java.io.File;
import java.util.List;

import br.com.scheidt.fileexplorer.engine.commands.Command;
import br.com.scheidt.fileexplorer.exception.ApplicationException;

public interface Engine {

    String help();

    String execute(String input) throws ApplicationException;

    void initialize(File file) throws ApplicationException;

    List<Command> commands();

    Command command(String name) throws ApplicationException;

    void add(Command command);

}
