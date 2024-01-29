# neo4j-database-practice

## Terminology

- ![Family Relation Chart](https://7esl.com/wp-content/uploads/2018/01/Family-Members-2.jpg)

## Queries

### User profile function notations
- https://docs.spring.io/spring-data/neo4j/docs/5.1.21.RELEASE/reference/html/#repositories.query-methods
- https://docs.spring.io/spring-data/neo4j/reference/repositories/core-concepts.html
- https://docs.spring.io/spring-data/neo4j/reference/repositories/query-methods-details.html
- https://www.baeldung.com/spring-data-derived-queries
- https://docs.spring.io/spring-data/neo4j/reference/repositories/custom-implementations.html
- https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html

### Create relation
```sql
-- does not account for duplicate relation, TODO
MATCH (a:Person WHERE ID(a) = 184)
MATCH (b:Person WHERE ID(b) = 185)
CREATE (a)-[:ACTED_IN ]->(b)
RETURN a, b
```

### Delete relation
```sql
MATCH (a:Person WHERE ID(a) = 184)
MATCH (b:Person WHERE ID(b) = 185)
-- this deletes only the relationship stated from a to b, remove ':ACTED_IN' to remove any relation.
MATCH (a)-[r:ACTED_IN ]->(b)
DELETE r
return a, b
```
### Finds relation

```sql
-- https://stackoverflow.com/questions/20704782/find-path-between-nodes-through-cypher
MATCH (a:Person WHERE ID(a) = 179)
MATCH (b:Person WHERE ID(b) = 182)
MATCH p = shortestPath((a)-[:ACTED_IN|HAHAHA*]->(b))
RETURN p
```

### Finds nodes

```sql
MATCH
  (a:Person WHERE ID(a) = 179),
  (c:Person WHERE ID(c) = 182)
RETURN a, c
```

### Finds children from parent
```sql
MATCH (parent:Person {firstName: "joe"})-[r:HAS_CHILD*1..]->(child:Person)
RETURN parent, collect(r), collect(child)

--------------- OR ---------------

-- https://docs.spring.io/spring-data/neo4j/reference/appendix/custom-queries.html#custom-queries.for-relationships.long-paths
MATCH p=(a:Person {firstName: "joe"})-[r:HAS_CHILD*1..]->(:Person)
RETURN a, collect(nodes(p)), collect(relationships(p))
```

### Combination of create and find
```sql
MATCH (parent:Person {firstName: "joe"})
MATCH (child:Person {firstName: "abe"})
CREATE (parent)-[r:HAS_CHILD]->(child)
WITH parent
MATCH (parent)-[r:HAS_CHILD*1..]->(child)
RETURN parent, collect(r), collect(child)

-------------- NOT CONFIRMED -------------- 
MATCH (parent:Person {firstName: "joe"}) 
MATCH (child:Person {firstName: "abe"})  
CREATE (parent)-[r:HAS_CHILD]->(child)
WITH parent, r, child
RETURN parent, collect(r), collect(child)
```


## TODO
- [ ] Determine generation on the fly (each time the page is loaded) or store in user node.
- [ ] Include "inlaw" status on user profile or do something else?
- [x] Why is the children property for findChildren empty?***, the saveChildRelationship should output the parent and its children when creating another new relation. https://stackoverflow.com/questions/77466175/problem-when-fetching-the-main-node-and-its-children-spring-data-neo4j
- [ ] Use a controller bc it combines the create and find has_child function. https://www.baeldung.com/spring-controllers
- [ ] look into path parameters and @POST https://docs.spring.io/spring-data/neo4j/docs/current-SNAPSHOT/reference/html/
- [ ] use lombok for easier time making Person class https://projectlombok.org/features/Builder, https://projectlombok.org/features/Data
- [ ] look into unit testing with JUnit https://www.vogella.com/tutorials/JUnit/article.html