package com.example.basicshell.service;

import com.example.basicshell.model.CliUser;

public interface UserService {
    boolean exists(String username);

    CliUser create(CliUser user);
    
    CliUser update(CliUser user);
}
