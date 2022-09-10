package com.example.basicshell.configuration;

import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.example.basicshell.utils.InputReader;
import com.example.basicshell.utils.ShellHelper;


@Configuration
public class SpringShellConfig {

    @Bean
    public ShellHelper shellHelper(@Lazy Terminal terminal) {
        return new ShellHelper(terminal);
    }
    
    @Bean
    public InputReader inputReader(@Lazy LineReader lineReader, ShellHelper shellHelper) {
      return new InputReader(lineReader, shellHelper);
    }
}
