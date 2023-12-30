package com.example.neo4jdatabasepractice;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Node
public class Person {
    @Id @GeneratedValue private Long id;

    private String firstName;
    private String lastName;
    // private String email;
    // private String bioPicture;
    // private String bioDescription;
    // private String sex;
    // private String gender;
    // private String pronouns;
    // private String birthday;
    // private String occupation;
    // private String residency;
    // private String shirtSize;

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
}
