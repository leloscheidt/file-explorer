package br.com.scheidt.fileexplorer.engine.commands.filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertFalse(this.command.validate(new String[0]));
    }
    
    @Test
    public void testValidateWithLesserNumberOfParameterArray() {
        assertFalse(this.command.validate(new String[1]));
    }
    
    @Test
    public void testValidateWithMoreNumberOfParameterArray() {
        assertFalse(this.command.validate(new String[3]));
    }
    
    @Test
    public void testValidateWithCorrectParameterArray() {
        assertTrue(this.command.validate(new String[2]));
    }
}
