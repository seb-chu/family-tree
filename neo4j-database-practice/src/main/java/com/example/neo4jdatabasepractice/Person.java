package com.example.neo4jdatabasepractice;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Node
public class Person {
    @Id @GeneratedValue 
    private Long id;

    private String firstName;
    private String lastName;
    // private String email;
    // private String bioPicture;
    // private String bioDescription;
    // private String sex;
    // private String gender;
    // private String pronouns;
    //https://docs.spring.io/spring-data/neo4j/docs/5.1.21.RELEASE/reference/html/#reference:type-conversion
        // private String birthdate;
        // @DateString(lenient = true)
        // private Date birthdate;
    // private String occupation;
    // private String residency;
    // private String shirtSize;
    // @Relationship(type = "HAS_CHILD", direction = Direction.INCOMING)
    // private Set<Person> parents; //using set bc its unordered and has no duplicates. 

    @Relationship(type = "HAS_CHILD", direction = Direction.OUTGOING)
    private Set<HasChildRelationship> children; //using set bc its unordered and has no duplicates. 
    
    public Person(Long id, String firstName, String lastName, Set<HasChildRelationship> children) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.children = children; 
        // this.parents = parents;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<HasChildRelationship> getChildren() {
        return children; //not showing up in swagger TODO
    }
    public void setChildren(Set<HasChildRelationship> children) {
        this.children = children;
    }

    // public Set<Person> getParents() {
    //     return parents;
    // }
    // public void SetParents(Set<Person> parents)  {
    //     this.parents = parents;
    // }
}
