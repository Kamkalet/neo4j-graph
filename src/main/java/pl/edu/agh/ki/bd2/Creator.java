package pl.edu.agh.ki.bd2;

import java.time.LocalDate;

/**
 * Created by AD on 18.12.2017.
 */
public interface Creator {

    void createUser(String name, String surname, LocalDate dateOfBirth);

    void createPost(String title, String content, int userId);


    void createLikePostRelationShip(int userId, int postId);

    void createHatePostRelationship(int userId, int postId);

    public void createLikeUserRelationship(int userId, int userId2ToBeLiked);

    public void createHateUserRelationship(int userId, int userId2ToBeHated);


}
