package com.example.demo.configuration;

import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConnectedPromptProvider  implements PromptProvider{
    private final PersonService personService;

    @Override
    public AttributedString getPrompt() {
        String message = String.format("Spring CMR (%s)> ", this.personService.isConnected() ? "connected" : "disconnect");
        return new AttributedString(message);
    }
}
