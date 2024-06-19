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

    @Query(
        "MATCH (refNode:Person WHERE ID(refNode) = $id)" +
        "OPTIONAL MATCH (refNode) -[r_parent:HAS_CHILD*]-> (child:Person)" +
        "OPTIONAL MATCH (refNode) <-[r_child:HAS_CHILD*]- (parent:Person)" +
        "RETURN refNode, collect(r_parent), collect(child), collect(r_child), collect(parent)"
    )
    List<Person> findChildrenAndParents(@Param("id") Long id);

    // I DONT NEED THIS I THINK SINCE I HAVE FIND CHILDREN AND PARENTS
    // @Query(
    //     "OPTIONAL MATCH (parent:Person WHERE ID(parent) = $id)-[r_parent:HAS_CHILD*]->(child:Person)" +
    //     "RETURN parent, collect(r_parent), collect(child)")
    // List<Person> findChildren(@Param("id") Long id);
    
    // Person saveChildRelationship(String parent, String child); //trying to do this. https://stackoverflow.com/questions/77466175/problem-when-fetching-the-main-node-and-its-children-spring-data-neo4j

    // CAN'T USE INTERNAL ID FOR CREATION.
    @Query("MATCH (parent:Person WHERE ID(parent) = $id_parent)" + 
            "MATCH (child:Person WHERE ID(child) = $id_child)" +  
            "MERGE (parent)-[:HAS_CHILD]->(child)")
    void createParentRelationship(@Param("id_parent") Long id_parent, @Param("id_child") Long id_child);

    @Query("MATCH (parent:Person WHERE ID(parent) = $id_parent)-[r_parent:HAS_CHILD]->(child:Person WHERE ID(child) = $id_child)" +
            "DELETE r_parent" 
            )
    void deleteParentRelationship(@Param("id_parent") Long id_parent, @Param("id_child") Long id_child);
    
    
    
    // LOCATION TO PERSON FUNCTION

    //path == unique (id), query 
}