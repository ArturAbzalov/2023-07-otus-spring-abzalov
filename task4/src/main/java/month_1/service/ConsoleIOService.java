package month_1.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleIOService implements IOService {

    private final Scanner scanner;

    private final PrintStream out;

    public ConsoleIOService(OutputStream outputStream, InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        this.out = new PrintStream(outputStream);
    }

    @Override
    public void print(String context) {
        out.print(context);
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
