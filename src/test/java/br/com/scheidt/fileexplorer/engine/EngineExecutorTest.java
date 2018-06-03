package br.com.scheidt.fileexplorer.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import br.com.scheidt.fileexplorer.engine.commands.Command;
import br.com.scheidt.fileexplorer.engine.commands.attribute.Attribute;
import br.com.scheidt.fileexplorer.engine.commands.count.CountAll;
import br.com.scheidt.fileexplorer.engine.commands.count.CountDistinct;
import br.com.scheidt.fileexplorer.engine.commands.exit.Exit;
import br.com.scheidt.fileexplorer.engine.commands.filter.Filter;
import br.com.scheidt.fileexplorer.engine.executor.EngineExecutor;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.file.TestFile;

public class EngineExecutorTest {
    
    private EngineExecutor engine;
    
    @Before
    public void setUp() {
        this.engine = new EngineExecutor();
    }
    
    @Test
    public void testAddWithNullCommand() {
        this.engine.add(null);
        
        assertTrue(this.engine.commands().isEmpty());
    }
    
    @Test
    public void testAddCorrectCommand() {
        this.engine.add(new Exit());
        
        assertEquals(1, this.engine.commands().size());
        assertEquals(Exit.class, this.engine.commands().get(0).getClass());
        
    }
    
    @Test
    public void testAddSameCommand() {
        this.engine.add(new Exit());
        this.engine.add(new Exit());
        
        assertEquals(1, this.engine.commands().size());
        assertEquals(Exit.class, this.engine.commands().get(0).getClass());
    }
    
    @Test
    public void testAddTwoCommands() {
        this.engine.add(new Exit());
        this.engine.add(new CountAll());
        
        assertEquals(2, this.engine.commands().size());
        assertEquals(Exit.class, this.engine.commands().get(0).getClass());
        assertEquals(CountAll.class, this.engine.commands().get(1).getClass());
    }
    
    @Test
    public void testHealpWithAllCommands() {
        Command[] commands = {
                new Attribute(), 
                new CountAll(), 
                new CountDistinct(), 
                new Filter(), 
                new Exit()
            };
        
        for(Command command : commands) {
            this.engine.add(command);
        }
        
        String help = this.engine.help();
        
        for(Command command : commands) {
            assertTrue(help.contains(command.description()));
        }
    }
    
    @Test(expected = ApplicationException.class)
    public void testCommandWithNull() throws ApplicationException {
        this.engine.command(null);
    }
    
    @Test(expected = ApplicationException.class)
    public void testCommandWithEmptyString() throws ApplicationException {
        this.engine.command("");
    }
    
    @Test(expected = ApplicationException.class)
    public void testCommandBlankString() throws ApplicationException {
        this.engine.command("  ");
    }
    
    @Test(expected = ApplicationException.class)
    public void testCommandWithInvalidString() throws ApplicationException {
        this.engine.command("invalid");
    }
    
    @Test(expected = ApplicationException.class)
    public void testCommandWithCorrectStringButNoCommands() throws ApplicationException {
        this.engine.command(Exit.NAME);
    }
    
    @Test
    public void testCommandWithCorrectString() throws ApplicationException {
        this.engine.add(new Exit());
        
        Command command = this.engine.command(Exit.NAME);
        
        assertEquals(Exit.class, command.getClass());
    }
    
    @Test(expected = ApplicationException.class)
    public void testCommandWithInvalidStringAndValidCommand() throws ApplicationException {
        this.engine.add(new CountAll());
        
        this.engine.command("invalid");
    }
    
    @Test(expected = ApplicationException.class)
    public void testInitializeWithNull() throws ApplicationException {
        this.engine.initialize(null);
    }
    
    @Test(expected = ApplicationException.class)
    public void testInitializeWithInvalidPath() throws ApplicationException {
        File file = new File("invalid.path");
        
        this.engine.initialize(file);
    }
    
    @Test(expected = ApplicationException.class)
    public void testInitializeWithRealPathButUnsupported() throws ApplicationException {
        this.engine.initialize(TestFile.unsupported());
    }
    
    @Test
    public void testInitializeWithCorrectFile() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        assertEquals(5, this.engine.commands().size());
    }
    
    @Test(expected = ApplicationException.class)
    public void testExecuteWithUnitilizedEngine() throws ApplicationException {
        this.engine.execute(Exit.NAME);
        
    }
    
    @Test
    public void testExecuteWithCorrectExitCommand() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        String result = this.engine.execute("exit");
        
        assertEquals("Bye!", result);
    }
    
    @Test(expected = ApplicationException.class)
    public void testExecuteWithExitCommandAndMoreParameter() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        this.engine.execute("exit x");
    }
    
    @Test
    public void testExecuteWithCorrectAttributeCommand() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        String result = this.engine.execute("attributes");
        
        assertEquals("id\nname\ncard\nemail\nstatus", result);
    }
    
    @Test(expected = ApplicationException.class)
    public void testExecuteWithAttributeCommandAndMoreParameter() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        this.engine.execute("attributes jj");
    }
    
    @Test
    public void testExecuteWithCorrectCountAllCommand() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        String result = this.engine.execute("count *");
        
        assertEquals(100, toInt(result));
    }
    
    @Test(expected = ApplicationException.class)
    public void testExecuteWithCountAllCommandAndMoreParameter() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        this.engine.execute("count * jr");
    }
    
    @Test(expected = ApplicationException.class)
    public void testExecuteWithCountAllCommandAndLessParameter() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        this.engine.execute("count");
    }
    
    @Test
    public void testExecuteWithCorrectCountDistinctCommand() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        String result = null;
        
        result = this.engine.execute("count distinct id");
        assertEquals(99, toInt(result));
        
        result = this.engine.execute("count distinct name");
        assertEquals(98, toInt(result));
        
        result = this.engine.execute("count distinct card");
        assertEquals(99, toInt(result));
        
        result = this.engine.execute("count distinct email");
        assertEquals(100, toInt(result));
        
        result = this.engine.execute("count distinct status");
        assertEquals(7, toInt(result));
    }
    
    @Test(expected = ApplicationException.class)
    public void testExecuteWithCountDistinctCommandAndMoreParameter() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        this.engine.execute("count distinct name card");
    }
    
    @Test(expected = ApplicationException.class)
    public void testExecuteWithCountDistinctCommandAndLessParameter() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        this.engine.execute("count distinct");
    }
    
    @Test(expected = ApplicationException.class)
    public void testExecuteWithCountDistinctCommandAndInvalidAttribute() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        this.engine.execute("count distinct invalid");
    }
    
    @Test
    public void testExecuteWithCorrectFilterCommand() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        String expected = "id,name,card,email,status\n30,Tasha Waters,3046,tasha@waters.com,6";
        
        String result = this.engine.execute("filter id 30");

        assertEquals(expected, result);
    }
    
    @Test
    public void testExecuteWithFilterCommandAndMoreParameter() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        String expected = "id,name,card,email,status\n31,Elaine Floyd,2587,adipiscing@vulputate.com,4";
        
        String result = this.engine.execute("filter name Elaine Floyd");
        
        assertEquals(expected, result);
    }
    
    @Test(expected = ApplicationException.class)
    public void testExecuteWithFilterCommandAndLessParameter() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        this.engine.execute("filter name");
    }
    
    @Test(expected = ApplicationException.class)
    public void testExecuteWithFilterCommandAndInvalidAttribute() throws ApplicationException {
        this.engine.initialize(TestFile.csv());
        
        this.engine.execute("filter invalid 30");
    }
    
    private int toInt(String text) {
        return Integer.valueOf(text.replaceAll("[^\\d]", ""));
    }
}
