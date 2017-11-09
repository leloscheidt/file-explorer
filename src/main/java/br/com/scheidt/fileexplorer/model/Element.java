package br.com.scheidt.fileexplorer.model;

import java.util.List;

/**
 * An element of the file.
 */
public interface Element {
    
    /**
     * Checks if this element has the given attribute.
     * 
     * @param attribute The attribute to check.
     * 
     * @return true if this element has the given attribute. False otherwise.
     */
    boolean has(String attribute);
    
    /**
     * Returns the value of the given attribute if it contains. Returns null otherwise.
     * 
     * @param attribute The attribute to get.
     * 
     * @return The value of the attribute.
     */
    String get(String attribute);
    
    /**
     * Returns all values contained by this element.
     * 
     * @return The values of this element.
     */
    List<String> values();
    
    /**
     * Adds a new attribute to this element with the given value.
     * 
     * @param attribute The name of the attribute.
     * @param value The value of the attribute.
     */
    void add(String attribute, String value);
}
