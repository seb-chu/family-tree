package com.example.neo4jdatabasepractice;

import java.util.List;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    List<Person> findByFirstNameStartsWithIgnoreCase(@Param("firstName") String firstName);
    @Query(
        "MATCH (parentFirst:Person {firstName: $parentFirst})-[r:HAS_CHILD]->(child:Person)" +
        "RETURN parentFirst, collect(r), collect(child)"
        )

    // This works inidividually, however combining it with the above method causes an error. TEST
    List<Person> findByLastNameStartsWithIgnoreCase(@Param("lastName") String lastName); 
    @Query(
        "MATCH (parentLast:Person {lastName: $parentLast})-[r:HAS_CHILD]->(child:Person)" +
        "RETURN parentLast, collect(r), collect(child)"
        )
            
    //how collect() works, https://neo4j.com/docs/cypher-manual/current/subqueries/collect/
    Person saveChildRelationship(String parent, String child); //trying to do this. https://stackoverflow.com/questions/77466175/problem-when-fetching-the-main-node-and-its-children-spring-data-neo4j

    // probably isn't needed assuming savechildrelationship is done correctly, but we will know later
    // @Query("MATCH (parent)-[r:HAS_CHILD*0..]->(child)" +
    //         "WITH parent, collect(r) as rels, collect(child) as children" +
    //         "RETURN parent, rels, children")
    // List<Person> findChildWithPersons(String parentFirstName);

    // @Query("MATCH (a:Person {firstName: $person1})" + 
    //         "MATCH (b:Person {firstName: $person2})" +  
    //         "CREATE (a)-[:HAS_CHILD]->(b)" )
    // void createParentRelationship(String person1, String person2);

    // @Query("MATCH (a:Person where ID(a) = 179)" +
    //         "MATCH (b:Person where ID(b) = 182)" +
    //         "MATCH p = shortestPath((a)-[:ACTED_IN|HAHAHA*]->(b))" +
    //         "RETURN p")
    // List<Person> findParentsUsingTwoIds(Integer person1, Integer person2);
}