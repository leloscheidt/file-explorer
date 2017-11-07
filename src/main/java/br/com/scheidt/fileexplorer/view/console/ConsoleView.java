package br.com.scheidt.fileexplorer.view.console;

import java.io.PrintStream;
import java.util.Scanner;

import br.com.scheidt.fileexplorer.constants.Constants;
import br.com.scheidt.fileexplorer.constants.Messages;
import br.com.scheidt.fileexplorer.view.View;

public class ConsoleView implements View {
    
    private final PrintStream output;
    private final Scanner input;
    
    public ConsoleView() {
        this.output = System.out;
        this.input = new Scanner(System.in);
    }

    public void show(String message) {
        this.output.println(message + Constants.BREAK_LINE);
    }

    public String read() {
        this.output.print(Messages.TYPE_HERE);
        return this.input.nextLine().toLowerCase();
    }

    @Override
    public void clear() {
        this.input.close();
        this.output.close();
    }
}
