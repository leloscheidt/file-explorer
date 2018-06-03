package br.com.scheidt.fileexplorer.engine.commands.count;

import java.util.List;
import java.util.stream.Stream;

import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.engine.commands.abstracts.AbstractStreamCommand;
import br.com.scheidt.fileexplorer.model.Element;
import br.com.scheidt.fileexplorer.parser.FileParser;

/**
 * Count all the elements from the file.
 */
public class CountAll extends AbstractStreamCommand {
    
    @Override
    public String name() {
        return "count *";
    }

    @Override
    public String description() {
        return "count * : \n\t Shows the total number of imported records.";
    }

    @Override
    protected String execute(FileParser parser, Stream<Element> stream, List<String> parameters) {
        long size = stream.count();
        
        return String.format(Messages.TOTAL_NUMBER_OF_RECORDS, size);
    }

    @Override
    protected int requiredParameters() {
        return 1;
    }
}
