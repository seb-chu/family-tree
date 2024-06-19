package com.example.neo4jdatabasepractice;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

import lombok.Setter;
import lombok.Getter;
import lombok.Data;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

// @Getter
// @Setter
// @AllArgsConstructor
@Data
@Node
public class Person {
    @Id @GeneratedValue @Setter(AccessLevel.PRIVATE) @Getter(AccessLevel.PUBLIC)
    private Long id;

    private String firstName;
    private String lastName;

    @Relationship(type = "HAS_CHILD", direction = Direction.OUTGOING)
    private List<Person> children; //using set bc its unordered and has no duplicates. 

    @JsonIgnoreProperties("children")
    @Relationship(type = "HAS_CHILD", direction = Direction.INCOMING)
    private List<Person> parents; //using set bc its unordered and has no duplicates. 
}












    // private String email;
    // private String bioPicture; //https://aws.amazon.com/s3/
    // private String bioDescription;
    // private Sex sex; USE ENUM
    // private String gender;
    // private String pronouns;
    //https://docs.spring.io/spring-data/neo4j/docs/5.1.21.RELEASE/reference/html/#reference:type-conversion
        // private String birthdate;
        // @DateString(lenient = true)
        // private Date birthdate;
    // private String occupation;
    // private String residency;
    // private ShirtSize shirtSize; USE ENUM