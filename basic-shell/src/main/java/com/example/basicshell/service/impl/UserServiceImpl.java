package com.example.basicshell.service.impl;

import org.springframework.stereotype.Service;

import com.example.basicshell.model.CliUser;
import com.example.basicshell.service.UserService;

@Service
public class UserServiceImpl implements UserService  {

    @Override
    public boolean exists(String username) {
        return "admin".equals(username);
    }

    @Override
    public CliUser create(CliUser user) {
        user.setId(1000L);
        return user;
    }

    @Override
    public CliUser update(CliUser user) {
        return user;
    }
    
}
