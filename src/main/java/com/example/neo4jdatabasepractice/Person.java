package com.example.neo4jdatabasepractice;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.HashSet;
import java.util.Set;

import lombok.Setter;
import lombok.Getter;
import lombok.Data;
import lombok.AccessLevel;

@Data
@Node
public class Person {
    @Id @GeneratedValue @Setter(AccessLevel.PRIVATE) @Getter(AccessLevel.PUBLIC)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
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

    // HAVING BOTH OF THESE RELATIONSHIPS CAUSES CYLCES IN THE GRAPH
    @Relationship(type = "HAS_CHILD", direction = Direction.OUTGOING)
    private Set<Person> children; //using set bc its unordered and has no duplicates. 

    // FILL THIS IN BACK END
    private Set<Person> parents; //using set bc its unordered and has no duplicates. 














    
    //////////////// USE LOMBOK TO GENERATE GETTERS AND SETTERS ////////////////
    // public Person(Long id, String firstName, String lastName, Set<Person> children, Set<Person> parents) {
    //     this.id = id;
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.children = children; 
    //     this.parents = parents;
    // }
    
    
    // public Long getId() {
    //     return id;
    // }
    // @Setter
    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getFirstName() {
    //     return firstName;
    // }
    // public void setFirstName(String firstName) {
    //     this.firstName = firstName;
    // }

    // public String getLastName() {
    //     return lastName;
    // }
    // public void setLastName(String lastName) {
    //     this.lastName = lastName;
    // }

    // public Set<Person> getChildren() {
    //     return children;
    // }
    // public void setChildren(Set<Person> children) {
    //     this.children = children;
    // }

    // public Set<Person> getParents() {
    //     return parents;
    // }
    // public void setParents(Set<Person> parents) {
    //     this.parents = parents;
    // }
}
