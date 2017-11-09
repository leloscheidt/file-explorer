package br.com.scheidt.fileexplorer.model.raw;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.scheidt.fileexplorer.model.Element;

/**
 * Simple implementation of the Element interface.
 */
public class RawElement implements Element {
    
    private final Map<String, String> values;

    public RawElement() {
        this.values = new LinkedHashMap<String,String>();
    }

    @Override
    public boolean has(String attribute) {
        return this.values.containsKey(attribute) && this.values.get(attribute) != null;
    }

    @Override
    public String get(String attribute) {
        return this.values.get(attribute);
    }

    @Override
    public void add(String attribute, String value) {
        this.values.put(attribute, value);
    }

    @Override
    public List<String> values() {
        List<String> list = new ArrayList<String>();
        
        for(String key : this.values.keySet()) {
            list.add(this.values.get(key));
        }
        
        return list;
    }
}
