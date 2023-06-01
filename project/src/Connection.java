/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectequipos;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Config;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Query;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.exceptions.Neo4jException;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jorge
 */
public class Connection {
    private final String URI= "neo4j+s://09b0ed38.databases.neo4j.io";
    private final String USER="neo4j";
    private final String PASSWORD="p4L9SK7o2r_9f-ltj-Co2nLTEW4ImJntnjiwRrM45Ks";

    private final Driver driver;
    public Connection() {
        driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
    }

    public Driver getDriver() {
        return driver;
    }

    public void close() {
        // The driver object should be closed before the application ends.
        driver.close();
    }
}
