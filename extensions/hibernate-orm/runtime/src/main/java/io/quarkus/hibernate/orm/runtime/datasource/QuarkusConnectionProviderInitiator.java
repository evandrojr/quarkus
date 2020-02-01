package io.quarkus.hibernate.orm.runtime.datasource;

import org.hibernate.HibernateException;
import org.hibernate.boot.registry.StandardServiceInitiator;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryImplementor;

import java.util.Map;

/**
 * Quarkus custom implementation of {@link org.hibernate.engine.jdbc.connections.internal.ConnectionProviderInitiator}.
 * Requires an instance of {@link QuarkusConnectionProvider} to be injected into the configuration under the key
 * {@link AvailableSettings#DATASOURCE}.
 */
public class QuarkusConnectionProviderInitiator implements StandardServiceInitiator<ConnectionProvider> {

    /**
     * Singleton access
     */
    public static final QuarkusConnectionProviderInitiator INSTANCE = new QuarkusConnectionProviderInitiator();

    @Override
    public ConnectionProvider initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
        Object o = configurationValues.get(AvailableSettings.DATASOURCE);
        if (o==null) {
            throw new HibernateException("The Quarkus / Hibernate ORM integration requires configuration of a Datasource: no other ways of looking up JDBC connections are allowed");
        }
        else if (! (o instanceof QuarkusConnectionProvider)) {
            throw new HibernateException("Unexpected type of ConnectionProvider configured. Type was: `" + o.getClass() + "` while we expect a `" + QuarkusConnectionProvider.class.getName() + '`');
        }
        else {
            return (QuarkusConnectionProvider)o;
        }
    }

    @Override
    public Class<ConnectionProvider> getServiceInitiated() {
        return ConnectionProvider.class;
    }

}
