package com.example.neo4jdatabasepractice;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

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


   

    @GetMapping("/people/{firstName}/{lastName}")
    public List<Person> getPerson(@PathVariable String firstName, @PathVariable String lastName) {
        return personRepository.findByFirstNameStartsWithIgnoreCaseAndLastNameStartsWithIgnoreCase(firstName, lastName);
    }

    @GetMapping("/people/allrels/{id}")
    public List<Person> getPerson(@PathVariable Long id) {
        return personRepository.findChildrenAndParents(id);
    }

    // @GetMapping("/people/children/{id_parent}")
    // public List<Person> getAllChildren(@PathVariable Long id_parent) {
    //     return personRepository.findChildren(id_parent);
    // }

    @PostMapping("/people/create/{id_parent}/{id_child}")
    public List<Person> createParentRelationship(@PathVariable Long id_parent, @PathVariable Long id_child) {
        personRepository.createParentRelationship(id_parent, id_child);
        return personRepository.findChildrenAndParents(id_parent);
    }

    @DeleteMapping("/people/delete/{id_parent}/{id_child}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteParentRelationship(@PathVariable Long id_parent, @PathVariable Long id_child) {
        personRepository.deleteParentRelationship(id_parent, id_child);
    }

    // FOR PARENT RELATIONSHIP, must calculate using graph traversal., then assign the parent (BFS or DFS).




    
    // IF node has 0 parent rels, then we MUST give it a parent rels, OR MAKE A PARENT THEN CONNECT IT.
    // fix URI naming conventions
    // USE NODE (IDs) TO SEARCH FOR GENEOLOGY, USE MOST COMMON ANCESTOR and some pattern idk. ///
}
