import helpers.Logger;
import helpers.UtilityHelper;
import model.CONSTANTS;
import model.Maze;

public class PolicyIteration {
    private Maze maze;
    private UtilityHelper utilityHelper;

    public PolicyIteration(Maze maze){
        this.maze = maze;
    }

    public void updatePolicies(){
        // Note that there is no initial random initialization of best actions per state as they are created with a
        // default action already
        boolean changed;
        int iterations = 0;

        do {
            policyEvaluation();
            changed = false;
            for (int i = 0; i < maze.numCols; i++) {
                for (int j = 0; j < maze.numRows; j++) {
                    if (!maze.tileStates[i][j].wall) {
                        if (UtilityHelper.maxActionExpectedUtility(maze, maze.tileStates[i][j].position) >
                                UtilityHelper.getActionExpectedUtility(maze.tileStates[i][j].bestAction, maze,
                                        maze.tileStates[i][j].position)) {
                            UtilityHelper.setBestAction(maze, maze.tileStates[i][j]);
                            changed = true;
                        }
                    }
                }
            }
            iterations++;

            // Add to logger
            Logger.logStates(maze.tileStates);
        } while (changed);
        System.out.println("Number of iterations = " + iterations);
    }

    private void policyEvaluation(){
        // K_VALUE is predefined as a constant to how many times policy evaluation will carry out
        for (int k = 0; k < CONSTANTS.K_VALUE; k++) {
            for (int i = 0; i < maze.numCols; i++) {
                for (int j = 0; j < maze.numRows; j++) {
                    if (!maze.tileStates[i][j].wall) {
                        maze.tileStates[i][j].utility = maze.tileStates[i][j].reward + CONSTANTS.DISCOUNT_FACTOR *
                                UtilityHelper.getActionExpectedUtility(maze.tileStates[i][j].bestAction, maze,
                                        maze.tileStates[i][j].position);
                    }
                }
            }
        }
    }
}
