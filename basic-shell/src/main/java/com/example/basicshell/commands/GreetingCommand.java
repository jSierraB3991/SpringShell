package com.example.basicshell.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.example.basicshell.utils.ShellHelper;

import lombok.RequiredArgsConstructor;

@ShellComponent
@RequiredArgsConstructor
public class GreetingCommand {

    private final ShellHelper helper;
    
    @ShellMethod("Display greeting messages to the users who name is supplied")
    public String greetting(@ShellOption({ "-n", "--name" }) String name) {
        var output = helper.getSuccessMessage(String.format("Hello %s! ", name));
        return output.concat("You are running spring shell.");
    }    
}
