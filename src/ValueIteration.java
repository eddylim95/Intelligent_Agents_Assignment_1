import java.util.ArrayList;

public class ValueIteration {
    private Maze maze;

    public ValueIteration(Maze maze){
        this.maze = maze;
    }

    public void updateUtilities(){
        /*
        Perform value iteration algorithm
         */
        double utility = 0.0;
        double newUtility = 0.0;
        double maxUtilityChange = 0.0;
        int iterations = 0;

        do {
            utility = newUtility;
            maxUtilityChange = 0;
            for (int i = 0; i < maze.numCols; i++) {
                for (int j = 0; j < maze.numRows; j++) {
                    if (!maze.tileStates[i][j].wall) {
                        newUtility = bellmanUpdate(maze.tileStates[i][j]);

                        double utilityDifference = Math.abs(newUtility - utility);
                        if (utilityDifference > maxUtilityChange) {
                            maxUtilityChange = utilityDifference;
                        }
                    }
                }
            }
            iterations++;
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
        double maxActionExpectedUtility = maxActionExpectedUtility(tileState.position);

        // Sets best action of the tileState
        setBestAction(tileState, maxActionExpectedUtility);

        tileState.utility = tileState.reward + CONSTANTS.DISCOUNT_FACTOR * maxActionExpectedUtility;

        return tileState.utility;
    }

    /**
     * Obtain the max expected utility of the different actions of up, down, left, right
     * @param position Position of tileState
     * @return Max expected utility
     */
    private double maxActionExpectedUtility(Position position){
        double up = upActionExpectedUtility(position);
        double down = downActionExpectedUtility(position);
        double left = leftActionExpectedUtility(position);
        double right = rightActionExpectedUtility(position);

        return Math.max(Math.max(up, down), Math.max(left, right));
    }

    /**
     * Sets best Action of tileState
     * @param tileState tileState
     * @param maxActionExpectedUtility Max expected utility of actions
     */
    private void setBestAction(TileState tileState, double maxActionExpectedUtility) {
        double up = upActionExpectedUtility(tileState.position);
        double down = downActionExpectedUtility(tileState.position);
        double left = leftActionExpectedUtility(tileState.position);
        double right = rightActionExpectedUtility(tileState.position);

        tileState.bestAction = new ArrayList<>();
        if (maxActionExpectedUtility == up)
            tileState.bestAction.add(Action.UP);
        if (maxActionExpectedUtility == down)
            tileState.bestAction.add(Action.DOWN);
        if (maxActionExpectedUtility == left)
            tileState.bestAction.add(Action.LEFT);
        if (maxActionExpectedUtility == right)
            tileState.bestAction.add(Action.RIGHT);
    }

    /**
     * Expected utility of the up action according to action probabilities
     * @param tileStatePosition Position of agent
     * @return expected utility of action
     */
    private double upActionExpectedUtility(Position tileStatePosition){
        return CONSTANTS.CORRECT_ACTION_PROBABILITY * upActionUtility(tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * leftActionUtility(tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * rightActionUtility(tileStatePosition);
    }

    /**
     * Expected utility of the down action according to action probabilities
     * @param tileStatePosition Position of agent
     * @return expected utility of action
     */
    private double downActionExpectedUtility(Position tileStatePosition){
        return CONSTANTS.CORRECT_ACTION_PROBABILITY * downActionUtility(tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * leftActionUtility(tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * rightActionUtility(tileStatePosition);
    }

    /**
     * Expected utility of the left action according to action probabilities
     * @param tileStatePosition Position of agent
     * @return expected utility of action
     */
    private double leftActionExpectedUtility(Position tileStatePosition){
        return CONSTANTS.CORRECT_ACTION_PROBABILITY * leftActionUtility(tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * upActionUtility(tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * downActionUtility(tileStatePosition);
    }

    /**
     * Expected utility of the right action according to action probabilities
     * @param tileStatePosition Position of agent
     * @return expected utility of action
     */
    private double rightActionExpectedUtility(Position tileStatePosition){
        return CONSTANTS.CORRECT_ACTION_PROBABILITY * rightActionUtility(tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * upActionUtility(tileStatePosition) +
                CONSTANTS.WRONG_ACTION_PROBABILITY * downActionUtility(tileStatePosition);
    }

    /**
     * Obtain the Utility of a up action
     * @param position Position of agent
     * @return utility of action
     */
    private double upActionUtility(Position position){
        /*
        Utility of the up action
         */
        // Check action if can be done, i.e. tileStates exists and not a wall
        if (maze.canMoveUp(position)){
            return maze.tileStates[position.col][position.row - 1].utility;
        }
        return maze.tileStates[position.col][position.row].utility;
    }

    /**
     * Obtain the Utility of a down action
     * @param position Position of agent
     * @return utility of action
     */
    private double downActionUtility(Position position){
        /*
        Utility of the down action
         */
        // Check action if can be done, i.e. tileStates exists and not a wall
        if (maze.canMoveDown(position)) {
            return maze.tileStates[position.col][position.row + 1].utility;
        }
        return maze.tileStates[position.col][position.row].utility;
    }

    /**
     * Obtain the Utility of a left action
     * @param position Position of agent
     * @return utility of action
     */
    private double leftActionUtility(Position position){
        /*
        Utility of the left action
         */
        // Check action if can be done, i.e. tileStates exists and not a wall
        if (maze.canMoveLeft(position)){
            return maze.tileStates[position.col - 1][position.row].utility;
        }
        return maze.tileStates[position.col][position.row].utility;
    }

    /**
     * Obtain the Utility of a right action
     * @param position Position of agent
     * @return utility of action
     */
    private double rightActionUtility(Position position){
        /*
        Utility of the right action
         */
        // Check action if can be done, i.e. tileStates exists and not a wall
        if (maze.canMoveRight(position)){
            return maze.tileStates[position.col + 1][position.row].utility;
        }
        return maze.tileStates[position.col][position.row].utility;
    }
}
