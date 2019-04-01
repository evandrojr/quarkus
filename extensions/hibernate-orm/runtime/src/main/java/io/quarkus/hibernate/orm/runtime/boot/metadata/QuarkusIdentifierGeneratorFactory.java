package io.quarkus.hibernate.orm.runtime.boot.metadata;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.dialect.Dialect;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.factory.spi.MutableIdentifierGeneratorFactory;
import org.hibernate.type.Type;

public final class QuarkusIdentifierGeneratorFactory implements MutableIdentifierGeneratorFactory {

    private final Dialect dialect;
    private final Map<String, IdentifierGenerator> strategyToIdGeneratorMap;
    private final Map<String, Class> strategyNameToClassMap;

    public QuarkusIdentifierGeneratorFactory(Dialect dialect, Map<String, IdentifierGenerator> strategyToIdGeneratorMap) {
        this.dialect = dialect;
        this.strategyToIdGeneratorMap = strategyToIdGeneratorMap;
        Map<String, Class> nameToClassMap = new HashMap<>();
        for (Map.Entry<String, IdentifierGenerator> entry : strategyToIdGeneratorMap.entrySet()) {
            final Class aClass = entry.getValue().getClass();
            //Fill this in for both the short hand name and the FQN:
            nameToClassMap.put(entry.getKey(), aClass);
            nameToClassMap.put(aClass.getName(), aClass);
        }
        strategyNameToClassMap = Collections.unmodifiableMap(nameToClassMap);
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public void setDialect(Dialect dialect) {
    }

    @Override
    public IdentifierGenerator createIdentifierGenerator(String strategy, Type type, Properties config) {
        final IdentifierGenerator identifierGenerator = strategyToIdGeneratorMap.get(strategy);
        if (identifierGenerator == null) {
            throw new IllegalArgumentException("IdentifierGenerator " + strategy
                    + " was not captured during the Quarkus Augmentation Phase, and therefore cannot be used at runtime");
        }
        return identifierGenerator;
    }

    @Override
    public Class getIdentifierGeneratorClass(String strategy) {
        final Class<? extends IdentifierGenerator> aClass = strategyNameToClassMap.get(strategy);
        if (aClass == null) {
            throw new IllegalArgumentException("IdentifierGenerator " + strategy
                    + " was not captured during the Quarkus Augmentation Phase, and therefore cannot be used at runtime");
        }
        return aClass;
    }

    @Override
    public void register(String strategy, Class generatorClass) {
        throw new IllegalStateException("Unsupported operation");
    }

}
