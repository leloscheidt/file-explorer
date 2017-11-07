package br.com.scheidt.fileexplorer.application;

import org.junit.Before;
import org.junit.Test;

import br.com.scheidt.fileexplorer.engine.executor.EngineExecutor;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.view.console.ConsoleView;

public class AppTest {
    
    private App application;
    
    @Before
    public void setUp() {
        this.application = new App(new EngineExecutor(), new ConsoleView());
    }
    
    @Test(expected = ApplicationException.class)
    public void testInitializeWithNull() throws ApplicationException {
        this.application.initialize(null);
    }
    
    @Test(expected = ApplicationException.class)
    public void testInitializeWithEmptyString() throws ApplicationException {
        this.application.initialize("");
    }
    
    @Test(expected = ApplicationException.class)
    public void testInitializeWithBlankString() throws ApplicationException {
        this.application.initialize("  ");
    }
    
    @Test(expected = ApplicationException.class)
    public void testInitializeWithInvalidPath() throws ApplicationException {
        this.application.initialize("invalid.path");
    }
    
    @Test(expected = ApplicationException.class)
    public void testInitializeWithDirectory() throws ApplicationException {
        String path = System.getProperty("java.home");
        
        this.application.initialize(path);
    }

}
