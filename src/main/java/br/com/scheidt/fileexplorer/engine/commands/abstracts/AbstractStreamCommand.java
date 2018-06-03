package br.com.scheidt.fileexplorer.engine.commands.abstracts;

import java.util.List;
import java.util.stream.Stream;

import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.model.Element;
import br.com.scheidt.fileexplorer.parser.FileParser;

/**
 * Generic command thats uses a stream from the file to execute.
 */
public abstract class AbstractStreamCommand extends AbstractCommand {

    @Override
    public String execute(FileParser parser, List<String> parameters) throws ApplicationException {
        
        try (Stream<Element> stream = parser.stream()) {
            return this.execute(parser, stream, parameters);
        }
    }

    /**
     * Execute the command with the given parser, parameters and the stream.
     * 
     * @param parser The parser to execute the command.
     * @param stream The stream from the file.
     * @param parameters The parameters of the execution.
     * 
     * @return The result of the execution.
     * 
     * @throws ApplicationException if couldn't execute this command.
     */
    protected abstract String execute(FileParser parser, Stream<Element> stream, List<String> parameters) throws ApplicationException;

}
