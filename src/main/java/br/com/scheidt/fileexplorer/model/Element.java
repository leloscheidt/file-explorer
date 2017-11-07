package br.com.scheidt.fileexplorer.model;

import java.util.List;

public interface Element {
    
    boolean has(String attribute);
    
    String get(String attribute);
    
    List<String> values();
    
    void add(String attribute, String value);
}
