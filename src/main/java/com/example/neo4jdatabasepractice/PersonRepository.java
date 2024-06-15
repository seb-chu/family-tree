package com.example.neo4jdatabasepractice;

import java.util.List;
import java.util.Set;
import java.util.Map;

import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    List<Person> findByFirstNameStartsWithIgnoreCaseAndLastNameStartsWithIgnoreCase(String firstName, String lastName);

    // List<Long> findIdByFirstNameStartsWithIgnoreCaseAndLastNameStartsWithIgnoreCase(String firstName, String lastName);

    @Query(
        "MATCH (parent:Person {firstName: $firstName, lastName: $lastName})-[r_parent:HAS_CHILD*]->(child:Person)" +
        "RETURN parent, collect(r_parent), collect(child)")
    List<Person> findChildren(@Param("firstName") String firstName, @Param("lastName") String lastName);
    
    // Person saveChildRelationship(String parent, String child); //trying to do this. https://stackoverflow.com/questions/77466175/problem-when-fetching-the-main-node-and-its-children-spring-data-neo4j

    @Query("MERGE (a:Person {firstName: $person1First, lastName: $person1Last})" + 
            "MERGE (b:Person {firstName: $person2First, lastName: $person2Last})" +  
            "MERGE (a)-[:HAS_CHILD]->(b)")
    void createParentRelationship(@Param("person1First") String person1First, @Param("person1Last") String person1Last, @Param("person2First") String person2First, @Param("person2Last") String person2Last);

    //ALWAYS RETURNS CODE 200, EVEN IF RELATIONSHIP DOESNT EXIST. FIX!!!!?
    @Query("MATCH (parent:Person {firstName: $person1First, lastName: $person1Last})-[r_parent:HAS_CHILD]->(child:Person {firstName: $person2First, lastName: $person2Last})" +
            // "WITH parent, r_parent, child" +        
            "DELETE r_parent" 
            // "RETURN parent, collect(child)"
            )
    void deleteParentRelationship(@Param("person1First") String person1First, @Param("person1Last") String person1Last, @Param("person2First") String person2First, @Param("person2Last") String person2Last);
}