package com.example.neo4jdatabasepractice;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>, CrudRepository<Person, Long> {
    List<Person> findByLastNameStartsWithIgnoreCase(@Param("lastName") String lastName);
    List<Person> findByFirstNameStartsWithIgnoreCase(@Param("firstName") String firstName);
}
