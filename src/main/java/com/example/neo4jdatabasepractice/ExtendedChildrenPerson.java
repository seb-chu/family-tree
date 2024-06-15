// package com.example.neo4jdatabasepractice;

// import java.util.Set;
// import org.springframework.data.neo4j.core.schema.Node;
// import org.springframework.data.neo4j.core.schema.Relationship;
// import org.springframework.data.neo4j.core.schema.Relationship.Direction;

// @Node
// class ExtendedChildrenPerson extends Person {

//     public ExtendedChildrenPerson(Long id, String firstName, String lastName, Set<Person> children, Set<Person> parents) {
//         super(id, firstName, lastName, children, parents);
//     }

//     @Relationship(type = "HAS_CHILD", direction = Direction.OUTGOING)
//     private Set<Person> children;
// }