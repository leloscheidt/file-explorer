package br.com.scheidt.fileexplorer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.scheidt.fileexplorer.application.AppTest;
import br.com.scheidt.fileexplorer.engine.EngineExecutorTest;
import br.com.scheidt.fileexplorer.engine.commands.attribute.AttributeTest;
import br.com.scheidt.fileexplorer.engine.commands.count.CountAllTest;
import br.com.scheidt.fileexplorer.engine.commands.count.CountDistinctTest;
import br.com.scheidt.fileexplorer.engine.commands.exit.ExitTest;
import br.com.scheidt.fileexplorer.engine.commands.filter.FilterTest;
import br.com.scheidt.fileexplorer.parser.FileParserFactoryTest;
import br.com.scheidt.fileexplorer.parser.csv.CsvParserTest;
import br.com.scheidt.fileexplorer.utils.UtilsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    
    /* Commands */
    
    AttributeTest.class,
    FilterTest.class,
    ExitTest.class,
    CountAllTest.class,
    CountDistinctTest.class,

    /* Factory */
    
    FileParserFactoryTest.class,
    
    /* Parser */
    
    CsvParserTest.class,
    
    /* Engine */
    
    EngineExecutorTest.class,
    
    /* App */
    
    AppTest.class,
    
    /* Utils */
    
    UtilsTest.class
    
    
})
public class AllTest {

}
