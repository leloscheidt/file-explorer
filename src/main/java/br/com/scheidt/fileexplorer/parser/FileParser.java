package br.com.scheidt.fileexplorer.parser;

import java.util.stream.Stream;

import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.model.Element;

public interface FileParser {

    Stream<Element> stream() throws ApplicationException;

    String[] attributes();

    void initialize() throws ApplicationException;

    boolean has(String attribute);
}
