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
 * The Connection class represents a connection to a Neo4j database.
 * It provides methods to establish and close the connection.
 *
 * @author Jose Gramajo
 */
public class Connection {
    private final String URI= "neo4j+s://09b0ed38.databases.neo4j.io";
    private final String USER="neo4j";
    private final String PASSWORD="p4L9SK7o2r_9f-ltj-Co2nLTEW4ImJntnjiwRrM45Ks";

    private final Driver driver;

 /**
 * Constructs a new Connection object and establishes a connection to the Neo4j database.
 */
    public Connection() {
        driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
    }

    /**
     * Returns the Neo4j Driver object associated with this connection.
     *
     * @return the Neo4j Driver object
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Closes the connection to the Neo4j database.
     * It is important to call this method when the application ends to release resources properly.
     */
    public void close() {
        // The driver object should be closed before the application ends.
        driver.close();
    }
}
