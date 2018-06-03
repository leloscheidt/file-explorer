package br.com.scheidt.fileexplorer.engine.commands.filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import br.com.scheidt.fileexplorer.engine.commands.Command;

public class FilterTest {
    
private Command command;
    
    @Before
    public void setUp() {
        this.command = new Filter();
    }
    
    @Test
    public void testValidateWithNull() {
        assertFalse(this.command.validate(null));
    }
    
    @Test
    public void testValidateWithEmptyArray() {
        assertFalse(this.command.validate(new ArrayList<>(0)));
    }
    
    @Test
    public void testValidateWithLesserNumberOfParameterArray() {
        assertFalse(this.command.validate(Arrays.asList("1")));
    }
    
    @Test
    public void testValidateWithMoreNumberOfParameterArray() {
        assertTrue(this.command.validate(Arrays.asList("1","2")));
        assertTrue(this.command.validate(Arrays.asList("1","2","3")));
        assertTrue(this.command.validate(Arrays.asList("1","2","3","4")));
    }
    
    @Test
    public void testValidateWithCorrectParameterArray() {
        assertTrue(this.command.validate(Arrays.asList("1","2")));
    }
}
