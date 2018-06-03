package br.com.scheidt.fileexplorer.engine.commands.count;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import br.com.scheidt.fileexplorer.engine.commands.Command;

public class CountAllTest {

    private Command command;

    @Before
    public void setUp() {
        this.command = new CountAll();
    }

    @Test
    public void testValidateWithNull() {
        assertFalse(this.command.validate(null));
    }

    @Test
    public void testValidateWithEmptyArray() {
        assertFalse(this.command.validate(new ArrayList<>()));
    }

    @Test
    public void testValidateWithMoreNumberOfParameterArray() {
        assertFalse(this.command.validate(Arrays.asList("one","two")));
    }

    @Test
    public void testValidateWithCorrectParameterArray() {
        assertTrue(this.command.validate(Arrays.asList("one")));
    }

}
