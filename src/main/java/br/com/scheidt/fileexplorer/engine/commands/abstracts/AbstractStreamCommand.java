package br.com.scheidt.fileexplorer.engine.commands.abstracts;

import java.util.stream.Stream;

import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.model.Element;
import br.com.scheidt.fileexplorer.parser.FileParser;

public abstract class AbstractStreamCommand extends AbstractCommand {

    @Override
    public String execute(FileParser parser, String[] parameters) throws ApplicationException {
        
        try (Stream<Element> stream = parser.stream()) {
            return this.execute(parser, stream, parameters);
        }
    }

    protected abstract String execute(FileParser parser, Stream<Element> stream, String[] parameters) throws ApplicationException;

}
