package pl.edu.agh.ki.bd2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kamkalet on 18.12.2017.
 */

public class EmbeddedCypherCreator implements Creator {

    private final GraphDatabase graphDatabase = GraphDatabase.createDatabase();

    @Override
    public void createUser(String name, String surname, LocalDate dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dateOfBirth.format(formatter);

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("surname", surname);
        params.put("dateOfBirth", dateOfBirth.toString());
        graphDatabase.runCypher("CREATE (user:User { " +
                "name : $name," +
                "surname : $surname, " +
                "dateOfBirth : $dateOfBirth}) " +
                "RETURN user", params);
    }

    @Override
    public void createPost(String title, String content, int userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        params.put("content", content);
        params.put("userId", userId);
        System.out.println(graphDatabase.runCypher("MATCH (u:User) WHERE ID(u)=$userId " +
                "CREATE (p:Post { " + "title : $title," + "content : $content}) " +
                "MERGE (p) -[r:CREATED_BY]-> (u)"+
                "RETURN p,r,u", params));
    }

    @Override
    public void createLikePostRelationShip(int userId, int postId) {
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("userId", userId);
        System.out.println(graphDatabase.runCypher(
                "MATCH (u:User) WHERE ID(u)=$userId " +
                "MATCH (p:Post) WHERE ID(p)=$postId " +
                "MERGE (u) -[r:LIKES]-> (p)"+
                "RETURN u,r,p", params));
    }

    @Override
    public void createHatePostRelationship(int userId, int postId) {
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("userId", userId);
        System.out.println(graphDatabase.runCypher(
                "MATCH (u:User) WHERE ID(u)=$userId " +
                        "MATCH (p:Post) WHERE ID(p)=$postId " +
                        "MERGE (u) -[r:HATES]-> (p)"+
                        "RETURN u,r,p", params));
    }

    @Override
    public void createLikeUserRelationship(int userId, int userId2ToBeLiked) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("userId2ToBeLiked", userId2ToBeLiked);
        System.out.println(graphDatabase.runCypher(
                "MATCH (u:User) WHERE ID(u)=$userId " +
                        "MATCH (u2:User) WHERE ID(u2)=$userId2ToBeLiked " +
                        "MERGE (u) -[r:LIKES]-> (u2)"+
                        "RETURN u, u2", params));
    }

    @Override
    public void createHateUserRelationship(int userId, int userId2ToBeHated) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("userId2ToBeHated", userId2ToBeHated);
        System.out.println(graphDatabase.runCypher(
                "MATCH (u:User) WHERE ID(u)=$userId " +
                        "MATCH (u2:User) WHERE ID(u2)=$userId2ToBeHated " +
                        "MERGE (u) -[r:HATES]-> (u2)"+
                        "RETURN u, u2", params));
    }

}
