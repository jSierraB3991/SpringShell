package com.example.demo.util;

import org.springframework.stereotype.Service;

import java.io.PrintStream;

@Service
public class ConsoleService {
    private final static String ANSI_RESET = "\u001B[0m";
    private final static String ANSI_YELLOW = "\u001B[33m";
    private final PrintStream out = System.out;

    public void write(String message, String... args){
        this.out.print("> ");
        this.out.print(ANSI_YELLOW);
        this.out.printf(message, (Object[]) args);
        this.out.print(ANSI_RESET);
        this.out.println();
    }
}
