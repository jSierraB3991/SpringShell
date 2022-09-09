package com.example.demo.service;

import com.example.demo.model.Person;

import java.util.Collection;

public interface PersonService {
    boolean isConnected();
    void connect(String user, String password);
    void disconnect();
    Person findById(Long id);
    Collection<Person> findByName(String name);
    String getUserNameLogging();
}
