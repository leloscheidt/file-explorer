package br.com.scheidt.fileexplorer.engine.commands.attribute;

import java.util.List;

import br.com.scheidt.fileexplorer.constants.Constants;
import br.com.scheidt.fileexplorer.engine.commands.abstracts.AbstractCommand;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.parser.FileParser;

/**
 * Show the currents attributes from the file.
 */
public class Attribute extends AbstractCommand {
    
    @Override
    public String name() {
        return "attributes";
    }

    @Override
    public String description() {
        return "attributes : \n\t Shows the attributes of the given file";
    }

    @Override
    protected String execute(FileParser parser, List<String> parameters) throws ApplicationException {
        return String.join(Constants.BREAK_LINE, parser.attributes());
    }

    @Override
    protected int requiredParameters() {
        return 0;
    }
}
