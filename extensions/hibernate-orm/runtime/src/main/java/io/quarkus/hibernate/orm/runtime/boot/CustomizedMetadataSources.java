package io.quarkus.hibernate.orm.runtime.boot;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.internal.MetadataBuilderImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.spi.XmlMappingBinderAccess;
import org.hibernate.service.ServiceRegistry;

final class CustomizedMetadataSources extends MetadataSources {

    CustomizedMetadataSources(ServiceRegistry serviceRegistry) {
        super(serviceRegistry);
    }

    /**
     * Disabled the capability to load XML metadata
     *
     * @return
     */
    @Override
    public XmlMappingBinderAccess getXmlMappingBinderAccess() {
        return null;
    }

    @Override
    public MetadataBuilder getMetadataBuilder(StandardServiceRegistry serviceRegistry) {
        MetadataBuilderImpl defaultBuilder = new MetadataBuilderImpl(this, serviceRegistry);
        //This is different from the parent implementation as it doesn't allow to override the builder via service loader
        return defaultBuilder;
    }

    @Override
    public MetadataSources addResource(String name) {
        throw new UnsupportedOperationException("Unexpected invocation: XML mapping should have been disabled");
    }

}
