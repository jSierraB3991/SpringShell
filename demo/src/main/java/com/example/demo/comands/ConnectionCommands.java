package com.example.demo.comands;

import com.example.demo.service.PersonService;
import com.example.demo.util.ConsoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class ConnectionCommands {
    private final ConsoleService consoleService;
    private final PersonService personService;

    @ShellMethod("connect to t CRM")
    public void connect(String user, String password) {
        this.personService.connect(user, password);
        this.consoleService.write("Connected to %s.", user);
    }

    @ShellMethodAvailability("connect")
    Availability connectAvailable() {
        return !this.personService.isConnected() ?
                Availability.available() :
                Availability.unavailable("You're already connected");
    }

    @ShellMethod("disconnect to CRM")
    public void disconnect()  {
        this.personService.disconnect();
    }

    @ShellMethodAvailability("disconnect")
    Availability disconnectAvailable() {
        return this.personService.isConnected() ?
                Availability.available() :
                Availability.unavailable("You are not connected");
    }
}
