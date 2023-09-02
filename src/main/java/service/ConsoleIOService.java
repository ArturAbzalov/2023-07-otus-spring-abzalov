package service;

import java.util.Scanner;

public class ConsoleIOService implements IOService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String context) {
        System.out.print(context);
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
