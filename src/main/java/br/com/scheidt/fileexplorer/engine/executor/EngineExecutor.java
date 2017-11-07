package br.com.scheidt.fileexplorer.engine.executor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.scheidt.fileexplorer.constants.Constants;
import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.engine.Engine;
import br.com.scheidt.fileexplorer.engine.commands.Command;
import br.com.scheidt.fileexplorer.engine.commands.attribute.Attribute;
import br.com.scheidt.fileexplorer.engine.commands.count.CountAll;
import br.com.scheidt.fileexplorer.engine.commands.count.CountDistinct;
import br.com.scheidt.fileexplorer.engine.commands.exit.Exit;
import br.com.scheidt.fileexplorer.engine.commands.filter.Filter;
import br.com.scheidt.fileexplorer.exception.ApplicationException;
import br.com.scheidt.fileexplorer.parser.FileParserFactory;
import br.com.scheidt.fileexplorer.utils.Utils;

public class EngineExecutor implements Engine {

    private final Map<String, Command> commands;
    
    private File file;
    
    public EngineExecutor() {
        this.commands = new LinkedHashMap<String, Command>();
    }

    @Override
    public String help() {

        StringBuilder builder = new StringBuilder();

        for (Command command : this.commands.values()) {
            builder.append(command.description());
            builder.append(Constants.BREAK_LINE);
        }

        return builder.toString();
    }

    @Override
    public void add(Command command) {
        if(command != null) {
            this.commands.put(command.name(), command);
        }
    }

    @Override
    public void initialize(File file) throws ApplicationException {
        if(!Utils.checkFile(file)) {
            throw new ApplicationException(Messages.FILE_NOT_FOUND); 
        }
        
        if(!FileParserFactory.supports(file)) {
            throw new ApplicationException(Messages.PARSER_NOT_FOUND);
        }
        
        this.file = file;
        
        this.addCommands();
    }
    
    private void addCommands() {
        this.add(new Attribute());
        this.add(new CountAll());
        this.add(new CountDistinct());
        this.add(new Filter());
        this.add(new Exit());
    }

    @Override
    public String execute(String text) throws ApplicationException {
        if(this.file != null) {
            Command command = this.command(text);
            
            String[] parts = text.split(Constants.SPACE);
            
            return command.execute(this.file, Arrays.copyOfRange(parts, 1, parts.length));
        }
        
        throw new ApplicationException(Messages.ENGINE_NOT_INITIALIZED);
    }
    
    @Override
    public List<Command> commands() {
        return new ArrayList<Command>(this.commands.values());
    }
    
    @Override
    public Command command(String name) throws ApplicationException {
        
        if(name != null) {

            for(String key : this.commands.keySet()) {
                
                if(name.startsWith(key)) {
                    return this.commands.get(key);
                }
            }
        }
        
        throw new ApplicationException(Messages.INVALID_COMMAND);
    }
}
