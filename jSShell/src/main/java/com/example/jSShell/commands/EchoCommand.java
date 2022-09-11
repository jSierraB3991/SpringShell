package com.example.jSShell.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class EchoCommand {

    private final static Integer MAX_LENGTH = 100;

    @ShellMethod("Display funny version of input")
    public String echo(@ShellOption({ "-m", "--message" }) String message) {
        String output = "--";
        for(int i = 0; i < getMaxLenght(message); i++) {
            output+="-";
        }


        output += getStringFormat(message);
        for(int i = 0; i < getMaxLenght(message); i++) {
            output+="-";
        }
        return output + "-\n"+ getPenguin();
    }

    private String getPenguin() {
        return "   \\\\"
                + "\n    \\\\"
                + "\n       .--."
                + "\n      |o_o |"
                + "\n      |:_/ |"
                + "\n     //   \\ \\"
                + "\n    (|     | )"
                + "\n   /'\\_   _/`\\"
                + "\n   \\___)=(___/";
    }

    private String getStringFormat(String message) {
        if(message.length() < MAX_LENGTH) {
            return String.format("\n|%s|\n", message);
        }

        var finalValue = message.length()/MAX_LENGTH;
        if(message.length()%MAX_LENGTH != 0) {
            finalValue++;
        }
        String data = "";
        for(int i=0; i < finalValue; i++) {
            var initialIndex = i*MAX_LENGTH;
            var finalValueString = initialIndex+MAX_LENGTH;
            if(finalValueString > message.length()) {
                data += "|" + message.substring(initialIndex, message.length()) + "";
                for (int j = 0; j < finalValueString-message.length(); j++) {
                    data += " ";
                }
                data += "|\n";
            }
            else {
                data += "|" + message.substring(initialIndex, finalValueString) + "|\n";
            }
        }
        return String.format("\n%s", data);
    }

    private Integer getMaxLenght(String message) {
        return message.length() < MAX_LENGTH 
            ? message.length() : MAX_LENGTH;
    }
}
