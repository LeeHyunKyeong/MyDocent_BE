package com.mydocent.docent.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class SnakeCaseNamingStrategy implements PhysicalNamingStrategy {
    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return convert(name);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return convert(name);
    }

    private Identifier convert(Identifier identifier) {
        if (identifier == null) return null;
        String regex = "([a-z])([A-Z])";
        String newName = identifier.getText()
                .replaceAll(regex, "$1_$2")
                .toLowerCase();
        return Identifier.toIdentifier(newName);
    }
}