package com.example.basicshell.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.basicshell.model.CliUser;
import com.example.basicshell.service.UserService;

@Service
public class UserServiceImpl implements UserService  {

    private final List<CliUser> users = new ArrayList<CliUser>();

    @Override
    public boolean exists(String username) {
        return "admin".equals(username);
    }

    @Override
    public CliUser create(CliUser user) {
        user.setId(getId());
        users.add(user);
        return user;
    }

    @Override
    public CliUser update(CliUser user) {
        return user;
    }
    
    private Long getId() {
        if(users.size() == 0) return 1L;
        return users.stream().map(CliUser::getId).max(Long::compareTo).get() + 1L;
    }

    @Override
    public List<CliUser> getUsers() {
        return users;
    }
}
