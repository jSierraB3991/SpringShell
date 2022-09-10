package com.example.basicshell.commands;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;s

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.util.StringUtils;

import com.example.basicshell.model.CliUser;
import com.example.basicshell.model.Gender;
import com.example.basicshell.service.UserService;
import com.example.basicshell.utils.InputReader;
import com.example.basicshell.utils.ShellHelper;

import lombok.RequiredArgsConstructor;

@ShellComponent
@RequiredArgsConstructor
public class UserCommand {
    private final ShellHelper helper;
    private final UserService service;
    private final InputReader inputReader;

    @ShellMethod("Display list of users")
    public void userList() {
        var users = service.getUsers();

        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
        headers.put("id", "Id");
        headers.put("userName", "Username");
        headers.put("fullName", "Full name");
        headers.put("gender", "Gender");
        var model = new BeanListTableModel<CliUser>(users, headers);

        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        helper.print(tableBuilder.build().render(80));
    }


    @ShellMethod("Create new user with supplied user name")
    public void createUser(@ShellOption({ "-u", "--username" }) String userName) {
        if(service.exists(userName)) {
            helper.printError(
                String.format("User with username = '%s' already exists   -----> ABORTING", userName)
            );
            return;
        }
        var user = CliUser.builder().userName(userName).build();


        //----------------READ  Full Name
        do {
            var fullName = inputReader.prompt("Full name");
            if (StringUtils.hasText(fullName)) {
                user.setFullName(fullName);
            } else {
                helper.printWarning("User's full name CAN NOT be empty string? Please enter valid value!");                
            }
        } while (user.getFullName() == null);


        //---------------------READ PASSWORD
        do {
            String password = inputReader.prompt("Password", "secret", false);
            if (StringUtils.hasText(password)) {
                user.setPassword(password);
            } else {
                helper.printWarning("Password'CAN NOT be empty string? Please enter valid value!");
            }
        } while (user.getPassword() == null);


        //---------------------READ GENDER
        Map<String, String> options = new HashMap<>();
        options.put("M", Gender.MALE.name());
        options.put("F", Gender.FEMALE.name() );
        options.put("D", Gender.DIVERSE.name());
        String genderValue = inputReader.selectFromList("Gender", 
                                                        "Please enter one of the [] values", options,
                                                        true, null);


        user.setGender(Gender.valueOf(options.get(genderValue.toUpperCase())));

        helper.printInfo("\nCreating new user:");
        helper.print(user.toString());

        CliUser createdUser = service.create(user);
        helper.printSuccess("Created user with id=" + createdUser.getId());
    }
}
