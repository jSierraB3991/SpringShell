package com.example.basicshell.service;

import java.util.List;

import com.example.basicshell.model.CliUser;

public interface UserService {

    List<CliUser> getUsers();

    boolean exists(String username);

    CliUser create(CliUser user);
    
    CliUser update(CliUser user);
}
