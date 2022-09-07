package com.example.demo.service.impl;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonServiceImpl implements PersonService, InitializingBean {

    private final AtomicBoolean connected = new AtomicBoolean();
    private final Map<Long, Person> people = new ConcurrentHashMap<>();

    public boolean isConnected() {
        return this.connected.get();
    }

    public void connect(String user, String password) {
        this.connected.set(true);
    }

    public void disconnect() {
        this.connected.set(false);
    }

    public Person findById(Long id) {
        return this.people.get(id);
    }

    public Collection<Person> findByName(String name) {
        return this.people.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AtomicLong atomicLong = new AtomicLong();
        var personsMap = Stream.of("Brian Dussavult", "Brian clozel", "Stephane Maldinie", "Stephane Nicoll", "James Watter",
                        "James Bayer", "Cornelia Davis", "Madhura Brave")
                .map(name -> Person.builder()
                        .id(atomicLong.incrementAndGet())
                        .name("name")
                        .build())
                .collect(Collectors.toMap(Person::getId, p -> p));
        people.putAll(personsMap);
    }
}
