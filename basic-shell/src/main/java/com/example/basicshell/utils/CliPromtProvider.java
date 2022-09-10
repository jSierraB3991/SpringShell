package com.example.basicshell.utils;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CliPromtProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("CLI-App:>", 
            AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
    
}
