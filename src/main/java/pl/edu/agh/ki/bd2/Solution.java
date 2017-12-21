package pl.edu.agh.ki.bd2;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    private final GraphDatabase graphDatabase = GraphDatabase.createDatabase();

    public void databaseStatistics() {
        System.out.println(graphDatabase.runCypher("CALL db.labels()"));
        System.out.println(graphDatabase.runCypher("CALL db.relationshipTypes()"));
        System.out.println(graphDatabase.runCypher("CALL db.schema()"));
    }

    public void runAllTests() {
        System.out.println(graphDatabase.runCypher("MATCH (users:User) " +
                "RETURN users"));
        System.out.println(graphDatabase.runCypher("MATCH (posts:Post) " +
                "RETURN posts"));
        System.out.println(graphDatabase.runCypher("MATCH (users:User)-[relationships]-(posts:Post) " +
                "RETURN users,relationships,posts"));
        System.out.println(graphDatabase.runCypher("MATCH (users:User)-[relationships]->(users2:User) " +
                "RETURN users,relationships,users2"));
        System.out.println(graphDatabase.runCypher("MATCH (node1)-[relationships]->(node2) " +
                "RETURN node1,relationships,node2"));
    }

    String getNodeRelationShips(int nodeId){
        Map<String, Object> params = new HashMap<>();
        params.put("nodeId", nodeId);
        return graphDatabase.runCypher("MATCH (node)-[relationship]->(node2)"
                + " WHERE ID(node)=$nodeId"
                + " RETURN node, relationship, node2",params);

    }

    String getNodeRelationShips(int nodeId1, int nodeId2){
        Map<String, Object> params = new HashMap<>();
        params.put("nodeId1", nodeId1);
        params.put("nodeId2", nodeId2);
        return graphDatabase.runCypher("MATCH (node1),(node2), path = shortestPath((node1)-[*]->(node2))"
                + " WHERE ID(node1)=$nodeId1 AND ID(node2)=$nodeId2"
                + " RETURN path",params);

    }

}
