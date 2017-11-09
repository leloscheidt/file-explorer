package br.com.scheidt.fileexplorer.parser;

import java.util.stream.Stream;

import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.model.Element;

/**
 * Reads a file and streams it elements.
 */
public interface FileParser {

    /**
     * Reads a file and streams it elements.
     * 
     * @return The stream of elements of the file.
     * 
     * @throws ApplicationExceptionif couldn't stream the file.
     */
    Stream<Element> stream() throws ApplicationException;

    /**
     * Returns all the elements attributes of the file.
     * 
     * @return The attributes.
     */
    String[] attributes();

    /**
     * Initialize this parser.
     * 
     * @throws ApplicationException if couldn't find the file.
     */
    void initialize() throws ApplicationException;

    /**
     * Check if the file has the given attribute.
     * 
     * @param attribute The attribute to check.
     * 
     * @return true if the file has the given attribute. False otherwise.
     */
    boolean has(String attribute);
}
