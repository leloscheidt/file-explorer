package br.com.scheidt.fileexplorer.engine.commands.count;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.engine.commands.abstracts.AbstractStreamCommand;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.model.Element;
import br.com.scheidt.fileexplorer.parser.FileParser;

/**
 * Count the distinct attribute values from the file.
 */
public class CountDistinct extends AbstractStreamCommand {

    @Override
    public String name() {
        return "count distinct";
    }

    @Override
    public String description() {
        return "count distincy [property] : Shows the total number of distinct values for the given attribute.";
    }

    @Override
    protected String execute(FileParser parser, Stream<Element> stream, String[] parameters) throws ApplicationException {
        String attribute = parameters[1];

        if (parser.has(attribute)) {

            long size = stream
                    .filter(e -> e.has(attribute))
                    .filter(this.distinctBy(e -> e.get(attribute))).count();

            return String.format(Messages.TOTAL_NUMBER_OF_DISTINCT_RECORDS, attribute, size);

        }
        
        throw new ApplicationException(Messages.ATTRIBUTE_NOT_FOUND);
    }

    /**
     * Check if the given key already was seen by the stream.
     * 
     * @param keyExtractor The function to apply against the stream elements.
     * 
     * @return The execution of the function against one element of the stream.
     */
    private <T> Predicate<T> distinctBy(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = Collections.newSetFromMap(new ConcurrentHashMap<>());
        return t -> seen.add(keyExtractor.apply(t));
    }

    @Override
    protected int requiredParameters() {
        return 2;
    }
}
