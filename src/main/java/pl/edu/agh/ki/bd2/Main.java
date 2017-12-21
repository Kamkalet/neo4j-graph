package pl.edu.agh.ki.bd2;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        EmbeddedCypherCreator embeddedCypherCreator = new EmbeddedCypherCreator();
        MockPopulator mockPopulator = new MockPopulator( embeddedCypherCreator);
        mockPopulator.populate();

        solution.databaseStatistics();
        solution.runAllTests();

        System.out.println(solution.getNodeRelationShips(0));
        System.out.println(solution.getNodeRelationShips(5));

        System.out.println(solution.getNodeRelationShips(0, 22));
        System.out.println(solution.getNodeRelationShips(0, 21));

    }

}
