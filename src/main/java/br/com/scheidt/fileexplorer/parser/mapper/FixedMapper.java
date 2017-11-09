package br.com.scheidt.fileexplorer.parser.mapper;

import java.util.function.Function;

import br.com.scheidt.fileexplorer.model.Element;
import br.com.scheidt.fileexplorer.model.raw.RawElement;

/**
 * Maps the given string to an new element using a fixed separator.
 */
public class FixedMapper {

    private String[] attributes;
    private String separator;

    /**
     * Defaults constructor.
     * 
     * @param attributes The attributes of the file.
     * @param separator The separator.
     */
    public FixedMapper(String[] attributes, String separator) {
        this.attributes = attributes;
        this.separator = separator;
    }
    
    /**
     * Returns the function thats maps the stream of the file to an element.
     */
    public Function<String, Element> map = (line) -> {
        String[] parts = line.split(this.separator);
        
        Element element = new RawElement();
        
        for(int i = 0; i < attributes.length; i++) {
            element.add(this.attributes[i], parts[i]);
        }
        
        return element;
    };
}
