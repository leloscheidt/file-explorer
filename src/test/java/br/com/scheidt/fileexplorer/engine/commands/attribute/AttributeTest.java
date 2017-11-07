package br.com.scheidt.fileexplorer.engine.commands.attribute;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.scheidt.fileexplorer.engine.commands.Command;
import br.com.scheidt.fileexplorer.exception.ApplicationException;

public class AttributeTest {
    
    private Command command;
    
    @Before
    public void setUp() {
        this.command = new Attribute();
    }
    
    @Test
    public void testValidateWithNull() {
        assertFalse(this.command.validate(null));
    }
    
    @Test
    public void testValidateWithEmptyArray() {
        assertTrue(this.command.validate(new String[0]));
    }
    
    @Test
    public void testValidateWithFilledArray() {
        assertFalse(this.command.validate(new String[1]));
    }
    
    @Test(expected = ApplicationException.class)
    public void testExecuteWithNull() throws ApplicationException {
        this.command.execute(null, null);
    }
    
    @Test(expected = ApplicationException.class)
    public void testExecuteWithNullParser() throws ApplicationException {
        this.command = new Attribute();
        
        this.command.execute(null, new String[0]);
    }
    

}
