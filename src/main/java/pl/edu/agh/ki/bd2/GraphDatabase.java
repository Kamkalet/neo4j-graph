package pl.edu.agh.ki.bd2;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

import java.io.File;
import java.util.Map;

final class GraphDatabase {

    private final GraphDatabaseService graphDatabaseService;
    private static final String GRAPH_DIR_LOC = "./neo4j";
    private static GraphDatabase INSTANCE = new GraphDatabase();

    static GraphDatabase createDatabase() {
         return INSTANCE;
    }

    private GraphDatabase() {
        graphDatabaseService = new GraphDatabaseFactory()
            .newEmbeddedDatabaseBuilder(new File(GRAPH_DIR_LOC))
            .setConfig(GraphDatabaseSettings.allow_upgrade, "false")
            .newGraphDatabase();
        registerShutdownHook();
    }

    private void registerShutdownHook() {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDatabaseService.shutdown();
            }
        });
    }

    public String runCypher(final String cypher) {
        try (Transaction transaction = graphDatabaseService.beginTx()) {
            final Result result = graphDatabaseService.execute(cypher);
            transaction.success();
            return result.resultAsString();
        }
    }

    public String runCypher(final String cypher, Map<String, Object> parameters) {
        try (Transaction transaction = graphDatabaseService.beginTx()) {
            final Result result = graphDatabaseService.execute(cypher, parameters);
            transaction.success();
            return result.resultAsString();
        }

    }

}
