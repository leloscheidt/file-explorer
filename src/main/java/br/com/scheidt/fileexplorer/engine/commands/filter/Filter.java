package br.com.scheidt.fileexplorer.engine.commands.filter;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.scheidt.fileexplorer.constants.Constants;
import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.engine.commands.abstracts.AbstractStreamCommand;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.model.Element;
import br.com.scheidt.fileexplorer.parser.FileParser;

/**
 * Shows the elements with the given attribute value.
 */
public class Filter extends AbstractStreamCommand {

    @Override
    public String name() {
        return "filter";
    }

    @Override
    public String description() {
        return "filter [property] [value] : \n\t Writes on the console every record with the given property value";
    }

    @Override
    public String execute(FileParser parser, Stream<Element> stream, List<String> parameters) throws ApplicationException {
    	parameters = new ArrayList<>(parameters);
    	
        String attribute = parameters.remove(0);
        String value = String.join(Constants.SPACE, parameters);
        
        if (parser.has(attribute)) {
            String result = stream
                    .filter(e -> e.has(attribute))
                    .filter(e -> this.normalize(e.get(attribute)).equalsIgnoreCase(this.normalize(value)))
                    .map(line -> String.join(Constants.COMMA, line.values()))
                    .collect(Collectors.joining(Constants.BREAK_LINE));
            
            String header = String.join(Constants.COMMA, parser.attributes());
            
            return header + Constants.BREAK_LINE + result;
        }
        
        throw new ApplicationException(Messages.ATTRIBUTE_NOT_FOUND);
    }
    
    /**
     * Normalize the given text.
     * 
     * @param text The text to normalize.
     * 
     * @return the normalized text.
     */
    private String normalize(String text) {
    	if(text != null) {
    		return Normalizer.normalize(text, Normalizer.Form.NFD)
    				.replaceAll(Constants.NOT_ASCII, Constants.EMPTY)
    				.trim();
    	}
    	
    	return "";
    }
    
    @Override
	public boolean validate(List<String> parameters) {
		return parameters != null && parameters.size() >= this.requiredParameters();
	}

	@Override
    protected int requiredParameters() {
        return 2;
    }
}
