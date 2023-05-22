import org.neo4j.driver.*;

public class Neo4jNodePrinter {
    public void run() {
        printNodes();
        close();
    }

    private final Driver driver;

    public Neo4jNodePrinter(String uri, String username, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    public void printNodes() {
        try (Session session = driver.session()) {
            String query = "MATCH (n) RETURN n";
            Result result = session.run(query);
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("n").asNode();
                System.out.println(node.asMap());
            }
        }
    }

    public void close() {
        driver.close();
    }

    public static void main(String[] args) {
        // Configura la conexión a tu base de datos Neo4j
        String uri = "neo4j+s://09b0ed38.databases.neo4j.io";
        String username = "neo4j";
        String password = "p4L9SK7o2r_9f-ltj-Co2nLTEW4ImJntnjiwRrM45Ks";

        Neo4jNodePrinter nodePrinter = new Neo4jNodePrinter(uri, username, password);
        nodePrinter.run();
        nodePrinter.printNodes();
        nodePrinter.close();
    }
    }
}
