package br.com.scheidt.fileexplorer.engine.commands.filter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.scheidt.fileexplorer.constants.Constants;
import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.engine.commands.abstracts.AbstractStreamCommand;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.model.Element;
import br.com.scheidt.fileexplorer.parser.FileParser;

public class Filter extends AbstractStreamCommand {
    
    public String name() {
        return "filter";
    }

    public String description() {
        return "filter [property] [value] : Writes on the console every record with the given property value";
    }

    @Override
    public String execute(FileParser parser, Stream<Element> stream, String[] parameters) throws ApplicationException {
        String attribute = parameters[0];
        String value = parameters[1];
        
        if (parser.has(attribute)) {
            String result = stream
                    .filter(e -> e.has(attribute))
                    .filter(e -> e.get(attribute).toLowerCase().equals(value.toLowerCase()))
                    .map(line -> String.join(Constants.COMMA, line.values()))
                    .collect(Collectors.joining(Constants.BREAK_LINE));
            
            String header = String.join(Constants.COMMA, parser.attributes());
            
            return header + Constants.BREAK_LINE + result;
        }
        
        throw new ApplicationException(Messages.ATTRIBUTE_NOT_FOUND);
    }

    @Override
    protected int requiredParameters() {
        return 2;
    }
}
