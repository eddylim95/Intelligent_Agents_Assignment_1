import helpers.Logger;
import helpers.UtilityHelper;
import model.*;

public class ValueIteration {
    private Maze maze;

    public ValueIteration(Maze maze){
        this.maze = maze;
    }

    /**
     * Performs the value iteration algorithm
     */
    public void updateUtilities(){
        double initialUtility;
        double updatedUtility;
        double maxUtilityChange;
        int iterations = 0;

        // Add to logger
        Logger.logStates(maze.tileStates);

        do {
            maxUtilityChange = 0;
            for (int i = 0; i < maze.numCols; i++) {
                for (int j = 0; j < maze.numRows; j++) {
                    if (!maze.tileStates[i][j].wall) {
                        initialUtility = maze.tileStates[i][j].utility;
                        updatedUtility = bellmanUpdate(maze.tileStates[i][j]);

                        double utilityDifference = Math.abs(updatedUtility - initialUtility);
                        if (utilityDifference > maxUtilityChange) {
                            maxUtilityChange = utilityDifference;
                        }
                    }
                }
            }
            iterations++;

            // Add to logger
            Logger.logStates(maze.tileStates);
        } while(maxUtilityChange >= CONSTANTS.MAX_UTILITY_ERROR *
                (1- CONSTANTS.DISCOUNT_FACTOR)/ CONSTANTS.DISCOUNT_FACTOR);
        System.out.println("Number of iterations = " + iterations);
    }

    /**
     * updates the utility of current tile and sets best action of tileState
     * @param tileState current tileState
     * @return Change in utility
     */
    private double bellmanUpdate(TileState tileState){

        // Sets best action of the tileState
        UtilityHelper.setBestAction(maze, tileState);

        // Update utility of state depending on Expected Utilities of next states
        tileState.utility = tileState.reward + CONSTANTS.DISCOUNT_FACTOR *
                UtilityHelper.maxActionExpectedUtility(maze, tileState.position);
        return tileState.utility;
    }
}
