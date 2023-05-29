import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
public class Connection {
    private final String URI = "neo4j+s://09b0ed38.databases.neo4j.io";
    private final String USER = "neo4j";
    private final String PASSWORD = "p4L9SK7o2r_9f-ltj-Co2nLTEW4ImJntnjiwRrM45Ks";
    private final Driver driver = GraphDatabase.driver("neo4j+s://09b0ed38.databases.neo4j.io", AuthTokens.basic("neo4j", "p4L9SK7o2r_9f-ltj-Co2nLTEW4ImJntnjiwRrM45Ks"));

    public Connection() {
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void close() {
        this.driver.close();
    }
}
