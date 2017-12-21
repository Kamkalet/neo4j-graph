package pl.edu.agh.ki.bd2;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by kamkalet on 21.12.2017.
 */

public class MockPopulator implements Populator {

    Creator creator;

    public MockPopulator(Creator creator) {
        this.creator = creator;
    }

    @Override
    public void populate() {

        //Users
        creator.createUser("Kamil", "Kaleta", LocalDate.of(1995, Month.DECEMBER, 25));
        creator.createUser("Lucyna", "Niebywała", LocalDate.of(1985, Month.DECEMBER, 4));
        creator.createUser("Anna", "Dziwny", LocalDate.of(1915, Month.JANUARY, 6));
        creator.createUser("Adam", "Adamowicz", LocalDate.of(1999, Month.APRIL, 18));
        creator.createUser("Karyna", "Stalin", LocalDate.of(1975, Month.DECEMBER, 11));

        //Posts
        creator.createPost("Achtung", "Karny za niewylogowanie się", 0);
        creator.createPost("Gigant333", "Długi tekst333", 20);
        creator.createPost("New", "Dobrze sie zyje", 21);
        creator.createPost("Sample title", "Sample content", 23);
        creator.createPost("Titel", "Inhalt", 22);
        creator.createPost("xx", "cc", 0);
        creator.createPost("xx2", "cc2", 0);
        creator.createPost("xx3", "cc3", 0);
        creator.createPost("xx4", "cc4", 22);
        creator.createPost("xx5", "cc5", 0);
        creator.createPost("xx6", "cc6", 0);

        //relation hates/likes
        creator.createLikePostRelationShip(0, 1);
        creator.createLikePostRelationShip(0, 1);
        creator.createLikePostRelationShip(20, 1);
        creator.createLikePostRelationShip(23, 2);
        creator.createLikePostRelationShip(21, 3);

        creator.createHatePostRelationship(0, 4);
        creator.createHatePostRelationship(0, 5);
        creator.createHatePostRelationship(0, 6);
        creator.createHatePostRelationship(0, 7);
        creator.createHatePostRelationship(0, 9);
        creator.createHatePostRelationship(22, 9);
        creator.createHatePostRelationship(22, 8);

        creator.createLikeUserRelationship(0, 20);
        creator.createLikeUserRelationship(0, 21);
        creator.createLikeUserRelationship(21, 22);
        creator.createLikeUserRelationship(23, 22);
        creator.createLikeUserRelationship(0, 23);

        creator.createHateUserRelationship(22, 0);
        creator.createHateUserRelationship(22, 21);
        creator.createHateUserRelationship(22, 22);
        creator.createHateUserRelationship(22, 23);

    }
}
