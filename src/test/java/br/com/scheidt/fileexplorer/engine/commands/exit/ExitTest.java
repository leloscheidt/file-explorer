package br.com.scheidt.fileexplorer.engine.commands.exit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import br.com.scheidt.fileexplorer.engine.commands.Command;

public class ExitTest {

    private Command command;

    @Before
    public void setUp() {
        this.command = new Exit();
    }

    @Test
    public void testValidateWithNull() {
        assertFalse(this.command.validate(null));
    }

    @Test
    public void testValidateWithEmptyArray() {
        assertTrue(this.command.validate(new ArrayList<>()));
    }

    @Test
    public void testValidateWithFilledArray() {
        assertFalse(this.command.validate(Arrays.asList("filled")));
    }
}
