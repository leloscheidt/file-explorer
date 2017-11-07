package br.com.scheidt.fileexplorer.engine.commands.exit;

import br.com.scheidt.fileexplorer.engine.commands.abstracts.AbstractCommand;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.parser.FileParser;

public class Exit extends AbstractCommand {
    
    public static final String NAME = "exit";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String description() {
        return "exit : Closes the application";
    }

    @Override
    protected int requiredParameters() {
        return 0;
    }

    @Override
    protected String execute(FileParser parser, String[] parameters) throws ApplicationException {
        return "Bye!";
    }
}
