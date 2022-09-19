package dev.tsvinc.micronaut.sqlite;

import io.micronaut.runtime.Micronaut;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        Micronaut.run(Application.class, args);
    }
}
