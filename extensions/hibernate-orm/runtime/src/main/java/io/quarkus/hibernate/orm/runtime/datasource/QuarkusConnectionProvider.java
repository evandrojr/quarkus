package io.quarkus.hibernate.orm.runtime.datasource;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class QuarkusConnectionProvider implements ConnectionProvider {

    private final DataSource ds;

    public QuarkusConnectionProvider(DataSource dataSource) {
        this.ds = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    @Override
    public void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return true;
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return ConnectionProvider.class.equals( unwrapType ) ||
                QuarkusConnectionProvider.class.isAssignableFrom( unwrapType ) ||
                DataSource.class.isAssignableFrom( unwrapType );
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        if ( ConnectionProvider.class.equals( unwrapType ) ||
                QuarkusConnectionProvider.class.isAssignableFrom( unwrapType ) ) {
            return (T) this;
        }
        else if ( DataSource.class.isAssignableFrom( unwrapType ) ) {
            return (T) ds;
        }
        else {
            throw new UnknownUnwrapTypeException( unwrapType );
        }
    }
}
