# Neo4j example

## Running the tests

By default, the tests of this module are disabled.

To run the tests in a standard JVM with Neo4j started as a Docker container, you can run the following command:

```
mvn clean install -Ddocker -Dtest-neo4j
```

To also test as a native image, add `-Dnative`:

```
mvn clean install -Ddocker -Dtest-neo4j -Dnative
```

Alternatively you can connect to your own Neo4j instance or cluster.
Reconfigure the connection URL with `-Dneo4j.uri=bolt+routing://yourcluster:7687`;
you'll probably want to change the authentication password too: `-Dneo4j.password=NotS0Secret`.

To run the Neo4j docker instance directly (useful to debug this):

```
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_test_neo4j -e NEO4J_AUTH=neo4j/music -e NEO4J_dbms_memory_pagecache_size=10M -e NEO4J_dbms_memory_heap_initial__size=10M  -p 60513:7687 neo4j:3.5.
```
