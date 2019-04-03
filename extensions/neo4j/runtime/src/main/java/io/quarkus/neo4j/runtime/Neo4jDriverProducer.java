package io.quarkus.neo4j.runtime;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.neo4j.driver.v1.Driver;

/**
 * @author Michael J. Simons
 */
@ApplicationScoped
public class Neo4jDriverProducer {

    private volatile Driver driver;

    void initialize(Driver driver) {
        this.driver = driver;
    }

    @Singleton
    @Produces
    public Driver driver() {
        return driver;
    }
}