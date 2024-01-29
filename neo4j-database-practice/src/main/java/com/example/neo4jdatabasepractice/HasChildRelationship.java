package com.example.neo4jdatabasepractice;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class HasChildRelationship {
    @RelationshipId
    private Long id;

    @TargetNode
    private Person targetPerson;
    
    public HasChildRelationship(Long id, Person targetPerson) {
        this.id = id;
        this.targetPerson = targetPerson;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Person getTargetPerson() {
        return targetPerson;
        
    }
    public void setTargetPerson(Person person) {
        this.targetPerson = person;
    }
}

