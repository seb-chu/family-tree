package com.example.neo4jdatabasepractice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// https://www.baeldung.com/spring-controllers
@RestController
public class PersonController {

    // Followed logic from https://stackabuse.com/build-a-spring-boot-rest-api-with-java-full-guide/
    @Autowired
    private PersonRepository personRepository;

    // // Spring Boot provides this already.
    // @GetMapping
    // public List<Person> getAllPeople() {
    //     return personRepository.findAll();
    // }

    // // Spring Boot provides this already.
    // @GetMapping("/getPeopleByLastName")
    // public List<Person> getPeopleByLastName(@PathVariable String lastName) {
    //     return personRepository.findByLastNameStartsWithIgnoreCase(lastName);
    // }


    @GetMapping(value = "/people/{firstName}")
    public List<Person> getChildRelationshipsFromFirstName(@PathVariable String firstName) {
        return personRepository.findByFirstNameStartsWithIgnoreCase(firstName);
    }


    // This works inidividually, however combining it with the above method causes an error. TEST
    // @GetMapping(value = "/people/{lastName}")
    // public List<Person> getChildRelationshipsFromLastName(@PathVariable String lastName) {
    //     return personRepository.findByLastNameStartsWithIgnoreCase(lastName);
    // }

}
