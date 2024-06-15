package com.example.neo4jdatabasepractice;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// https://www.baeldung.com/spring-controllers
@RestController
public class PersonController {

    // Followed logic from https://stackabuse.com/build-a-spring-boot-rest-api-with-java-full-guide/
    // @Autowired
    // private PersonRepository personRepository;

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // // Spring Boot provides this already.
    // @GetMapping("/getPeopleByLastName")
    // public List<Person> getPeopleByLastName(@PathVariable String lastName) {
    //     return personRepository.findByLastNameStartsWithIgnoreCase(lastName);
    // }


    //////////////////////////////////////////////////////////////////////////////////////////////////
    // MAKE SURE TO PREVENT DUPLICATE ENTRIES
    // @GetMapping("/people/blah/{firstName}/{lastName}")
    // public List<Person> getPeopleByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
    //     return personRepository.findAllByFirstNameAndLastName(firstName, lastName);
    // }

    @GetMapping(value = "/people/{firstName}/{lastName}")
    public List<Person> getPerson(@PathVariable String firstName, @PathVariable String lastName) {
        // List<Person> familyBranch = personRepository.findChildren(firstName, lastName);
        // System.out.println(familyBranch);
        // return familyBranch;
        return personRepository.findByFirstNameStartsWithIgnoreCaseAndLastNameStartsWithIgnoreCase(firstName, lastName);
    }
    
    @GetMapping("/people/children/{firstName}/{lastName}")
    public List<Person> getAllChildren(@PathVariable String firstName, @PathVariable String lastName) {
        return personRepository.findChildren(firstName, lastName);
    }

    // // HOW TO FIND ID OF A PERSON BY FIRST AND LAST NAME????
    // If i were to search a name and get two ids, how would i save the id i want???
    @GetMapping("/people/id/{firstName}/{lastName}")
    public Long getId(@PathVariable String firstName, @PathVariable String lastName) {
        // return personRepository.findByFirstNameStartsWithIgnoreCaseAndLastNameStartsWithIgnoreCase(firstName, lastName).get(0).getId();
        return personRepository.findByFirstNameStartsWithIgnoreCaseAndLastNameStartsWithIgnoreCase(firstName, lastName).get(0).getId();
    }





    @GetMapping("/people/create/{p1firstName}/{p1lastName}/{p2firstName}/{p2lastName}")
    public List<Person> createParentRelationship(@PathVariable String p1firstName, @PathVariable String p1lastName, @PathVariable String p2firstName, @PathVariable String p2lastName) {
        personRepository.createParentRelationship(p1firstName, p1lastName, p2firstName, p2lastName);
        return personRepository.findChildren(p1firstName, p1lastName);
    }

    @GetMapping("/people/delete/{p1firstName}/{p1lastName}/{p2firstName}/{p2lastName}")
    public void deleteParentRelationship(@PathVariable String p1firstName, @PathVariable String p1lastName, @PathVariable String p2firstName, @PathVariable String p2lastName) {
        personRepository.deleteParentRelationship(p1firstName, p1lastName, p2firstName, p2lastName);
    }

    // FOR PARENT RELATIONSHIP, must calculate using graph traversal., then assign the parent (BFS or DFS).

    // USE NODE (IDs) TO SEARCH FOR GENEOLOGY, USE MOST COMMON ANCESTOR and some pattern idk.
}
