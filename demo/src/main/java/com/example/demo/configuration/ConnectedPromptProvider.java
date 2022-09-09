package com.example.demo.configuration;

import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConnectedPromptProvider  implements PromptProvider{
    private final PersonService personService;

    @Override
    public AttributedString getPrompt() {

        var logged = new AttributedString(String.format("Spring CMR (%s)> ", this.personService.getUserNameLogging()),
                AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));
        var unLogged = new AttributedString("Spring CMR (disconnect)> ",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.RED));

        return this.personService.isConnected()
                ? logged : unLogged;
    }
}
